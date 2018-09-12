package com.ta.framework.selenium;

public class Collection extends AbstractElement{

    public Collection(String selectorType, String selector) {
        super(selectorType, selector, true);
    }

    public Collection(String selectorType, String text, String selector) {
        super(selectorType, text, selector, true);
    }

}
