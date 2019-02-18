/**
 * 
 */
package com.alobaton.api.commons.utils.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author alobaton
 *
 */
public class DateUtil {
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
}