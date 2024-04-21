package com.biddflux.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
