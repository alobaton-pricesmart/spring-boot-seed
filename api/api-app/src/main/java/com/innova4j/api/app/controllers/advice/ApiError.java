/**
 * 
 */
package com.innova4j.api.app.controllers.advice;

import java.util.HashSet;
import java.util.Set;

/**
 * @author alobaton
 *
 */
public class ApiError {
	private String message;
	private Set<String> errors;
	
	public ApiError() {
		this.errors = new HashSet<>();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errors
	 */
	public Set<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Set<String> errors) {
		this.errors = errors;
	}

	/**
	 * 
	 * @param error The error to add
	 */
	public void addError(String error) {
		this.errors.add(error);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiError [message=" + message + ", errors=" + errors + "]";
	}

}
