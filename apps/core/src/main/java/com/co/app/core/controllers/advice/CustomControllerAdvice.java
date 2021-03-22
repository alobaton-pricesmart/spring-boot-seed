/**
 * 
 */
package com.co.app.core.controllers.advice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.co.app.commons.dto.ExceptionMessageDto;
import com.co.app.commons.exception.ApiException;
import com.co.app.commons.exception.CustomDuplicateKeyException;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.message.service.MessageService;

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
	public @ResponseBody ResponseEntity<ExceptionMessageDto> duplicateKeyExceptionHandler(
			CustomDuplicateKeyException ex) {
		ExceptionMessageDto error = new ExceptionMessageDto();
		error.setMessage(ex.getCode() == null ? ex.getCode()
				: messageService.getMessage(ex.getCode(), translateArgs(ex.getArgs())));
		error.setError(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(RegisterNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseEntity<ExceptionMessageDto> registerNotFoundExceptionHandler(
			RegisterNotFoundException ex) {
		ExceptionMessageDto error = new ExceptionMessageDto();
		error.setMessage(ex.getCode() == null ? ex.getCode()
				: messageService.getMessage(ex.getCode(), translateArgs(ex.getArgs())));
		error.setError(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<ExceptionMessageDto> apiExceptionHandler(ApiException ex) {
		ExceptionMessageDto error = new ExceptionMessageDto();
		error.setMessage(ex.getCode() == null ? ex.getCode()
				: messageService.getMessage(ex.getCode(), translateArgs(ex.getArgs())));
		error.setError(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	private String[] translateArgs(String... args) {
		ArrayList<String> argsList = new ArrayList<>();
		for (String arg : args) {
			try {
				arg = messageService.getMessage(arg);
				argsList.add(arg);
			} catch (Exception e) {
				argsList.add(arg);
			}
		}
		return argsList.toArray(args);
	}
}
