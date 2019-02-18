/**
 * 
 */
package com.alobaton.api.commons.exception;

import javax.validation.constraints.NotNull;

/**
 * @author alobaton
 *
 */
public class CustomDuplicateKeyException extends ApiException {

	private static final long serialVersionUID = -8618808352593994489L;

	private static final String CODE = "exception.duplicateKey";

	public <T> CustomDuplicateKeyException(@NotNull Class<T> clazz, @NotNull String key, @NotNull String id) {
		super(CODE, new String[] { clazz.getName(), key, id },
				String.format("%s duplicate key %s.", clazz.getName(), id));
	}

}
