package com.tomorrow.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static List<String> strToList(String str){
        List<String> list = Arrays.asList(str.split(";"));
        List<String> newlist = new ArrayList<String>(list);
        return newlist;
    }

}
