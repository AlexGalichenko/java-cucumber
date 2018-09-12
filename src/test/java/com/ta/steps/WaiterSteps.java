package com.ta.steps;

import com.codeborne.selenide.Condition;
import com.ta.constants.ConstantMap;
import com.ta.framework.pop.map.PageManager;
import com.ta.framework.pop.page.Page;
import com.ta.framework.utils.Browser;
import com.ta.framework.utils.CustomLogger;
import com.ta.framework.memory.Memory;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.sleep;

public class WaiterSteps {

    private Memory memory = Memory.getInstance().setConstantMap(new ConstantMap());
    private Browser browser = Browser.getInstance();
    private final static Logger LOGGER = CustomLogger.getLogger(WaiterSteps.class);
    private static final int EXPLICIT_WAIT = 30 * 1000;

    @When("^(?:I|User) waits? for \"(\\d+)\" seconds$")
    public void waitForSeconds(int time) {
        sleep(time * 1000);
    }

    @When("^(?:I|User) waits? until \"(.+)\" element is (visible|enabled|disabled|not visible|present|not present)$")
    public void waitForCondition(String alias, String condition) throws Error {
        Page page = PageManager.getCurrentPage();

        switch (condition) {
            case "present": page.getElement(alias).waitUntil(Condition.appear, EXPLICIT_WAIT); break;
            case "not present": page.getElement(alias).waitUntil(Condition.disappear, EXPLICIT_WAIT); break;
            case "visible": page.getElement(alias).waitUntil(Condition.visible, EXPLICIT_WAIT); break;
            case "enabled": page.getElement(alias).waitUntil(Condition.enabled, EXPLICIT_WAIT); break;
            case "disabled": page.getElement(alias).waitUntil(Condition.disabled, EXPLICIT_WAIT); break;
            case "not visible": page.getElement(alias).waitUntil(Condition.not(Condition.visible), EXPLICIT_WAIT); break;
            default: throw new Error(condition + " is not defined");
        }

    }

}