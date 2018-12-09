/**
 * 
 */
package com.innova4j.api.auth.services.encoder;

/**
 * @author innova4j-team
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
