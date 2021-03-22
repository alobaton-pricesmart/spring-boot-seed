/**
 * 
 */
package com.co.app.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netflix.hystrix.exception.ExceptionNotWrappedByHystrix;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomDuplicateKeyException extends ApiException implements ExceptionNotWrappedByHystrix {

	private static final long serialVersionUID = 1L;

	public CustomDuplicateKeyException(String code) {
		super(code);
	}

	public CustomDuplicateKeyException(String code, String... args) {
		super(code, args);
	}

}
