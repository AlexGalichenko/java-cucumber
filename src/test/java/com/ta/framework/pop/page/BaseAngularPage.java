package com.ta.framework.pop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BaseAngularPage extends BasePage implements Page {

    private static final String ANGULAR_WAITER = "if (window.angular == undefined){return true} else {var r;window.angular.getTestability(document).whenStable(function(){r=true;}); return r;}";

    private void waitForAngular() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 30);
        wait.until(ExpectedConditions.jsReturnsValue(ANGULAR_WAITER));
    }

    @Override
    public SelenideElement getElement(String alias) {
        waitForAngular();
        return super.getElement(alias);
    }

    @Override
    public ElementsCollection getCollection(String alias) {
        waitForAngular();
        return super.getCollection(alias);
    }
}
