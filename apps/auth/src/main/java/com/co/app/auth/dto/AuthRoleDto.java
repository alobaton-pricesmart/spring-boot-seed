/**
 * 
 */
package com.co.app.auth.dto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.co.app.auth.domain.AuthRole;
import com.co.app.commons.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class AuthRoleDto extends BaseDto {

	@NotNull
	private String id;
	@NotNull
	private String groupId;
	@NotNull
	private Map<String, String> description;
	private String parentId;
	private List<AuthPermissionDto> permissions;

	public static final Function<AuthRole, AuthRoleDto> CONVERTER = (AuthRole t) -> {
		AuthRoleDto dto = new AuthRoleDto();
		dto.setId(t.getId());
		dto.setDescription(t.getDescription());
		dto.setGroupId(t.getGroupId());
		dto.setParentId(t.getParentId());
		dto.setPermissions(t.getPermissions().stream().map(AuthPermissionDto.CONVERTER)
				.collect(Collectors.<AuthPermissionDto>toList()));

		dto.setCreated(t.getCreated());
		dto.setLastModified(t.getLastModified());

		return dto;
	};

}
