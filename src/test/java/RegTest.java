import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.tiurin.api.User;
import ru.tiurin.pageobject.LogInPage;
import ru.tiurin.pageobject.RegPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegTest extends BaseTest {
    private final List<User> userListForDelete = new ArrayList<>();

    @Test
    @DisplayName("Проверка регистрации нового пользователя с валидными данными")
    public void checkNewUserIsRegWithValidData() {
        User user = User.getRandomUserValidData();
        open(RegPage.URL, RegPage.class)
                .fillNameInput(user.getName())
                .fillEmailInput(user.getEmail())
                .fillPasswordInput(user.getPassword())
                .clickButtonReg()
                .regPageDisappear();
        userListForDelete.add(user);
        String currentURL = webdriver().driver().url();
        assertEquals(LogInPage.URL, currentURL);

    }

    @Test
    @DisplayName("Проверка регистрации нового пользователя с повторным использованием Email")
    public void checkUserIsNotRegisteredWithRepeatedEmail() {
        User userValid = User.getRandomUserValidData();
        open(RegPage.URL, RegPage.class)
                .fillNameInput(userValid.getName())
                .fillEmailInput(userValid.getEmail())
                .fillPasswordInput(userValid.getPassword())
                .clickButtonReg();
        userListForDelete.add(userValid);

        User userNotValid = User.getRandomUserValidData();
        userNotValid.setEmail(userValid.getEmail());
        boolean isUserAlreadyExistErrorMessageDisplayed =
                open(RegPage.URL, RegPage.class)
                        .fillNameInput(userNotValid.getName())
                        .fillEmailInput(userNotValid.getEmail())
                        .fillPasswordInput(userNotValid.getPassword())
                        .clickButtonReg()
                        .isUserAlreadyExistErrorMessage();
        userListForDelete.add(userNotValid);
        assertTrue("Не отобразилось сообщение об ошибке", isUserAlreadyExistErrorMessageDisplayed);
    }

    @Test
    @DisplayName("Проверка регистрации нового пользователя с полем Password не соотвествующему требования")
    public void checkNewUserIsNotRegisteredWithTooShortPassword() {
        User userNotValid = User.getRandomUserValidData();
        userNotValid.setPassword(User.getRandomNotValidPassword());
        boolean isIncorrectPasswordErrorMessageDisplayed =
                open(RegPage.URL, RegPage.class)
                        .fillNameInput(userNotValid.getName())
                        .fillEmailInput(userNotValid.getEmail())
                        .fillPasswordInput(userNotValid.getPassword())
                        .clickButtonReg()
                        .isIncorrectPasswordErrorMessage();
        userListForDelete.add(userNotValid);
        assertTrue("Не отобразилось сообщение об ошибке", isIncorrectPasswordErrorMessageDisplayed);
    }


    @After
    @DisplayName("Удаление пользователя и очистка cookies")
    public void cleanDate() {
        for (User user : userListForDelete) {
            if (user != null) {
                user.deleteUser();
            }
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
