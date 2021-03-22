/**
 * 
 */
package com.co.app.commons.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "password" }, doNotUseGetters = true, callSuper = false)
public class AuthPasswordDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "password {error.general.isRequired}")
	private String password;

}