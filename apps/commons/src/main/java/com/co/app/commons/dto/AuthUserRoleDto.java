package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthRole;
import com.co.app.commons.domain.AuthUserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "authUserRolePk" }, doNotUseGetters = true, callSuper = false)
public class AuthUserRoleDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "authUserRolePk {error.general.isRequired}")
	private AuthUserRolePkDto authUserRolePk;

	private AuthUserDto authUser;

	private AuthRoleDto authRole;

	public static final Function<AuthUserRole, AuthUserRoleDto> CONVERTER_DTO = (AuthUserRole t) -> {
		AuthUserRoleDto dto = new AuthUserRoleDto();

		dto.setAuthUserRolePk(AuthUserRolePkDto.CONVERTER_DTO.apply(t.getAuthUserRolePk()));

		if (t.getAuthRole() != null) {
			AuthRole authRoleEntity = t.getAuthRole();
			AuthRoleDto authRoleDto = new AuthRoleDto();

			authRoleDto.setId(authRoleEntity.getId());
			authRoleDto.setDescription(authRoleEntity.getDescription());
			authRoleDto.setGroupId(authRoleEntity.getGroupId());
			authRoleDto.setParentId(authRoleEntity.getParentId());

			dto.setAuthRole(authRoleDto);
		}

		return (AuthUserRoleDto) BaseDto.CONVERTER_DTO.apply(t, dto);
	};

	public static final Function<AuthUserRoleDto, AuthUserRole> CONVERTER_ENTITY = (AuthUserRoleDto t) -> {
		AuthUserRole domain = new AuthUserRole();

		domain.setAuthUserRolePk(AuthUserRolePkDto.CONVERTER_ENTITY.apply(t.getAuthUserRolePk()));

		if (t.getAuthRole() != null) {
			AuthRoleDto authRoleDto = t.getAuthRole();
			AuthRole authRoleEntity = new AuthRole();

			authRoleEntity.setId(authRoleDto.getId());
			authRoleEntity.setDescription(authRoleDto.getDescription());
			authRoleEntity.setGroupId(authRoleDto.getGroupId());
			authRoleEntity.setParentId(authRoleDto.getParentId());

			domain.setAuthRole(authRoleEntity);
		}

		return domain;
	};
}
