package com.co.app.commons.utils;

public class StringUtil {

	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String AT_SIGN = "@";
	public static final String COMMA = ",";
	public static final String SEMICOLON = ";";
	public static final String HYPHEN = "-";

	private StringUtil() {

	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static String trim(String s) {
		return isNullOrEmpty(s) ? EMPTY : s.trim();
	}

	public static String toString(Object s) {
		return s == null ? EMPTY : String.valueOf(s);
	}
}
