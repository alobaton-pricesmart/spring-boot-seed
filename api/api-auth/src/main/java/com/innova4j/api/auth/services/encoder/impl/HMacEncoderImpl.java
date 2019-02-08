package com.innova4j.api.auth.services.encoder.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.innova4j.api.auth.services.encoder.HMacEncoder;

/**
 * 
 * @author alobaton
 *
 */
@Service
public class HMacEncoderImpl implements HMacEncoder {

	private static final Log LOGGER = LogFactory.getLog(HMacEncoderImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.innova4j.api.auth.services.HMacEncoder#calculateHMac(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String encode(@NotNull String secret, @NotNull String message, @NotNull String algorithm) {
		SecretKeySpec key;
		try {
			key = new SecretKeySpec((secret).getBytes(StandardCharsets.UTF_8.name()), algorithm);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			throw new IllegalStateException("UTF-8 encoding not available. Fatal (should be in the JDK).");
		}

		Mac messageAuthenticationCode;
		try {
			messageAuthenticationCode = Mac.getInstance(algorithm);
			messageAuthenticationCode.init(key);
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage());
			return null;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
			throw new IllegalStateException(algorithm + " algorithm not available. Fatal (should be in the JDK).");
		}

		byte[] bytes;
		try {
			bytes = messageAuthenticationCode.doFinal(message.getBytes(StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			throw new IllegalStateException("UTF-8 encoding not available. Fatal (should be in the JDK).");
		} catch (IllegalStateException e) {
			LOGGER.error(e.getMessage());
			return null;
		}

		String digest = toHexString(bytes);

		return digest;
	}

	/**
	 * To HEX String.
	 * 
	 * @param bytes The bytes.
	 * @return The HEX string.
	 */
	private static String toHexString(byte[] bytes) {
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();

		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return formatter.toString();
	}

}
