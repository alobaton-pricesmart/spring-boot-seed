/**
 * 
 */
package com.co.app.auth.services.encoder.impl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.co.app.auth.services.encoder.CodeGenerator;
import com.co.app.auth.services.encoder.CodeType;

/**
 * @author alobaton
 *
 */
@Service
public class CodeGeneratorImpl implements CodeGenerator {

	private static final int NUMERIC_SIZE = 10;
	private static final int ALPHA_SIZE = 26;
	private static final int ALPHANUMERIC_SIZE = NUMERIC_SIZE + ALPHA_SIZE;

	private static final char[] NUMERIC_SYMBOLS = new char[NUMERIC_SIZE];
	private static final char[] ALPHANUMERIC_SYMBOLS = new char[ALPHANUMERIC_SIZE];

	static {
		for (int i = 0; i < NUMERIC_SIZE; i++) {
			NUMERIC_SYMBOLS[i] = (char) ('0' + i);
			ALPHANUMERIC_SYMBOLS[i] = (char) ('0' + i);
		}
		for (int i = NUMERIC_SIZE; i < ALPHANUMERIC_SIZE; i++) {
			ALPHANUMERIC_SYMBOLS[i] = (char) ('a' + i - NUMERIC_SIZE);
		}
	}

	private SecureRandom random;

	public CodeGeneratorImpl() {
		this.random = new SecureRandom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.co.app.auth.services.CodeGenerator#generateCode(com.innova4j.stsp.
	 * auth.services.CodeType, int)
	 */
	@Override
	public String generateCode(CodeType type, int length) {
		char[] buf = new char[6];

		char[] symbols;
		switch (type) {
		case NUMERIC:
			symbols = NUMERIC_SYMBOLS;
			break;
		case ALPHANUMERIC:
		default:
			symbols = ALPHANUMERIC_SYMBOLS;
			break;
		}

		for (int i = 0; i < buf.length; i++) {
			buf[i] = symbols[random.nextInt(symbols.length)];
		}

		return new String(buf).toUpperCase();
	}

}
