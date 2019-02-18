/**
 * 
 */
package com.alobaton.api.app.controllers.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alobaton.api.commons.exception.ApiException;
import com.alobaton.api.commons.exception.CustomDuplicateKeyException;
import com.alobaton.api.commons.exception.RegisterNotFoundException;
import com.alobaton.api.message.service.MessageService;

/**
 * @author alobaton
 *
 */
@ControllerAdvice
public class CustomControllerAdvice {

	@Autowired
	private MessageService messageService;

	@ExceptionHandler(CustomDuplicateKeyException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody ResponseEntity<ApiError> duplicateKeyExceptionHandler(CustomDuplicateKeyException ex) {
		ApiError error = new ApiError();
		error.setMessage(ex.getCode() == null ? ex.getCode() : messageService.getMessage(ex.getCode(), ex.getArgs()));
		error.addError(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(RegisterNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseEntity<ApiError> registerNotFoundExceptionHandler(RegisterNotFoundException ex) {
		ApiError error = new ApiError();
		error.setMessage(ex.getCode() == null ? ex.getCode() : messageService.getMessage(ex.getCode(), ex.getArgs()));
		error.addError(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<ApiError> apiExceptionHandler(ApiException ex) {
		ApiError error = new ApiError();
		error.setMessage(ex.getCode() == null ? ex.getCode() : messageService.getMessage(ex.getCode()));
		error.addError(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
