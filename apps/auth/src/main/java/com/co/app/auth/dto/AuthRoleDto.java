/**
 * 
 */
package com.co.app.auth.dto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.auth.domain.AuthRole;
import com.co.app.commons.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
public class AuthRoleDto extends BaseDto {

	public static final Function<AuthRole, AuthRoleDto> CONVERTER = new Function<AuthRole, AuthRoleDto>() {
		@Override
		public AuthRoleDto apply(AuthRole t) {
			AuthRoleDto dto = new AuthRoleDto();
			dto.setId(t.getId());
			dto.setName(t.getName());
			dto.setDescription(t.getDescription());
			dto.setGroup(t.getGroup());
			dto.setParentId(t.getParentId());
			dto.setPermissions(t.getPermissions());
			dto.setCreated(t.getCreated());
			dto.setLastModified(t.getLastModified());

			return dto;
		}
	};

	@NotNull
	private String id;
	private String group;
	@NotNull
	private Map<String, String> name;
	@NotNull
	private Map<String, String> description;
	private String parentId;
	private List<String> permissions;

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
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the name
	 */
	public Map<String, String> getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(Map<String, String> name) {
		this.name = name;
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
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the permissions
	 */
	public List<String> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthRoleDto [id=" + id + ", group=" + group + ", name=" + name + ", description=" + description
				+ ", parentId=" + parentId + ", permissions=" + permissions + ", created=" + created + ", lastModified="
				+ lastModified + "]";
	}

}
