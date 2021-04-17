package com.tomorrow.util;

import cn.hutool.crypto.SecureUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {

    /**
     * String转List
     * @param str
     * @return
     */
    public static List<String> strToList(String str){
        List<String> list = Arrays.asList(str.split(","));
        List<String> newlist = new ArrayList<String>(list);
        return newlist;
    }

    /**
     * List转String
     * @param list
     * @return
     */
    public static String listToString(List<String> list){
        String str = "";
        int size = list.size();
        for (int i=0;i<size;i++){
            str += list.get(i) + ",";
        }
        return str;
    }

    public static String md5(String pwd){
        if(pwd==null){
            return null;
        }
        return SecureUtil.md5(pwd);
    }
}
