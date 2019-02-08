/**
 * 
 */
package com.innova4j.api.message.service;

import java.util.Locale;

/**
 * @author alobaton
 *
 */
public interface MessageService {
	/**
	 * Get message for a key for default locale.
	 * 
	 * @param key The message key.
	 * @return
	 */
	String getMessage(String key);

	/**
	 * Get message for a key for default locale with args.
	 * 
	 * @param key The message key.
	 * @return
	 */
	String getMessage(String key, Object[] args);

	/**
	 * Get message for a key and locale.
	 * 
	 * @param key    The message key.
	 * @param locale The locale.
	 * @return
	 */
	String getMessage(String key, Locale locale);

	/**
	 * Get message for a key and locale with args.
	 * 
	 * @param key    The message key.
	 * @param locale The locale.
	 * @param args   The message args.
	 * @return
	 */
	String getMessage(String key, Locale locale, Object[] args);

}
