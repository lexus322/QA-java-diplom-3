package ru.tiurin.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    private final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    @FindBy(how = How.XPATH,using = "//a[text()='Войти']")
    private SelenideElement logInLink;
    @Step("Клик по кнопке 'Войти' на станице сброса пароля")
    public LogInPage clickLogInLink(){
        logInLink.shouldBe(Condition.visible).click();
        return page(LogInPage.class);
    }
    @Step("Ожидание загрузки страницы Сброса пароля")
    public ForgotPasswordPage forgotPasswordPageLoaded() {
        logInLink.shouldBe(Condition.visible);
        return this;
    }
    @Step("Ожидание закрытия страницы Сброса пароля")
    public ForgotPasswordPage forgotPasswordPageDisappear() {
        logInLink.shouldBe(Condition.disappear);
        return this;
    }
}
