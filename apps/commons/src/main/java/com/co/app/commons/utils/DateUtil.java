/**
 * 
 */
package com.co.app.commons.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author alobaton
 *
 */
public class DateUtil {
	private DateUtil() {

	}

	public static final String ZONE_ID = "America/Bogota";
	public static final String FECHA_PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String FECHA_PATTERN_DD_MM_YYYY_WITH_SLASH = "dd/MM/yyyy";
	public static final String FECHA_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FECHA_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String FECHA_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String FECHA_PATTERN_HHMMSS = "HHmmss";
	public static final String FECHA_PATTERN_HH_MM_SS = "HH:mm:ss";
	public static final String FECHA_PATTERN_DD_MM_YYYY_HH_MM_SS_A = "dd-MM-yyyy hh:mm:ss a";
	public static final String FECHA_PATTERN_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String FECHA_PATTERN_DD_MM_YYYY_T_HH_MM_SS = "dd-MM-yyyy'T'HH:mm:ss";
	public static final String FECHA_PATTERN_YYYY_MM_DD_T_HH_MM_SS_SSS_SS_S = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]";

	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
}