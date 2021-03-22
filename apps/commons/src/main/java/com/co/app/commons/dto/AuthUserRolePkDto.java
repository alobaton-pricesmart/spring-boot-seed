package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthUserRolePk;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "nickname", "roleId" }, doNotUseGetters = true, callSuper = false)
public class AuthUserRolePkDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "nickname {error.general.isRequired}")
	private String nickname;

	@NotNull(message = "roleId {error.general.isRequired}")
	private String roleId;

	public static final Function<AuthUserRolePk, AuthUserRolePkDto> CONVERTER_DTO = (AuthUserRolePk t) -> {
		AuthUserRolePkDto dto = new AuthUserRolePkDto();

		dto.setNickname(t.getNickname());
		dto.setRoleId(t.getRoleId());

		return dto;
	};

	public static final Function<AuthUserRolePkDto, AuthUserRolePk> CONVERTER_ENTITY = (AuthUserRolePkDto t) -> {
		AuthUserRolePk domain = new AuthUserRolePk();

		domain.setNickname(t.getNickname());
		domain.setRoleId(t.getRoleId());

		return domain;
	};
}
