package com.ta.framework.assertion;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringAssert {

    public static void assertEquals(String actual, String expected, boolean negate) {

        if (negate) {
            assertThat(actual).isNotEqualTo(expected);
        }
        else {
            assertThat(actual).isEqualTo(expected);
        }
    }

    public static void assertContains(String actual, String expected, boolean negate) {
        if (negate) {
            assertThat(actual).doesNotContain(expected);
        }
        else {
            assertThat(actual).contains(expected);
        }
    }

    public static void assertStartsWith(String actual, String expected, boolean negate) {
        if (negate) {
            assertThat(actual).doesNotStartWith(expected);
        }
        else {
            assertThat(actual).startsWith(expected);
        }
    }

    public static void assertEndsWith(String actual, String expected, boolean negate) {
        if (negate) {
            assertThat(actual).doesNotEndWith(expected);
        }
        else {
            assertThat(actual).endsWith(expected);
        }
    }

    public static void assertMatch(String actual, String pattern, boolean negate) {
        if (negate) {
            assertThat(actual).doesNotMatch(pattern);
        }
        else {
            assertThat(actual).matches(pattern);
        }
    }

}
