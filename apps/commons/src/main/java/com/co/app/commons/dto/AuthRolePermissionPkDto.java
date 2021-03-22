package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthRolePermissionPk;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "roleId", "permissionId" }, doNotUseGetters = true, callSuper = false)
public class AuthRolePermissionPkDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "roleId {error.general.isRequired}")
	private String roleId;

	@NotNull(message = "permissionId {error.general.isRequired}")
	private String permissionId;

	public static final Function<AuthRolePermissionPk, AuthRolePermissionPkDto> CONVERTER_DTO = (
			AuthRolePermissionPk t) -> {
		AuthRolePermissionPkDto dto = new AuthRolePermissionPkDto();

		dto.setPermissionId(t.getPermissionId());
		dto.setRoleId(t.getRoleId());

		return dto;
	};

	public static final Function<AuthRolePermissionPkDto, AuthRolePermissionPk> CONVERTER_ENTITY = (
			AuthRolePermissionPkDto t) -> {
		AuthRolePermissionPk domain = new AuthRolePermissionPk();

		domain.setPermissionId(t.getPermissionId());
		domain.setRoleId(t.getRoleId());

		return domain;
	};

}
