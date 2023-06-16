package ru.tiurin.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    @FindBy(how = How.XPATH, using = "//label[text()='Логин']/following-sibling::input")
    private SelenideElement logInInput;
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logOutInput;

    @Step("Получить логин из поля 'Логин'")
    public String getLogInInput() {
        return logInInput.shouldBe(Condition.visible).getValue();
    }

    @Step("Клик по кнопку 'Выход' на странице профиля")
    public ProfilePage clickLogOutButton() {
        logOutInput.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Ожидание загрузки страницы Профиля")
    public ProfilePage profilePageLoaded() {
        logOutInput.shouldBe(Condition.visible);
        return this;
    }

    @Step("Ожидание закрытия страницы Профиля")
    public ProfilePage profilePageDisappear() {
        logOutInput.shouldBe(Condition.disappear);
        return this;
    }
}
