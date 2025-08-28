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
}
