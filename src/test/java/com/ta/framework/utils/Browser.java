package com.ta.framework.utils;

import com.ta.framework.env.EnvironmentData;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Browser {

    private static Browser instance = null;
    private EnvironmentData environmentData;
    final static Logger LOGGER = CustomLogger.getLogger(Browser.class);


    public Browser() {
        parseConfig();
    }

    private void parseConfig() {
        try {
            String env = System.getProperty("env");
            String configString = ResourceLoader.load("./src/test/resources/config/environment.json");

            String baseUrl = JsonPath.from(configString).getString(env + ".baseUrl");
            String userName = JsonPath.from(configString).getString(env + ".user.userName");
            String password = JsonPath.from(configString).getString(env + ".user.password");

            environmentData = new EnvironmentData(baseUrl, userName, password);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * browser singleton
     * @return instance of broswer
     */
    public static Browser getInstance() {
        if (instance == null) {
           instance = new Browser();
        }
        return instance;
    }

    public String getBaseUrl() {
        return environmentData.getBaseUrl();
    }

    public String getUserName() {
        return environmentData.getUserName();
    }

    public String getPassword() {
        return environmentData.getPassword();
    }
}