import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.tiurin.api.User;
import ru.tiurin.pageobject.HeaderPage;
import ru.tiurin.pageobject.LogInPage;
import ru.tiurin.pageobject.ProfilePage;
import ru.tiurin.pageobject.RegPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogOutTest extends BaseTest {
    private User validUserData;

    @Before
    @DisplayName("Создание случайного пользователя и авторизация")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegPage.URL, RegPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonReg()
                .regPageDisappear();

        open(LogInPage.URL, LogInPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .logInPageDisappear();
        page(HeaderPage.class).clickAccountLinkHeaderButton();
    }

    @Test
    @Description("Проверка выхода и аккаунта")
    public void checkLogOutFromProfile() {
        page(ProfilePage.class)
                .profilePageLoaded()
                .clickLogOutButton()
                .profilePageDisappear();
        boolean isLogInPageLoaded = page(LogInPage.class).isLogInPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", LogInPage.URL, currentUrl);
        assertTrue("Страница авторизации не загружена", isLogInPageLoaded);
    }

    @After
    @DisplayName("Удаление пользователя и очистка cookies")
    public void cleanDate() {
        if (validUserData != null) {
            validUserData.deleteUser();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
