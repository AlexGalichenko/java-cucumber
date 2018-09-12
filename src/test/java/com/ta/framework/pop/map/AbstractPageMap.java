package com.ta.framework.pop.map;

import com.ta.framework.pop.page.BasePage;
import com.ta.framework.pop.page.Page;
import com.ta.framework.utils.CustomLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;

public abstract class AbstractPageMap {

    private final static Logger LOGGER = CustomLogger.getLogger(BasePage.class);

    private HashMap<String, PageDefinition> pageDefinitions = new HashMap<>();

    protected void definePage(String alias, String selector, Class pageClass) {
        this.pageDefinitions.put(alias, new PageDefinition(selector, pageClass));
    }

    public Page getPage(String pageSelector) {

        Class pageClass = pageDefinitions
                .values()
                .stream()
                .filter(pageDefinition -> pageSelector.matches(pageDefinition.getSelector()))
                .findFirst()
                .orElseThrow(() -> new Error("Page with selector " + pageSelector + " was not found"))
                .getPageClass();

        Page constructedPage = null;

        try {
            constructedPage = (Page)pageClass.newInstance();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return constructedPage;

        }

    public PageDefinition getPageDefinition(String key) {
        return pageDefinitions.get(key);
    }
}
