package com.quemsi.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SizeParser {
    private final static long KB_FACTOR = 1024;
    private final static long MB_FACTOR = 1024 * KB_FACTOR;
    private final static long GB_FACTOR = 1024 * MB_FACTOR;

    public static long parse(String val, String type) {
        if(val == null){
            return 0L;
        }
        double ret = Double.parseDouble(val);
        switch (type) {
            case "GB":
                return (long)(ret * GB_FACTOR);
            case "MB":
                return (long)(ret * MB_FACTOR);
            case "KB":
                return (long)(ret * KB_FACTOR);
        }
        return -1;
    }

    public static String toString(Long bytes){
        if(bytes == null){
            return null;
        }
        BigDecimal raw = null;
        String unit = null;
        if(bytes < MB_FACTOR){
            raw = BigDecimal.valueOf(bytes).divide(BigDecimal.valueOf(KB_FACTOR)).setScale(1, RoundingMode.HALF_EVEN);
            unit = " KB";
        } else if(bytes < GB_FACTOR){
            raw = BigDecimal.valueOf(bytes).divide(BigDecimal.valueOf(MB_FACTOR)).setScale(1, RoundingMode.HALF_EVEN);
            unit = " MB";
        } else {
            raw = BigDecimal.valueOf(bytes).divide(BigDecimal.valueOf(GB_FACTOR)).setScale(1, RoundingMode.HALF_EVEN);
            unit = " GB";
        }
        return new BigDecimal(raw.stripTrailingZeros().toPlainString()).toString() + unit;
    }
}
