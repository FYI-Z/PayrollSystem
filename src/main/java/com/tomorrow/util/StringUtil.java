package com.tomorrow.util;

import java.util.UUID;

public class StringUtil {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-", "");
        return id;
    }
}
