/**
 * 
 */
package com.co.app.core.controllers.advice;

/**
 * @author alobaton
 *
 */
public class ApiError {

	private String timestamp;
	private int status;
	private String error;
	private String message;
	private String responseCode;
	private String description;
	private String path;
	private String traza;

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
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
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the traza
	 */
	public String getTraza() {
		return traza;
	}

	/**
	 * @param traza the traza to set
	 */
	public void setTraza(String traza) {
		this.traza = traza;
	}

	@Override
	public String toString() {
		return "ApiError [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message=" + message
				+ ", responseCode=" + responseCode + ", description=" + description + ", path=" + path + ", traza="
				+ traza + "]";
	}

}
