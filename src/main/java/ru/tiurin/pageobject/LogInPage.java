package ru.tiurin.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LogInPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH,using = "//button[text()='Войти']")
    private SelenideElement buttonLogIn;
    @FindBy(how = How.XPATH,using = "//h2[text()='Войти']")
    private SelenideElement headerLogIn;
    @Step("Заполение поля 'Email' значением {email}")
    public LogInPage fillEmailInput(String email){
        emailInput.sendKeys(email);
        return this;
    }
    @Step("Заполение поля 'Пароль' значением {password}")
    public LogInPage fillPasswordInput(String password){
        passwordInput.sendKeys(password);
        return this;
    }
    @Step("Клик по кнопке 'Зарегестрироваться'")
    public LogInPage clickButtonLogIn(){
        buttonLogIn.shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Ожидание закрытия страницы Вход")
    public MainPage logInPageDisappear(){
        headerLogIn.should(Condition.disappear);
        return page(MainPage.class);
    }
    @Step("Ожидание загрузки страницы Входа")
    public LogInPage logInPageLoaded(){
        headerLogIn.shouldBe(Condition.visible);
        return this;
    }
    @Step("Получение статуса страницы Входа")
    public boolean isLogInPageLoaded(){
        return headerLogIn.isDisplayed();

    }
}
