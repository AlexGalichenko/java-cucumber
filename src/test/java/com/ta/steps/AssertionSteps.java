package com.ta.steps;

import com.ta.constants.ConstantMap;
import com.ta.framework.assertion.BooleanAssert;
import com.ta.framework.assertion.NumberAssert;
import com.ta.framework.assertion.StringAssert;
import com.ta.framework.helpers.Helpers;
import com.ta.framework.pop.map.PageManager;
import com.ta.framework.pop.page.Page;
import com.ta.framework.utils.CustomLogger;
import com.ta.framework.memory.Memory;
import com.ta.pop.PageMap;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.WebDriverRunner.url;

public class AssertionSteps {

    private Memory memory = Memory.getInstance().setConstantMap(new ConstantMap());
    final static Logger LOGGER = CustomLogger.getLogger(AssertionSteps.class);

    @Then("^Text of \"(.+)\" element (should|should not) (be equal to|contain|start with|end with) \"(.+)\"$")
    public void textShouldBe(String alias, String shouldNot, String validation, String expectedValue) {
        Page page = PageManager.getCurrentPage();

        String actualText = page.getElement(alias).getText();
        String expectedText = memory.parseValue(expectedValue);
        boolean negate = Helpers.negate(shouldNot);

        switch (validation) {
            case "be equal to": StringAssert.assertEquals(actualText, expectedText, negate); break;
            case "contain": StringAssert.assertContains(actualText, expectedText, negate); break;
            case "start with": StringAssert.assertStartsWith(actualText, expectedText, negate); break;
            case "end with": StringAssert.assertEndsWith(actualText, expectedText, negate); break;
            default: throw new Error(validation + " is not defined");
        }
    }

    @Then("^Text \"(.+)\" (should|should not) (be equal to|contain|start with|end with) \"(.+)\"$")
    public void textValidation(String actualValue, String shouldNot, String validation, String expectedValue) throws Error {

        String actualText = memory.parseValue(actualValue);
        String expectedText = memory.parseValue(expectedValue);
        boolean negate = Helpers.negate(shouldNot);

        switch (validation) {
            case "be equal to": StringAssert.assertEquals(actualText, expectedText, negate); break;
            case "contain": StringAssert.assertContains(actualText, expectedText, negate); break;
            case "start with": StringAssert.assertStartsWith(actualText, expectedText, negate); break;
            case "end with": StringAssert.assertEndsWith(actualText, expectedText, negate); break;
            default: throw new Error(validation + " is not defined");
        }
    }

    @Then("^URL (should|should not) (be equal to|contain|start with|end with) \"(.+)\"$")
    public void textValidation(String shouldNot, String validation, String expectedValue) throws Error {

        String actualText = url();
        String expectedText = memory.parseValue(expectedValue);
        boolean negate = Helpers.negate(shouldNot);

        switch (validation) {
            case "be equal to": StringAssert.assertEquals(actualText, expectedText, negate); break;
            case "contain": StringAssert.assertContains(actualText, expectedText, negate); break;
            case "start with": StringAssert.assertStartsWith(actualText, expectedText, negate); break;
            case "end with": StringAssert.assertEndsWith(actualText, expectedText, negate); break;
            default: throw new Error(validation + " is not defined");
        }
    }

    @Then("^\"(.+)\" element (should|should not) be (visible|enabled|disabled)")
    public void elementValidation(String alias, String shouldNot, String validation) throws Error {

        Page page = PageManager.getCurrentPage();
               boolean negate = Helpers.negate(shouldNot);

        switch (validation) {
            case "visible": BooleanAssert.assertTrue(page.getElement(alias).isDisplayed(), negate); break;
            case "enabled": BooleanAssert.assertTrue(page.getElement(alias).isEnabled(), negate); break;
            case "disabled": BooleanAssert.assertFalse(page.getElement(alias).isEnabled(), negate); break;
            default: throw new Error(validation + " is not defined");
        }

    }

    @Then("^Text of \"(\\d+)\" element of \"(.+)\" collection (should|should not) (be equal to|contain|start with|end with) \"(.+)\"$")
    public void textOfCollectionElementShouldBe(int index, String alias, String shouldNot, String validation, String expectedValue) {
        Page page = PageManager.getCurrentPage();

        String actualText = page.getCollection(alias).get(index).getText();
        String expectedText = memory.parseValue(expectedValue);
        boolean negate = Helpers.negate(shouldNot);

        switch (validation) {
            case "be equal to": StringAssert.assertEquals(actualText, expectedText, negate); break;
            case "contain": StringAssert.assertContains(actualText, expectedText, negate); break;
            case "start with": StringAssert.assertStartsWith(actualText, expectedText, negate); break;
            case "end with": StringAssert.assertEndsWith(actualText, expectedText, negate); break;
            default: throw new Error(validation + " is not defined");
        }
    }

    @Then("^Value \"(.+)\" (should|should not) be (equal to|greater than|less than) \"(.+)\"$")
    public void valueShouldBe(String actual, String shouldNot, String validation, String expected) {
        Page page = PageManager.getCurrentPage();

        Double actualValue = Double.valueOf(memory.parseValue(actual));
        Double expectedValue = Double.valueOf(memory.parseValue(expected));
        boolean negate = Helpers.negate(shouldNot);

        switch (validation) {
            case "equal to": NumberAssert.assertEquals(actualValue, expectedValue, negate); break;
            case "greater than": NumberAssert.assertGreaterThan(actualValue, expectedValue, negate); break;
            case "less than": NumberAssert.assertLessThan(actualValue, expectedValue, negate); break;
            default: throw new Error(validation + " is not defined");
        }
    }

    @Then("^(?:I|User) (should|should not) be on \"(.+)\" page$")
    public void pageShouldBe(String shouldNot, String expectedPage) {
        String expectedPageDefinitionSelector = new PageMap().getPageDefinition(expectedPage).getSelector();
        boolean negate = Helpers.negate(shouldNot);
        System.out.println(expectedPageDefinitionSelector);
        StringAssert.assertMatch(url(), expectedPageDefinitionSelector, negate);
    }

}