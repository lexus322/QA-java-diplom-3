import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.tiurin.api.User;
import ru.tiurin.pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class LogInTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegPage.URL, RegPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonReg()
                .regPageDisappear();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидный данными авторизоваться через Личный кабинет")
    public void checkHeaderAccountButtonLogInWithValidData() {
        open(MainPage.URL, HeaderPage.class).clickAccountLinkHeaderButton();
        checkLogInUserWithValidData();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидными данными авторизоваться по кнопке Войти в аккаунт")
    public void checkLogInToAccountButtonLogInWithValidData() {
        open(MainPage.URL, MainPage.class).clickLogInButton();
        checkLogInUserWithValidData();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидными данными авторизоваться со страницы Регистрации по ссылке Войти")
    public void checkLinkLogInInRegistrationPageWithValidData() {
        open(RegPage.URL, RegPage.class).clickLinkLogIn();
        checkLogInUserWithValidData();
    }

    @Test
    @DisplayName("Проверка может ли пользователь с валидными данными авторизоваться со страницы Забыли пароль по ссылке Войти")
    public void checkLinkLogInInForgotPasswordPageWithValidData() {
        open(ForgotPasswordPage.URL, ForgotPasswordPage.class).clickLogInLink();
        checkLogInUserWithValidData();
    }

    @After
    @DisplayName("Переход в личный кабинет 'выход из профиля'+'удаление пользователя'+'очистка cookies'")
    public void cleanDate() {
        page(HeaderPage.class).clickAccountLinkHeaderButton();
        page(ProfilePage.class)
                .profilePageLoaded()
                .clickLogOutButton()
                .profilePageDisappear();
        if (validUserData != null) {
            validUserData.deleteUser();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }


    private void checkLogInUserWithValidData() {
        page(LogInPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .logInPageDisappear()
                .mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Залогиниться не удалось", MainPage.URL, currentUrl);

        page(HeaderPage.class).clickAccountLinkHeaderButton();
        String actualLogin = page(ProfilePage.class).getLogInInput();
        assertEquals("Логин не совпадает", validUserData.getEmail(), actualLogin);
    }
}
