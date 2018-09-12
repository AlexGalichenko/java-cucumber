package com.ta.framework.pop.map;

import com.ta.framework.pop.page.BasePage;
import com.ta.framework.pop.page.Page;
import com.ta.framework.utils.CustomLogger;
import com.ta.pop.PageMap;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.WebDriverRunner.url;

public class PageManager {

    private final static Logger LOGGER = CustomLogger.getLogger(BasePage.class);

    public static Page getCurrentPage() {
        String url = url();
        System.out.println(url);


        return new PageMap().getPage(url);
    }

    public static PageDefinition getPageDefinition(String key) {
        return new PageMap().getPageDefinition(key);
    }

}
