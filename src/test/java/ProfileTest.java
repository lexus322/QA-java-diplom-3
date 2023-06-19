import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.tiurin.api.User;
import ru.tiurin.pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest {
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
    @DisplayName("Проверка перехода на страницу страницу Конструктора из Профиля пользователя")
    public void checkNavigateFromProfileToConstructor() {
        page(HeaderPage.class)
                .clickConstructorHeaderButton();
        boolean isMainPageLoaded = page(MainPage.class)
                .mainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
    }

    @Test
    @DisplayName("Проверка перехода на Главную страницу по клику на логотип из Профиля пользователя")
    public void checkNavigateFromProfileToMainPageLogo() {
        page(HeaderPage.class)
                .clickLogoHeaderButton();
        boolean isMainPageLoaded = page(MainPage.class)
                .mainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
    }

    @After
    @DisplayName("Переход в личный кабинет 'выход из профиля'+'удаление пользователя'+'очистка cookies'")
    public void cleanData() {
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
}
