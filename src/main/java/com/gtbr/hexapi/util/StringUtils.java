package com.gtbr.hexapi.util;

import java.util.Arrays;

public class StringUtils {

    public static boolean in(String actual, String... strings) {
        return Arrays.asList(strings).contains(actual);
    }
}
