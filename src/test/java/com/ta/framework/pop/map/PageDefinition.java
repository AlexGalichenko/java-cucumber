package com.ta.framework.pop.map;

public class PageDefinition {

    private String selector;
    private Class pageClass;

    PageDefinition(String selector, Class pageClass) {
        this.selector = selector;
        this.pageClass = pageClass;
    }


    public String getSelector() {
        return selector;
    }

    public Class getPageClass() {
        return pageClass;
    }

}
