/**
 * 
 */
package com.co.app.auth.controllers.advice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public @ResponseBody ExceptionMessageDto duplicateKeyExceptionHandler(CustomDuplicateKeyException ex) {
		ExceptionMessageDto error = new ExceptionMessageDto();
		error.setMessage(ex.getCode() == null ? ex.getCode()
				: messageService.getMessage(ex.getCode(), translateArgs(ex.getArgs())));
		error.setError(ex.getMessage());

		return error;
	}

	@ExceptionHandler(RegisterNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionMessageDto registerNotFoundExceptionHandler(RegisterNotFoundException ex) {
		ExceptionMessageDto error = new ExceptionMessageDto();
		error.setMessage(ex.getCode() == null ? ex.getCode()
				: messageService.getMessage(ex.getCode(), translateArgs(ex.getArgs())));
		error.setError(ex.getMessage());

		return error;
	}

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionMessageDto apiExceptionHandler(ApiException ex) {
		ExceptionMessageDto error = new ExceptionMessageDto();
		error.setMessage(ex.getCode() == null ? ex.getCode()
				: messageService.getMessage(ex.getCode(), translateArgs(ex.getArgs())));
		error.setError(ex.getMessage());

		return error;
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
