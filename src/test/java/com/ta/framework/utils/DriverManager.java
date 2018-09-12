package com.ta.framework.utils;

public class DriverManager {

    static public void initBrowser() {

        String browser = "chrome";

        if (System.getProperty("browser") != null) {
            browser = System.getProperty("browser");
        }
        switch (browser) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", "target/drivers/binaries/windows/googlechrome/64bit/chromedriver.exe");
                System.setProperty("selenide.browser", "chrome");
            } break;
            case "ie": {
                System.setProperty("webdriver.ie.driver", "target/drivers/binaries/windows/internetexplorer/64bit/IEDriverServer.exe");
                System.setProperty("selenide.browser", "ie");
            }
        }
    }
}
