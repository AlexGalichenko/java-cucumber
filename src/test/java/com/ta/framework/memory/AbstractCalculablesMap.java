package com.ta.framework.memory;

import com.ta.framework.utils.CustomLogger;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.function.Function;

public abstract class AbstractCalculablesMap implements CalculablesMap {

    private final static Logger LOGGER = CustomLogger.getLogger(AbstractCalculablesMap.class);

    private HashMap<String, Function<String[], String>> calculables = new HashMap<>();

    @Override
    public void setCalculable(String key, Function<String[], String> lambda) {
        this.calculables.put(key, lambda);
    }

    @Override
    public String getCalculable(String key) {

        String parsedKey = this.calculables.keySet()
                .stream()
                .filter(pattern -> key.matches(pattern))
                .findFirst()
                .orElseThrow(() -> new Error("Calculable with pattern match " + key + " was not found"));

        System.out.println(parsedKey);

        if (this.calculables.containsKey(parsedKey)) {
            return this.calculables.get(parsedKey).apply(getArguments(key));
        }
        else {
            throw new Error(key + " does not exist");
        }
    }

    private String[] getArguments(String signature) {

        String GET_ARGS_REGEXP = "\\(|\\)";
        String SPLIT_ARGS_REGEXP = ",\\s*";

        String[] splittedSignature = signature.split(GET_ARGS_REGEXP);

        if (splittedSignature.length > 1) {
            return splittedSignature[1].split(SPLIT_ARGS_REGEXP);
        } else return new String[0];
    }

}
