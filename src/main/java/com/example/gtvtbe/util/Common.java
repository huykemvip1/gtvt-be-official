package com.example.gtvtbe.util;


public class Common {
    public static String[] stringToArray(String source){
        if (source == null){
            return null;
        }
        return source.substring(1,source.length()-1).split(",");
    }
}
