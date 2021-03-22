/**
 * 
 */
package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthPermission;
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
public class AuthPermissionDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id {error.general.isRequired}")
	private String id;

	@NotNull(message = "description {error.general.isRequired}")
	private Map<String, String> description;

	public static final Function<AuthPermission, AuthPermissionDto> CONVERTER_DTO = (AuthPermission t) -> {
		AuthPermissionDto dto = new AuthPermissionDto();

		dto.setId(t.getId());
		dto.setDescription(t.getDescription());

		return (AuthPermissionDto) BaseDto.CONVERTER_DTO.apply(t, dto);
	};

	public static final Function<AuthPermissionDto, AuthPermission> CONVERTER_ENTITY = (AuthPermissionDto t) -> {
		AuthPermission domain = new AuthPermission();

		domain.setId(t.getId());
		domain.setDescription(t.getDescription());

		return domain;
	};

}
