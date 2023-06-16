import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.tiurin.api.User;
import ru.tiurin.pageobject.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderWithLogInTest extends BaseTest {
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
        page(MainPage.class).mainPageLoaded();
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Личный кабинет' авторизованного пользователя")
    public void checkHeaderAccountButtonClickWithLogIn() {
        page(HeaderPage.class).clickAccountLinkHeaderButton();
        page(ProfilePage.class).profilePageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", ProfilePage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Лента заказов' авторизованного пользователя")
    public void checkHeaderFeedButtonClickWithLogIn() {
        page(HeaderPage.class).clickFeedHeaderButton();
        page(FeedOrdersPage.class).feedOrdersPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrdersPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Конструктор' авторизованного пользователя")
    public void ckeckHeaderConstructorButtonClickWithLogIn() {
        page(HeaderPage.class).clickConstructorHeaderButton();
        page(MainPage.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на Логотип авторизованного пользователя")
    public void checkHeaderLogoClickWithLogIn() {
        page(HeaderPage.class).clickLogoHeaderButton();
        page(MainPage.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
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
