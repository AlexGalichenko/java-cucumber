package com.ta.framework.assertion;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumberAssert {

    public static void assertEquals(Double actual, Double expected, boolean negate) {

        if (negate) {
            assertThat(actual).isNotEqualTo(expected);
        }
        else {
            assertThat(actual).isEqualTo(expected);
        }
    }

    public static void assertGreaterThan(Double actual, Double expected, boolean negate) {

        if (negate) {
            assertThat(expected).isGreaterThan(actual);
        }
        else {
            assertThat(actual).isGreaterThan(expected);
        }
    }

    public static void assertLessThan(Double actual, Double expected, boolean negate) {

        if (negate) {
            assertThat(expected).isLessThan(actual);
        }
        else {
            assertThat(actual).isLessThan(expected);
        }
    }

}
