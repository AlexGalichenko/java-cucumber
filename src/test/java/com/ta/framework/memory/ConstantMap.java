package com.ta.framework.memory;

public interface ConstantMap {

    void setConstant(String key, String value);
    void setFileConstant(String key, String path);
    String getConstantValue(String key);
    String getFileConstantValue(String key);

}
