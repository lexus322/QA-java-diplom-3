import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.tiurin.pageobject.FeedOrdersPage;
import ru.tiurin.pageobject.HeaderPage;
import ru.tiurin.pageobject.LogInPage;
import ru.tiurin.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderWithOutLogInTest extends BaseTest {
    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Личный кабинет' не авторизованного пользователя")
    public void checkHeaderAccountButtonClickWithOutLogIn() {
        open(MainPage.URL, HeaderPage.class).clickAccountLinkHeaderButton();
        page(LogInPage.class).logInPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", LogInPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Лента заказов' не авторизованного пользователя")
    public void checkHeaderFeedButtonClickWithOutLogIn() {
        open(MainPage.URL, HeaderPage.class).clickFeedHeaderButton();
        page(FeedOrdersPage.class).feedOrdersPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", FeedOrdersPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на переход в 'Конструктор' не авторизованного пользователя")
    public void ckeckHeaderConstructorButtonClickWithOutLogIn() {
        open(MainPage.URL, HeaderPage.class).clickConstructorHeaderButton();
        page(MainPage.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка клика в шапке главной страницы на Логотип не авторизованного пользователя")
    public void checkHeaderLogoClickWithOutLogIn() {
        open(MainPage.URL, HeaderPage.class).clickLogoHeaderButton();
        page(MainPage.class).mainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }
}
