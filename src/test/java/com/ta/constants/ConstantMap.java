package com.ta.constants;

import com.ta.framework.memory.AbstractConstantMap;

public class ConstantMap extends AbstractConstantMap {

    public ConstantMap() {
        setConstant("NUMBER_ONE", "1");
        setFileConstant("FILE_CONST", "./src/test/resources/fileconstants/const.txt");
    }

}
