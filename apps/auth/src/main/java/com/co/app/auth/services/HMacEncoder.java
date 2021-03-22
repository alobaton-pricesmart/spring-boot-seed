/**
 * 
 */
package com.co.app.auth.services;

/**
 * @author alobaton
 *
 */
public interface HMacEncoder {

	/**
	 * Encode HMac for a given secret, message and algorithm.
	 * 
	 * @param secret    The secret.
	 * @param message   The message.
	 * @param algorithm The algorithm.
	 * @return The HMac signature.
	 */
	String encode(String secret, String message, String algorithm);

}
