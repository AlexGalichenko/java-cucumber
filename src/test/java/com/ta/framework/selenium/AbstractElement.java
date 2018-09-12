package com.ta.framework.selenium;

public abstract class AbstractElement implements Selectable {

    private String selectorType;
    private String selector;
    private String text;
    private boolean isCollection;

    AbstractElement(String selectorType, String selector, boolean isCollection) {
        this.selectorType = selectorType;
        this.selector = selector;
        this.isCollection = isCollection;
    }

    AbstractElement(String selectorType, String text, String selector, boolean isCollection) {
        this.selectorType = selectorType;
        this.selector = selector;
        this.text = text;
        this.isCollection = isCollection;
    }

    public String getSelectorType() {
        return selectorType;
    }

    public String getSelector() {
        return selector;
    }

    public String getText() {
        if (text != null) {
            return text;
        }
        else return "";

    }

    public boolean isCollection() {
        return isCollection;
    }
}
