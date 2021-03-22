/**
 * 
 */
package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "nickname" }, doNotUseGetters = true, callSuper = false)
public class AuthUserDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "nickname {error.general.isRequired}")
	private String nickname;

	@NotNull(message = "name  {error.general.isRequired}")
	private String name;

	@NotNull(message = "lastName  {error.general.isRequired}")
	private String lastName;

	@NotNull(message = "email  {error.general.isRequired}")
	@Email(message = "{error.general.invalidEmail}")
	private String email;

	@NotNull(message = "authUserRoleList  {error.general.isRequired}")
	private List<AuthUserRoleDto> authUserRoleList;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private boolean locked;

	private boolean enabled;

	private String phone;

	public static final Function<AuthUser, AuthUserDto> CONVERTER_DTO = (AuthUser t) -> {
		AuthUserDto dto = new AuthUserDto();
		dto.setNickname(t.getNickname());
		dto.setName(t.getName());
		dto.setLastName(t.getLastName());
		dto.setEmail(t.getEmail());
		dto.setEnabled(t.isEnabled());
		dto.setCreated(t.getCreated());
		dto.setLastModified(t.getLastModified());
		dto.setPhone(t.getPhone());

		if (t.getAuthUserRoleList() != null && !t.getAuthUserRoleList().isEmpty()) {
			dto.setAuthUserRoleList(
					t.getAuthUserRoleList().stream().map(AuthUserRoleDto.CONVERTER_DTO).collect(Collectors.toList()));

		}

		return (AuthUserDto) BaseDto.CONVERTER_DTO.apply(t, dto);
	};

	public static final Function<AuthUserDto, AuthUser> CONVERTER_ENTITY = (AuthUserDto t) -> {
		AuthUser domain = new AuthUser();
		domain.setNickname(t.getNickname());
		domain.setName(t.getName());
		domain.setLastName(t.getLastName());
		domain.setEmail(t.getEmail());
		domain.setPassword(t.getPassword());
		domain.setEnabled(t.isEnabled());
		domain.setPhone(t.getPhone());

		if (t.getAuthUserRoleList() != null && !t.getAuthUserRoleList().isEmpty()) {
			domain.setAuthUserRoleList(t.getAuthUserRoleList().stream().map(AuthUserRoleDto.CONVERTER_ENTITY)
					.collect(Collectors.toList()));
			domain.getAuthUserRoleList().forEach(authUserRole -> authUserRole.setAuthUser(domain));
		}

		return domain;
	};

}