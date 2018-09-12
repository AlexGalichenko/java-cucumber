package com.ta.framework.selenium;

public interface Selectable {

    String getSelectorType();
    String getSelector();
    String getText();
    boolean isCollection();
}

