/**
 * 
 */
package com.co.app.auth.services;

/**
 * @author alobaton
 *
 */
public interface HashEncoder {
	/**
	 * Encode for a given message.
	 * 
	 * @param message The message.
	 * @return The HMac signature.
	 */
	String encode(String message);

	/**
	 * Encode for a given message and algorithm.
	 * 
	 * @param message   The message.
	 * @param algorithm The algorithm.
	 * @return The HMac signature.
	 */
	String encode(String message, String algorithm);
}
