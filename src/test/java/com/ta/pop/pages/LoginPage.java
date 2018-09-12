package com.ta.pop.pages;

import com.ta.framework.pop.page.BaseAngularPage;
import com.ta.pop.components.BaseComponent;

public class LoginPage extends BaseAngularPage {

    public LoginPage() {

        assignComponent("BaseComponent",new BaseComponent());
        defineElement("Button", "css", "div");

    }

}
