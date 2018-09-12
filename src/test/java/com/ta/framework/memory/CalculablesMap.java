package com.ta.framework.memory;

import java.util.function.Function;

public interface CalculablesMap {

    void setCalculable(String key, Function<String[], String> lambda);
    String getCalculable(String value);

}
