package com.co.app.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.netflix.hystrix.exception.ExceptionNotWrappedByHystrix;

/**
 * 
 * @author alobaton
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException implements ExceptionNotWrappedByHystrix {

	private static final long serialVersionUID = 1L;

}