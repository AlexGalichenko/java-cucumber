package com.ta.pop;

import com.ta.framework.pop.map.AbstractPageMap;
import com.ta.framework.utils.Browser;
import com.ta.pop.pages.LoginPage;

public class PageMap extends AbstractPageMap {

    public PageMap() {

        String baseUrl = Browser.getInstance().getBaseUrl();

        definePage("Login Page", "^.*authorization.*$", LoginPage.class);
    }

}
