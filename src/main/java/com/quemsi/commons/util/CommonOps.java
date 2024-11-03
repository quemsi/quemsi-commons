package com.quemsi.commons.util;

import java.math.BigDecimal;

public class CommonOps {
	private static final int ROUND_PRECISION = 2;
	
	private CommonOps() {}
	
	public static double round(double value) {
		return round(value, ROUND_PRECISION);
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public static <T> T orDefault(T o, T d){
		if(o == null) {
			return d;
		}
		return o;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SafeVarargs
	public static <T extends Comparable> T minOf(T... ops){
		T min = null;
		for(T o : ops) {
			if(o != null) {
				if(min == null) {
					min = o;
				} else {
					if(o.compareTo(min) < 0) {
						min = o;
					}
				}
			}
		}
		return min;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SafeVarargs
	public static <T extends Comparable> T maxOf(T... ops){
		T max = null;
		for(T o : ops) {
			if(o != null) {
				if(max == null) {
					max = o;
				} else {
					if(o.compareTo(max) > 0) {
						max = o;
					}
				}
			}
		}
		return max;
	}
	public static BigDecimal orZero(BigDecimal num) {
		return num!=null?num:BigDecimal.ZERO;
	}
	public static int compareToZero(BigDecimal num) {
		return orDefault(num, BigDecimal.ZERO).compareTo(BigDecimal.ZERO);
	}
	public static boolean equals(BigDecimal n1, BigDecimal n2) {
		if(n1 == n2) {
			return true;
		}
		return n1 != null && n2 != null && n1.compareTo(n2) == 0;
	}
	public static boolean equalsWithDefZero(BigDecimal n1, BigDecimal n2) {
		return orZero(n1).compareTo(orZero(n2)) == 0;
	}
	public static int compareToWithDefZero(BigDecimal n1, BigDecimal n2) {
		return orZero(n1).compareTo(orZero(n2));
	}
	public static String stringOf(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof String str){
			return str;
		}
		return obj.toString();
	}
	public static boolean valueOf(Boolean first, Boolean second, boolean fallback){
		if(first != null){
			return first.booleanValue();
		}
		if(second != null){
			return second.booleanValue();
		}
		return fallback;
	}
}