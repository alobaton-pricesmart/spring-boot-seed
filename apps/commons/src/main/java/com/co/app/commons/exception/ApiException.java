/**
 * 
 */
package com.co.app.commons.exception;

/**
 * @author alobaton
 *
 */
public class ApiException extends RuntimeException {

	private String code;
	private String[] args;

	private static final long serialVersionUID = 3536029346705198789L;

	public ApiException(String code) {
		this.code = code;
	}

	public ApiException(String code, String[] args) {
		this.code = code;
		this.args = args;
	}

	public ApiException(String code, String[] args, String message) {
		super(message);
		this.code = code;
		this.args = args;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}
