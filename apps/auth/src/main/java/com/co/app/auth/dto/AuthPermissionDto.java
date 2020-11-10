/**
 * 
 */
package com.co.app.auth.dto;

import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.auth.domain.AuthPermission;
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
public class AuthPermissionDto extends BaseDto {

	@NotNull
	private String id;

	@NotNull
	private Map<String, String> description;

	@NotNull
	private AuthClientDetailsDto client;

	public static final Function<AuthPermission, AuthPermissionDto> CONVERTER = (AuthPermission t) -> {
		AuthPermissionDto dto = new AuthPermissionDto();

		dto.setId(t.getId());
		dto.setDescription(t.getDescription());
		dto.setClient(AuthClientDetailsDto.CONVERTER.apply(t.getClient()));

		dto.setCreated(t.getCreated());
		dto.setLastModified(t.getLastModified());

		return dto;
	};

}
