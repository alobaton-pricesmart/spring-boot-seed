/**
 * 
 */
package com.alobaton.api.auth.services.encoder.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.NotNull;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alobaton.api.auth.services.encoder.HashEncoder;

/**
 * @author alobaton
 *
 */
@Service
public class HashEncoderImpl implements HashEncoder {

	private static final Log LOGGER = LogFactory.getLog(HashEncoderImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alobaton.api.auth.services.HashEncoder#encode(java.lang.String)
	 */
	@Override
	public String encode(@NotNull String message) {
		return encode(message, MessageDigestAlgorithms.SHA_256);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alobaton.api.auth.services.HashEncoder#encode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String encode(@NotNull String message, @NotNull String algorithm) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
			throw new IllegalStateException(algorithm + " algorithm not available. Fatal (should be in the JDK).");
		}

		try {
			byte[] bytes = digest.digest(message.getBytes(StandardCharsets.UTF_8.name()));
			return String.format("%032x", new Object[] { new BigInteger(1, bytes) });
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			throw new IllegalStateException("UTF-8 encoding not available. Fatal (should be in the JDK).");
		}
	}

}
