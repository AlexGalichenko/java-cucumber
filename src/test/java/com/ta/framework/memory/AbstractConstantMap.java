package com.ta.framework.memory;

import com.ta.framework.utils.CustomLogger;
import com.ta.framework.utils.ResourceLoader;
import org.apache.log4j.Logger;

import java.util.HashMap;

public abstract class AbstractConstantMap implements ConstantMap {

    private final static Logger LOGGER = CustomLogger.getLogger(AbstractConstantMap.class);

    private HashMap<String, String> constants = new HashMap<>();

    @Override
    public void setConstant(String key, String value) {
        constants.put(key, value);
    }

    @Override
    public void setFileConstant(String key, String path) {
        constants.put(key, path);
    }

    @Override
    public String getConstantValue(String key) throws Error {
        if (this.constants.containsKey(key)) {
            return this.constants.get(key);
        }
        else {
            throw new Error(key + " does not exist");
        }
    }

    @Override
    public String getFileConstantValue(String key) throws Error {
        String content = "";
        try {
            content = ResourceLoader.load(constants.get(key));
        }
        catch (Exception err) {
            LOGGER.error(err);
            throw new Error(err);
        }
        return content;

    }
}
