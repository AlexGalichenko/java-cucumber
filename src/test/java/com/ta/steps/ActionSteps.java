package com.ta.steps;

import com.ta.constants.CalculablesMap;
import com.ta.constants.ConstantMap;
import com.ta.framework.memory.Memory;
import com.ta.framework.pop.map.PageManager;
import com.ta.framework.pop.page.Page;
import com.ta.framework.utils.Browser;
import com.ta.framework.utils.CustomLogger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ActionSteps {

    private Memory memory = Memory.getInstance()
            .setConstantMap(new ConstantMap())
            .setCalculablesMap(new CalculablesMap());

    private Browser browser = Browser.getInstance();
    private final static Logger LOGGER = CustomLogger.getLogger(ActionSteps.class);

    @Given("^(?:I|User) logins? as \"(.+)\" \"(.+)\"$")
    public void iLoginAs(String login, String password) {
        Page page = PageManager.getCurrentPage();

        page.getElement("Username Input").sendKeys(login);
        page.getElement("Password Input").sendKeys(password);
        page.getElement("Login Button").click();
    }

    @Given("^(?:I|User) logins?$")
    public void iLogin() {
        Page page = PageManager.getCurrentPage();

        page.getElement("Username Input").sendKeys(browser.getUserName());
        page.getElement("Password Input").sendKeys(browser.getPassword());
        page.getElement("Login Button").click();
    }

    @When("^(?:I|User) clicks? \"(.+)\"$")
    public void iClick(String alias) {
        Page page = PageManager.getCurrentPage();

        page.getElement(alias).click();
    }

    @When("^(?:I|User) types? \"(.+)\" text to \"(.+)\" element$")
    public void iTypeTextTo(String text, String alias) {
        Page page = PageManager.getCurrentPage();
        String parsedText = memory.parseValue(text);

        page.getElement(alias).sendKeys(parsedText);
    }

    @When("^(?:I|User) inserts? \"(.+)\" text to \"(.+)\" element$")
    public void iInsertTextTo(String text, String alias) {
        Page page = PageManager.getCurrentPage();
        String parsedText = memory.parseValue(text);

        executeJavaScript("arguments[0].value = `" + parsedText + "`", page.getElement(alias).getWrappedElement());
        page.getElement(alias).sendKeys(Keys.SPACE, Keys.BACK_SPACE);

    }

    @When("^(?:I|User) clicks? element with \"(.+)\" (text|index) in \"(.+)\" collection")
    public void iClickElementInCollection(String identifier, String selector, String alias) throws Exception {
        Page page = PageManager.getCurrentPage();

        String identifierParsed = memory.parseValue(identifier);

        switch (selector) {
            case "text": page.getCollection(alias).findBy(exactText(identifierParsed)).click(); break;
            case "index": page.getCollection(alias).get(Integer.parseInt(identifierParsed) - 1).click(); break;
            default: throw new Exception("Selection method by " + selector + " is not defined");
        }

    }

    @When("^(?:I|User) clicks? \"(\\d+)\" element of \"(.+)\" collection$")
    public void rememberTextOfElementInCollection(String index, String alias) {
        Page page = PageManager.getCurrentPage();

        String parsedIndex = memory.parseValue(index);

        page.getCollection(alias).get(Integer.parseInt(parsedIndex) - 1).click();

    }



}