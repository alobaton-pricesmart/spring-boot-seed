/**
 * 
 */
package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "id" }, doNotUseGetters = true, callSuper = false)
public class AuthRoleDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id {error.general.isRequired}")
	private String id;

	@NotNull(message = "groupId {error.general.isRequired}")
	private String groupId;

	@NotNull(message = "description {error.general.isRequired}")
	private Map<String, String> description;

	private String parentId;

	private List<AuthRolePermissionDto> authRolePermissionList;

	public static final Function<AuthRole, AuthRoleDto> CONVERTER_DTO = (AuthRole t) -> {
		AuthRoleDto dto = new AuthRoleDto();

		dto.setId(t.getId());
		dto.setDescription(t.getDescription());
		dto.setGroupId(t.getGroupId());
		dto.setParentId(t.getParentId());

		if (t.getAuthRolePermissionList() != null && !t.getAuthRolePermissionList().isEmpty()) {
			dto.setAuthRolePermissionList(t.getAuthRolePermissionList().stream()
					.map(AuthRolePermissionDto.CONVERTER_DTO).collect(Collectors.toList()));
		}

		return (AuthRoleDto) BaseDto.CONVERTER_DTO.apply(t, dto);
	};

	public static final Function<AuthRoleDto, AuthRole> CONVERTER_ENTITY = (AuthRoleDto t) -> {
		AuthRole domain = new AuthRole();

		domain.setId(t.getId());
		domain.setDescription(t.getDescription());
		domain.setGroupId(t.getGroupId());
		domain.setParentId(t.getParentId());

		if (t.getAuthRolePermissionList() != null && !t.getAuthRolePermissionList().isEmpty()) {
			domain.setAuthRolePermissionList(t.getAuthRolePermissionList().stream()
					.map(AuthRolePermissionDto.CONVERTER_ENTITY).collect(Collectors.toList()));
			domain.getAuthRolePermissionList().forEach(authRolePermission -> authRolePermission.setAuthRole(domain));
		}

		return domain;
	};

}
