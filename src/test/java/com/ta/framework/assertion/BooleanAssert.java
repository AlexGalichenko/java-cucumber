package com.ta.framework.assertion;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BooleanAssert {

    public static void assertTrue(boolean actual, boolean negate) {

        if (negate) {
            assertThat(actual).isNotEqualTo(true);
        }
        else {
            assertThat(actual).isEqualTo(true);
        }
    }

    public static void assertFalse(boolean actual, boolean negate) {

        if (negate) {
            assertThat(actual).isNotEqualTo(false);
        }
        else {
            assertThat(actual).isEqualTo(false);
        }
    }

}
