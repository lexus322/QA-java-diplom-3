package ru.tiurin.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderPage {
    @FindBy(how = How.XPATH,using = "div[contains(@class,'logo')]/a[@href='/']")
    private SelenideElement logoLink;
    @FindBy(how = How.XPATH,using = "a[contains(@class,'header')][@href='/']")
    private SelenideElement constructorLink;
    @FindBy(how = How.XPATH,using = "a[@href='/feed']")
    private SelenideElement feedLink;
    @FindBy(how = How.XPATH,using = "a[@href='/account']")
    private SelenideElement accountLink;
    @Step("Клик по логотипу в шапке")
    public HeaderPage clickLogoHeaderButton(){
        logoLink.shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Клик по 'Конструктору' в шапке")
    public HeaderPage clickConstructorHeaderButton(){
        constructorLink.shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Клик по 'Ленте заказов' в шапке")
    public HeaderPage clickFeedHeaderButton(){
        feedLink.shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Клик по 'Ленте заказов' в шапке")
    public HeaderPage clickAccountLinkHeaderButton(){
        accountLink.shouldBe(Condition.visible).click();
        return this;
    }
}
