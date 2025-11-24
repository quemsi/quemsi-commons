package com.quemsi.commons.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileNameUtil {
    private Map<String, String> extensionTypeMap = new HashMap<>();
	
    public FileNameUtil(){
        extensionTypeMap.put("xls", "application/vnd.ms-excel");
		extensionTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		extensionTypeMap.put("xml", "text/xml");
		extensionTypeMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
		extensionTypeMap.put("csv", "text/plain");
		extensionTypeMap.put("tmpl", "text/plain");
		extensionTypeMap.put("sql", "text/sql");
		extensionTypeMap.put("pdf", "application/pdf");
		extensionTypeMap.put("php", "application/x-httpd-php");
		extensionTypeMap.put("jpg", "image/jpeg");
		extensionTypeMap.put("png", "image/png");
		extensionTypeMap.put("gif", "image/gif");
		extensionTypeMap.put("bmp", "image/bmp");
		extensionTypeMap.put("txt", "text/plain");
		extensionTypeMap.put("doc", "application/msword");
		extensionTypeMap.put("js", "text/js");
		extensionTypeMap.put("swf", "application/x-shockwave-flash");
		extensionTypeMap.put("mp3", "audio/mpeg");
		extensionTypeMap.put("zip", "application/zip");
		extensionTypeMap.put("rar", "application/rar");
		extensionTypeMap.put("tar", "application/tar");
		extensionTypeMap.put("gz", "application/gz");
		extensionTypeMap.put("arj", "application/arj");
		extensionTypeMap.put("cab", "application/cab");
		extensionTypeMap.put("html", "text/html");
		extensionTypeMap.put("htm", "text/html");
		extensionTypeMap.put("json", "application/json");
		extensionTypeMap.put("default", "application/octet-stream");
    }
	public String getFileType(String fileName) {
		String extension = getFileExtension(fileName);
		return extensionTypeMap.containsKey(extension)?extensionTypeMap.get(extension):extensionTypeMap.get("default");
	}
	public String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if(index == -1){
            return null;
        }
		return fileName.substring(index + 1);
	}

    public String versionedFileName(String name, Long version) {
        String prefix = name;
        String suffix = "";
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex > -1) {
            prefix = name.substring(0, dotIndex);
            suffix = name.substring(dotIndex);
        }
        return new StringBuilder(prefix).append("-").append(version).append(suffix).toString();
    }

	public String renameFileName(String absolutePath, String name){
		String dir = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator) + 1);
		return dir + name;
	}
}
