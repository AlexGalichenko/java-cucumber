package com.ta.framework.pop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.ta.framework.selenium.Selectable;

import java.util.HashMap;

public interface Page {

    void defineElement(String alias, String selectorType, String selector);
    void defineCollection(String alias, String selectorType, String selector);
    void defineElement(String alias, String selectorType, String text, String selector);
    void defineCollection(String alias, String selectorType, String text, String selector);
    void assignComponent(String assignName, Page component);
    SelenideElement getElement(String alias);
    ElementsCollection getCollection(String alias);
    HashMap<String, Selectable> getAllElements();
}
