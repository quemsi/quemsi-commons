package com.biddflux.commons.util;

import java.util.function.Supplier;

public class DelayedFormatter {
	private DelayedFormatter() {
	}
	public static Object toDelayedString(StringBuilder sb) {
		return new Object() {
			@Override
			public String toString() {
				return sb.toString();
			}
		};
	}
    public static Object toDelayedString(Supplier<String> supplier){
        return new Object(){
            @Override
            public String toString() {
                return supplier.get();
            }
        };
    }
}
