package com.ta.framework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ResourceLoader {

    final static Logger LOGGER = CustomLogger.getLogger(ResourceLoader.class);

    /**
     * Read resource file
     * @param path - path to fie with resources
     * @return String - content of file
     * @throws IOException
     */
    public static String load(String path) throws IOException {
        File file = new File(path);

        String content = "";

        try {
            content = FileUtils.readFileToString(file, "utf-8");
        }
        catch (Error err) {
            LOGGER.error(err);
        }

        return content;
    }

}