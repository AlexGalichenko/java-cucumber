package com.ta.framework.pop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.ta.framework.selenium.Collection;
import com.ta.framework.selenium.Element;
import com.ta.framework.utils.CustomLogger;
import com.ta.framework.selenium.Selectable;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class BasePage implements Page {

    private final static Logger LOGGER = CustomLogger.getLogger(BasePage.class);

    private HashMap<String, Selectable> elements = new HashMap<>();

    /**
     * define element in PO
     * @param alias - alias of element
     * @param selectorType - selector type
     * @param selector - selector
     */
    @Override
    public void defineElement(String alias, String selectorType, String selector) {
        this.elements.put(alias, new Element(selectorType, selector));
    }

    /**
     * define element in PO
     * @param alias - alias of element
     * @param selectorType - selector type
     * @param text - text to locate element
     * @param selector - selector
     */
    @Override
    public void defineElement(String alias, String selectorType, String text, String selector) {
        this.elements.put(alias, new Element(selectorType, text, selector));
    }

    /**
     * define collection in PO
     * @param alias - alias of element
     * @param selectorType - selector type
     * @param selector - selector
     */
    @Override
    public void defineCollection(String alias, String selectorType, String selector) {
        this.elements.put(alias, new Collection(selectorType, selector));
    }

    /**
     * define collection in PO
     * @param alias - alias of element
     * @param selectorType - selector type
     * @param text - text to locate element
     * @param selector - selector
     */
    @Override
    public void defineCollection(String alias, String selectorType, String text, String selector) {
        this.elements.put(alias, new Collection(selectorType, text, selector));
    }

    /**
     * get element by alias
     * @param alias - alias of element
     * @return selenide element
     */
    @Override
    public SelenideElement getElement(String alias) {

        String selectorType;
        String selector;
        String text;

        try {
            Selectable element = this.elements.get(alias);
            selectorType = element.getSelectorType();
            selector = element.getSelector();
            text = element.getText();
        } catch (NullPointerException npe) {
            throw new Error("Element " + alias + " was not found on page");
        }

        switch (selectorType) {
            case "css": return $(selector);
            case "xpath": return  $(byXpath(selector));
            case "cssContainingText": return $$(selector).findBy(exactText(text));
            default: throw new Error("Selector type " + selectorType + " is not defined");
        }

    }

    /**
     * get collection by alias
     * @param alias - alias of collection
     * @return selenide collection
     */
    @Override
    public ElementsCollection getCollection(String alias) {

        String selectorType;
        String selector;

        try {
            Selectable collection = this.elements.get(alias);
            selectorType = collection.getSelectorType();
            selector = collection.getSelector();
        } catch (NullPointerException npe) {
            throw new Error("Element " + alias + " was not found on page");
        }

        switch (selectorType) {
            case "css": return $$(selector);
            case "xpath": return  $$(byXpath(selector));
            case "cssContainingText": throw new Error(selectorType + " has no sense in case of collection");
            default: throw new Error("Selector type " + selectorType + " is not defined");
        }
    }

    @Override
    public HashMap<String, Selectable> getAllElements() {
        return this.elements;
    }

    /**
     * define component in PO
     * @param component - component to define
     */
    @Override
    public void assignComponent(String assignName, Page component) {

        component.getAllElements().forEach((String key, Selectable value) -> {
            this.elements.put(assignName + " > " + key, value);
        });

    }
}
