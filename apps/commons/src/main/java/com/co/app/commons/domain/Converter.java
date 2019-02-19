/**
 * 
 */
package com.co.app.commons.domain;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.SerializationUtils;

/**
 * @author alobaton
 *
 */
public class Converter {

	public static <T> String serialize(T token) {
		byte[] bytes = SerializationUtils.serialize(token);
		return Base64.encodeBase64String(bytes);
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String encoded) {
		byte[] bytes = Base64.decodeBase64(encoded);
		return (T) SerializationUtils.deserialize(bytes);
	}

}
