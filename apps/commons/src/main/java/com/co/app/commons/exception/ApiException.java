/**
 * 
 */
package com.co.app.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Getter
@Setter
@ToString
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	private String[] args;

	public ApiException(String code) {
		this.code = code;
	}

	public ApiException(String code, String... args) {
		this.code = code;
		this.args = args;
	}

}
