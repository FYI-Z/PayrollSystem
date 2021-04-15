package com.tomorrow.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

    //String转List
    public static List<String> strToList(String str){
        List<String> list = Arrays.asList(str.split(";"));
        List<String> newlist = new ArrayList<String>(list);
        return newlist;
    }

    //List转String
    public static String listToString(List<String> list){
        String str = "";
        int size = list.size();
        for (int i=0;i<size;i++){
            str += list.get(i) + ";";
        }
        return str;
    }
}
