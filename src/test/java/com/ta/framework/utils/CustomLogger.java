package com.ta.framework.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CustomLogger {

    public static Logger getLogger(Class clazz) {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        return Logger.getLogger(clazz);
    }

}
