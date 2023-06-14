package ru.tiurin.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FeedOrdersPage {
    private final String URL = "https://stellarburgers.nomoreparties.site/feed";
    @FindBy(how = How.XPATH,using = "//h1[text()='Лента заказов']")
    private SelenideElement feedOrdersHeader;
    @Step("Ожидание загрузки страницы Лента заказов")
    public FeedOrdersPage feedOrdersPageLoaded() {
        feedOrdersHeader.shouldBe(Condition.visible);
        return this;
    }
    @Step("Ожидание закрытия страницы Лента заказов")
    public FeedOrdersPage feedOrdersPageDisappear() {
        feedOrdersHeader.shouldBe(Condition.disappear);
        return this;
    }
}
