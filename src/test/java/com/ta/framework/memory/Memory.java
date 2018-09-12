package com.ta.framework.memory;

import com.ta.framework.utils.CustomLogger;
import com.ta.steps.ActionSteps;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class Memory {

    private final static Logger LOGGER = CustomLogger.getLogger(ActionSteps.class);
    private static Memory instance = null;

    private Memory() {

    }

    private HashMap<String, String> memory = new HashMap<>();
    private ConstantMap constantMap;
    private CalculablesMap calculablesMap;

    /**
     * return remembered value
     * @param key - key to parse
     * @return parsed value
     */
    public String parseValue(String key){

        String value = key;

        try {
            if (key.matches("^\\${1}[^\\$]+$")) {
                String keyParsed = key.replace("$", "");
                value = getValue(keyParsed);
            }
            else if (key.matches("^\\${2}[^\\$].+$")) {
                String keyParsed = key.replace("$", "");
                value = getConstantValue(keyParsed);
            }
            else if (key.matches("^\\${3}[^\\$].+$")) {
                String keyParsed = key.replace("$", "");
                value = getFileConstantValue(keyParsed);
            }
            else if (key.matches("^#.+$")) {
                String keyParsed = key.replace("#", "");
                System.out.println(keyParsed);
                value = getCalculablesValue(keyParsed);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return value;
    }

    /**
     * remember value with defined key
     * @param key key to save
     * @param value value to save
     */
    public void setValue(String key, String value) {
        this.memory.put(key,value);
    }

    /**
     * return value by defined key
     * @param key key to read
     * @return String value returned by key
     */
    private String getValue(String key) throws Error {
        if (this.memory.containsKey(key)) {
            return this.memory.get(key);
        }
        else {
            throw new Error(key + " does not exist");
        }
    }

    /**
     * return value of constant from constant object
     * @param key key to read
     * @return String value returned by key
     */
    private String getConstantValue(String key) throws Error {
       return this.constantMap.getConstantValue(key);
    }

    /**
     * return value of file constant from constant object
     * @param key key to read
     * @return String value returned by key
     */
    private String getFileConstantValue(String key) throws Error {
        return this.constantMap.getFileConstantValue(key);
    }

    /**
     * return value of file constant from calculables object
     * @param key key to read
     * @return String value returned by key
     */
    private String getCalculablesValue(String key) throws Error {
        return this.calculablesMap.getCalculable(key);
    }

    /**
     * Set constant data source
     * @param constantMap - object of constant map
     */
    public Memory setConstantMap(ConstantMap constantMap) {
        this.constantMap = constantMap;
        return this;
    }

    /**
     * Set calculables data source
     * @param calculablesMap - object of constant map
     */
    public Memory setCalculablesMap(CalculablesMap calculablesMap) {
        this.calculablesMap = calculablesMap;
        return this;
    }

    /**
     * memory singleton
     * @return instance of memory
     */
    public static Memory getInstance() {
        if (instance == null) {
           instance = new Memory();
        }
        return instance;
    }
}
