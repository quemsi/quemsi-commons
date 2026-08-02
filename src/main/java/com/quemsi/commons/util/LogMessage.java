package com.quemsi.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import lombok.Getter;

public class LogMessage {
    @Getter
    private String level;
    private String[] formats;
    private Object[] args;
    @Getter
    private String messageId;
    @Getter
    private String cause;
    @Getter
    private String stackTrace;
    
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

    /**
     * Short human message in {@link #toString()}, with messageId/cause/stackTrace as structured fields.
     */
    public static LogMessage errorWithCause(String tag, Throwable throwable) {
        LogMessage message = new LogMessage("ERROR", "{}", tag);
        message.fillFromThrowable(throwable);
        return message;
    }
    
    public LogMessage(String level, String format, Object... args) {
        this.level = level;
        if(format == null) {
            this.formats = new String[0];
        } else {
            this.formats = format.split("\\{\\}");
        }
        Object[] messageArgs = args != null ? args : new Object[0];
        if (messageArgs.length > 0 && messageArgs[messageArgs.length - 1] instanceof Throwable throwable) {
            fillFromThrowable(throwable);
            messageArgs = Arrays.copyOf(messageArgs, messageArgs.length - 1);
        }
        this.args = messageArgs;
    }

    private void fillFromThrowable(Throwable throwable) {
        if (throwable == null) {
            return;
        }
        BaseRuntimeException bre = firstBaseRuntimeException(throwable);
        if (bre != null) {
            this.messageId = bre.getMessageId();
        }
        Throwable root = rootCause(throwable);
        String rootMessage = root != null ? root.getMessage() : null;
        if (rootMessage == null || rootMessage.isBlank()) {
            rootMessage = root != null ? root.getClass().getSimpleName() : throwable.getClass().getSimpleName();
        }
        this.cause = rootMessage;
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
    }

    private static BaseRuntimeException firstBaseRuntimeException(Throwable throwable) {
        Throwable current = throwable;
        while (current != null) {
            if (current instanceof BaseRuntimeException bre) {
                return bre;
            }
            current = current.getCause();
        }
        return null;
    }

    private static Throwable rootCause(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null && current.getCause() != current) {
            current = current.getCause();
        }
        return current;
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
