import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.tiurin.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Проверка переключения на вкладку 'Булки'")
    public void checkClickBuns() {
        String expectedTab = "Булки";
        open(MainPage.URL, MainPage.class).clickTabFillings();
        page(MainPage.class).clickTabBun();

        String currentTab = page(MainPage.class).getCurrentTab();
        assertEquals("Вкладка не переключилась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Соусы'")
    public void checkClickSouce() {
        String expectedTab = "Соусы";
        open(MainPage.URL, MainPage.class).clickTabSouce();

        String currentTab = page(MainPage.class).getCurrentTab();
        assertEquals("Вкладка не поменялась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Начинки'")
    public void checkClickFilling() {
        String expectedTab = "Начинки";
        open(MainPage.URL, MainPage.class).clickTabFillings();

        String currentTab = page(MainPage.class).getCurrentTab();
        assertEquals("Вкладка не поменялась", expectedTab, currentTab);
    }
}
