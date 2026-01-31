package com.quemsi.commons.util;

import lombok.Getter;

public class LogMessage {
    @Getter
    private String level;
    private String[] formats;
    private Object[] args;
    
    // Factory methods without format parameter, defaulting to null format
    public static LogMessage info(Object... args) {
        return new LogMessage("INFO", null, args);
    }

    public static LogMessage warn(Object... args) {
        return new LogMessage("WARN", null, args);
    }

    public static LogMessage error(Object... args) {
        return new LogMessage("ERROR", null, args);
    }

    public static LogMessage debug(Object... args) {
        return new LogMessage("DEBUG", null, args);
    }
    public static LogMessage info(String format, Object... args) {
        return new LogMessage("INFO", format, args);
    }
    public static LogMessage warn(String format, Object... args) {
        return new LogMessage("WARN", format, args);
    }
    public static LogMessage error(String format, Object... args) {
        return new LogMessage("ERROR", format, args);
    }
    public static LogMessage debug(String format, Object... args) {
        return new LogMessage("DEBUG", format, args);
    }
    
    public LogMessage(String level, String format, Object... args) {
        this.level = level;
        if(format == null) {
            this.formats = new String[0];
        } else {
            this.formats = format.split("\\{\\}");
        }
        this.args = args;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int max = Math.max(formats.length, args.length);
        for(int i = 0; i < max; i++) {
            if(i < formats.length) {
                sb.append(formats[i]);
            }
            if(i < args.length) {
                sb.append(args[i]);
            }
        }
        return sb.toString();
    }
}
