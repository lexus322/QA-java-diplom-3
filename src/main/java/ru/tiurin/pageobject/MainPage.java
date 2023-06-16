package ru.tiurin.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement headerOrder;
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLogIn;
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement tabBun;
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement tabSouce;
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement tabFillings;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'current')]/span")
    private SelenideElement tabCurrent;
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement headerFilling;

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public MainPage clickLogInButton() {
        buttonLogIn.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Клик по табу 'Булки' на главной странице")
    public MainPage clickTabBun() {
        tabBun.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Клик по табу 'Соусы' на главной странице")
    public MainPage clickTabSouce() {
        tabSouce.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Клик по табу 'Начинки' на главной странице")
    public MainPage clickTabFillings() {
        tabFillings.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Получить текст текущего таба")
    public String getCurrentTab() {
        return tabCurrent.getText();
    }

    @Step("Ожидание закрытия Главной страницы")
    public MainPage mainPageDisappear() {
        headerOrder.should(Condition.disappear);
        return this;
    }

    @Step("Ожидание загрузки Главной страницы")
    public MainPage mainPageLoaded() {
        headerOrder.shouldBe(Condition.visible);
        return this;
    }

    @Step("Получение статуса Главной страницы")
    public boolean isMainPageLoaded() {
        return headerOrder.isDisplayed();

    }
}
