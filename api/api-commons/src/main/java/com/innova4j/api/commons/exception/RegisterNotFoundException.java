/**
 * 
 */
package com.innova4j.api.commons.exception;

import javax.validation.constraints.NotNull;

/**
 * @author alobaton
 * @param <T>
 *
 */
public class RegisterNotFoundException extends ApiException {
	private static final long serialVersionUID = -7500322046753778196L;

	private static final String CODE = "exception.registerNotFoud";

	public <T> RegisterNotFoundException(@NotNull Class<T> clazz, @NotNull String key, @NotNull String id) {
		super(CODE, new String[] { clazz.getName(), key, id },
				String.format("Could not find %s '%s'", clazz.getName(), id));
	}
}
