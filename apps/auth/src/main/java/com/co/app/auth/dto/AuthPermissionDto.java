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

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
public class AuthPermissionDto extends BaseDto {

	public static final Function<AuthPermission, AuthPermissionDto> CONVERTER = new Function<AuthPermission, AuthPermissionDto>() {
		@Override
		public AuthPermissionDto apply(AuthPermission t) {
			AuthPermissionDto dto = new AuthPermissionDto();

			dto.setId(t.getId());
			dto.setDescription(t.getDescription());
			dto.setClient(AuthClientDetailsDto.CONVERTER.apply(t.getClient()));

			dto.setCreated(t.getCreated());
			dto.setLastModified(t.getLastModified());

			return dto;
		}
	};

	@NotNull
	private String id;

	@NotNull
	private Map<String, String> description;

	@NotNull
	private AuthClientDetailsDto client;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public Map<String, String> getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(Map<String, String> description) {
		this.description = description;
	}

	/**
	 * @return the client
	 */
	public AuthClientDetailsDto getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(AuthClientDetailsDto client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "AuthPermissionsDto [id=" + id + ", description=" + description + ", client=" + client + ", created="
				+ created + ", lastModified=" + lastModified + "]";
	}

}
