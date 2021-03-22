package com.co.app.commons.dto;

import java.util.function.Function;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "email" }, doNotUseGetters = true, callSuper = false)
public class AuthUserRegisterDto {
	@NotNull(message = "name  {error.general.isRequired}")
	private String name;
	@NotNull(message = "lastName  {error.general.isRequired}")
	private String lastName;
	@NotNull(message = "email  {error.general.isRequired}")
	@Email(message = "{error.general.invalidEmail}")
	private String email;
	@NotNull(message = "emailConfirm  {error.general.isRequired}")
	@Email(message = "{error.general.invalidEmail}")
	private String emailConfirm;
	@NotNull(message = "password  {error.general.isRequired}")
	private String password;
	@NotNull(message = "confirmPassword  {error.general.isRequired}")
	private String confirmPassword;
	private Object token;

	public static final Function<AuthUser, AuthUserRegisterDto> CONVERTER_DTO = (AuthUser t) -> {
		AuthUserRegisterDto dto = new AuthUserRegisterDto();
		dto.setName(t.getName());
		dto.setLastName(t.getLastName());
		dto.setEmail(t.getEmail());

		return dto;
	};

	public static final Function<AuthUserRegisterDto, AuthUser> CONVERTER_ENTITY = (AuthUserRegisterDto t) -> {
		AuthUser domain = new AuthUser();
		domain.setName(t.getName());
		domain.setLastName(t.getLastName());
		domain.setEmail(t.getEmail());
		domain.setPassword(t.getPassword());
		return domain;
	};
}
