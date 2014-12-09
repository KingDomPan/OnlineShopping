package com.shopOL.util;

public class Type {

	public static boolean isInteger(String str) {
		str = str.trim();
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isDouble(String str) {
		str = str.trim();
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isLong(String str) {
		str = str.trim();
		try {
			Long.parseLong(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
