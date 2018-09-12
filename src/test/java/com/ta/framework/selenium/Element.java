package com.ta.framework.selenium;

public class Element extends AbstractElement{

    public Element(String selectorType, String selector) {
        super(selectorType, selector, false);
    }

    public Element(String selectorType, String text, String selector) {
        super(selectorType, text, selector, false);
    }

}
