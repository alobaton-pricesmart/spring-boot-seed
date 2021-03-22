/**
 * 
 */
package com.co.app.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netflix.hystrix.exception.ExceptionNotWrappedByHystrix;

/**
 * @author alobaton
 * @param <T>
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegisterNotFoundException extends ApiException implements ExceptionNotWrappedByHystrix {

	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException(String code) {
		super(code);
	}

	public RegisterNotFoundException(String code, String... args) {
		super(code, args);
	}
}
