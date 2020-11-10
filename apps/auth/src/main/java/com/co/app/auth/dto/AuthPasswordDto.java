/**
 * 
 */
package com.co.app.auth.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class AuthPasswordDto {

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	private String password;

}