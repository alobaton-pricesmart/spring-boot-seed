package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthPermission;
import com.co.app.commons.domain.AuthRole;
import com.co.app.commons.domain.AuthRolePermission;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "authRolePermissionPk" }, doNotUseGetters = true, callSuper = false)
public class AuthRolePermissionDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "authRolePermission {error.general.isRequired}")
	private AuthRolePermissionPkDto authRolePermissionPk;

	private AuthRoleDto authRole;

	private AuthPermissionDto authPermission;

	public static final Function<AuthRolePermission, AuthRolePermissionDto> CONVERTER_DTO = (AuthRolePermission t) -> {
		AuthRolePermissionDto dto = new AuthRolePermissionDto();

		if (t.getAuthRolePermissionPk() != null) {
			dto.setAuthRolePermissionPk(AuthRolePermissionPkDto.CONVERTER_DTO.apply(t.getAuthRolePermissionPk()));
		}

		if (t.getAuthRole() != null) {
			AuthRole authRoleDomain = t.getAuthRole();
			AuthRoleDto authRoleDto = new AuthRoleDto();

			authRoleDto.setId(authRoleDomain.getId());
			authRoleDto.setDescription(authRoleDomain.getDescription());
			authRoleDto.setGroupId(authRoleDomain.getGroupId());
			authRoleDto.setParentId(authRoleDomain.getParentId());

			dto.setAuthRole(authRoleDto);
		}

		if (t.getAuthPermission() != null) {
			AuthPermission authPermissionDomain = t.getAuthPermission();
			AuthPermissionDto authPermissionDto = new AuthPermissionDto();

			authPermissionDto.setId(authPermissionDomain.getId());
			authPermissionDto.setDescription(authPermissionDomain.getDescription());

			dto.setAuthPermission(authPermissionDto);
		}

		return (AuthRolePermissionDto) BaseDto.CONVERTER_DTO.apply(t, dto);
	};

	public static final Function<AuthRolePermissionDto, AuthRolePermission> CONVERTER_ENTITY = (
			AuthRolePermissionDto t) -> {
		AuthRolePermission domain = new AuthRolePermission();

		if (t.getAuthRolePermissionPk() != null) {
			domain.setAuthRolePermissionPk(AuthRolePermissionPkDto.CONVERTER_ENTITY.apply(t.getAuthRolePermissionPk()));
		}

		if (t.getAuthRole() != null) {
			AuthRoleDto authRoleDto = t.getAuthRole();
			AuthRole authRoleDomain = new AuthRole();

			authRoleDomain.setId(authRoleDto.getId());

			domain.setAuthRole(authRoleDomain);
		}

		if (t.getAuthPermission() != null) {
			AuthPermissionDto authPermissionDto = t.getAuthPermission();
			AuthPermission authPermissionDomain = new AuthPermission();

			authPermissionDomain.setId(authPermissionDto.getId());

			domain.setAuthPermission(authPermissionDomain);
		}

		return domain;
	};
}
