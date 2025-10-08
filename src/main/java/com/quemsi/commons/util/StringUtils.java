package com.quemsi.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtils extends org.springframework.util.StringUtils {

    public static String[] commaDelimitedListToStringArray(String str) {
        String trimmed = org.springframework.util.StringUtils.trimAllWhitespace(str);
        return org.springframework.util.StringUtils.delimitedListToStringArray(trimmed, ",");
    }
    
    public static boolean isEmptyOrNull(String str) {
    	return str == null || "".equals(str.trim());
    }
    public static String emptyIfNull(String str) {
    	return str==null?"":str;
    }
    public static String nullIfEmpty(String str) {
    	return (str==null || "".equals(str.trim()))?null:str;
    }
    public static String trim(String str) {
    	return str==null?str:str.trim();
    }
    public static String trimSymetric(String str, String front, String end){
        if (str == null) {
            return null;
        }
        if (!isEmptyOrNull(front) && !isEmptyOrNull(end)
                && str.startsWith(front) && str.endsWith(end)
                && str.length() >= front.length() + end.length()) {
            return str.substring(front.length(), str.length() - end.length());
        }
        return str;
    }
    public static String trim(String str, String front, String end) {
    	if(str == null){
            return null;
        }
        boolean retry = true;
        while (retry) {
            retry = false;
            if(!isEmptyOrNull(front)){
                if(str.startsWith(front)){
                    str = str.substring(front.length());
                    retry = true;
                }
            }
            if(!isEmptyOrNull(end)){
                if(str.endsWith(end)){
                    str = str.substring(0, str.length() - end.length());
                    retry = true;
                }
            }
        }
        return str;
    }
    public static String stackTraceOf(Exception e) {
    	StringWriter sw = new StringWriter();
    	e.printStackTrace(new PrintWriter(sw));
    	return sw.toString();
    }
    public static String combine(String first, String second, String delimeter){
        if(first == null && second == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if(first != null){
            sb.append(first);
        }
        if(!isEmptyOrNull(first) && !isEmptyOrNull(second)){
            sb.append(delimeter);
        }
        if(second != null){
            sb.append(second);
        }
        return sb.toString();
    }

    public static String ensureSeperator(String path1, String path2){
        if(path1 == null || !path1.startsWith("/")){
            path1 = "/" + path1;
        }
        if(path2 == null || !path2.startsWith("/")){
            path2 = "/" + path2;
        }
        if(path1.endsWith("/")){
            return path1 + path2.substring(1);
        }
        return path1 + path2;
    }
}
