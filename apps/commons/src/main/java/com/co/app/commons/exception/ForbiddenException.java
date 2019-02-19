package com.co.app.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author alobaton
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = -4224380767468586105L;

	/**
	 * 
	 * @param message
	 */
	public <C> ForbiddenException(String message) {
		super(message);
	}
}