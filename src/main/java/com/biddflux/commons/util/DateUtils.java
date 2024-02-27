package com.biddflux.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter TAG_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TAG_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    public String getDateString(LocalDateTime now){
        return TAG_DATE_FORMAT.format(now);
    }

    public String getTimeString(LocalDateTime now){
        return TAG_TIME_FORMAT.format(now);
    }
}
