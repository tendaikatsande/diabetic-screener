package com.zimttech.diabeticscreener.util;

import java.util.UUID;

public class RandomNumberUtils {



    public static String getUid() {
        return UUID.randomUUID().toString();
    }
}
