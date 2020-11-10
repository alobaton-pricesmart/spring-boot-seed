/**
 * 
 */
package com.co.app.auth.dto;

import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.co.app.auth.domain.AuthUser;
import com.co.app.commons.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class AuthUserDto extends BaseDto {

	@NotNull
	private String nickname;
	@NotNull
	private String name;
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private List<String> roles;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private boolean locked;
	private boolean enabled;

	public static final Function<AuthUser, AuthUserDto> CONVERTER = (AuthUser t) -> {
		AuthUserDto dto = new AuthUserDto();
		dto.setNickname(t.getNickname());
		dto.setName(t.getName());
		dto.setLastName(t.getLastName());
		dto.setEmail(t.getEmail());
		dto.setRoles(t.getRoles());
		dto.setEnabled(t.isEnabled());
		dto.setCreated(t.getCreated());
		dto.setLastModified(t.getLastModified());

		return dto;
	};
}