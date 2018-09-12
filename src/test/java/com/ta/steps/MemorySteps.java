package com.ta.steps;

import com.ta.constants.ConstantMap;
import com.ta.framework.pop.map.PageManager;
import com.ta.framework.pop.page.Page;
import com.ta.framework.utils.CustomLogger;
import com.ta.framework.memory.Memory;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

public class MemorySteps {

    private Memory memory = Memory.getInstance().setConstantMap(new ConstantMap());
    final static Logger LOGGER = CustomLogger.getLogger(ActionSteps.class);

    @When("^(?:I|User) remembers? text of \"(.+)\" element as \"(.+)\"$")
    public void rememberTextOfElement(String alias, String key) {
        Page page = PageManager.getCurrentPage();

        String elementText = page.getElement(alias).getText();
        memory.setValue(key, elementText);
    }

    @Then("^(?:I|User) remembers? \"(\\d+)\" element of \"(.+)\" collection as \"(.+)\"$")
    public void rememberTextOfElementInCollection(int index, String alias, String key) {
        Page page = PageManager.getCurrentPage();

        String elementText = page.getCollection(alias).get(index - 1).getText();
        memory.setValue(key, elementText);

    }

    @Then("^(?:I|User) remembers? number of elements in \"(.+)\" collection as \"(.+)\"$")
    public void rememberTextOfElementInCollection(String alias, String key) {
        Page page = PageManager.getCurrentPage();

        String elementText = Integer.toString(page.getCollection(alias).size());
        memory.setValue(key, elementText);

    }
}
