/**
 * 
 */
package com.innova4j.api.auth.domain;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.SerializationUtils;

/**
 * @author innova4j-team
 *
 */
public class OAuth2AuthenticationConverter {
	/**
	 * 
	 * @param authentication The OAuth2Authentication
	 * @return The encoded authentication.
	 */
	public static String serialize(OAuth2Authentication authentication) {
		byte[] bytes = SerializationUtils.serialize(authentication);
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * 
	 * @param encoded The encoded authentication.
	 * @return The OAuth2Authentication
	 */
	public static OAuth2Authentication deserialize(String encoded) {
		byte[] bytes = Base64.decodeBase64(encoded);
		return (OAuth2Authentication) SerializationUtils.deserialize(bytes);
	}

}