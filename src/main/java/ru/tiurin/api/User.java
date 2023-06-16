package ru.tiurin.api;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Data;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Data
@Builder
public class User {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String LOGIN_API = "api/auth/";
    private static final Faker FAKER_RU = new Faker(new Locale("ru-RU"));
    private static final Faker FAKER_EN = new Faker(new Locale("en-GB"));
    private String email;
    private String password;
    private String name;

    public static User getRandomUserValidData() {
        final String email = getRandomValidEmail();
        final String password = getRandomValidPassword();
        final String name = getRandomValidName();
        return new User(email, password, name);
    }

    @Step("Создание валидных данных для поля Email")
    public static String getRandomValidEmail() {
        return FAKER_EN.internet().emailAddress();
    }

    @Step("Создание валидных данных для поля Password")
    public static String getRandomValidPassword() {
        int minSize = 6;
        int maxSize = 20;
        return FAKER_EN.internet().password(minSize, maxSize);
    }

    @Step("Создание невалидных данных для поля Password")
    public static String getRandomNotValidPassword() {
        int minSize = 1;
        int maxSize = 6;
        return FAKER_EN.internet().password(minSize, maxSize);
    }

    @Step("Создание валидных данных для поля Name")
    public static String getRandomValidName() {
        return FAKER_EN.name().fullName();
    }

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Получение токена для использования в API")
    private String getUserToken() {
        String accessToken = "";
        ValidatableResponse response = given()
                .spec(getBaseSpec())
                .body(this)
                .when()
                .post(LOGIN_API + "login")
                .then();
        if (response.extract().statusCode() == 200) {
            accessToken = response.extract().path("accessToken");
        }
        return accessToken;
    }

    @Step("Удаление созданого пользователя при помощи API")
    public void deleteUser() {
        String token = getUserToken();
        given()
                .spec(getBaseSpec())
                .header("authorization", token)
                .when()
                .delete(LOGIN_API + "user")
                .then();
    }
}
