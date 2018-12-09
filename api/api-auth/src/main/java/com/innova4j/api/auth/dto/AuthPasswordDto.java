/**
 * 
 */
package com.innova4j.api.auth.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author innova4j-team
 *
 */
@JsonInclude(Include.NON_NULL)
public class AuthPasswordDto {

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	private String password;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}