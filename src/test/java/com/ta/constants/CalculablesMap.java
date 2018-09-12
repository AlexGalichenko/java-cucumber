package com.ta.constants;

import com.ta.framework.memory.AbstractCalculablesMap;

public class CalculablesMap extends AbstractCalculablesMap {

    public CalculablesMap() {

        //example
        setCalculable("^MY_FIRST_CALCULABLE\\(.+\\)$", (args) -> {
            String a = "1";
            String b = "2";
            System.out.println(args[0]);
            System.out.println(args[1]);

            return args[0];
        });

    }
}
