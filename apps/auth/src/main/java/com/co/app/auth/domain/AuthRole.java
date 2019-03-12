/**
 * 
 */
package com.co.app.auth.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.auth.dto.AuthRoleDto;
import com.co.app.commons.domain.BaseDomain;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_role")
public class AuthRole extends BaseDomain {

	public static final Function<AuthRoleDto, AuthRole> CONVERTER = new Function<AuthRoleDto, AuthRole>() {
		@Override
		public AuthRole apply(AuthRoleDto t) {
			AuthRole domain = new AuthRole();
			domain.setId(t.getId());
			domain.setName(t.getName());
			domain.setDescription(t.getDescription());
			domain.setGroup(t.getGroup());
			domain.setParentId(t.getParentId());
			domain.setPermissions(t.getPermissions());
			domain.setCreated(t.getCreated());
			domain.setLastModified(t.getLastModified());

			return domain;
		}
	};

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

	// LDAP group mapping.
	@Column(name = "group")
	private String group;

	@NotNull
	@Type(type = "json")
	@Column(name = "name", columnDefinition = "json")
	private Map<String, String> name;

	@NotNull
	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description;

	@Column(name = "parent_id")
	private String parentId;

	@Type(type = "json")
	@Column(name = "permissions", columnDefinition = "json")
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

	public Map<String, String> getName() {
		return name;
	}

	public void setName(Map<String, String> name) {
		this.name = name;
	}

	public Map<String, String> getDescription() {
		return description;
	}

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
		return "AuthRole [id=" + id + ", group=" + group + ", name=" + name + ", description=" + description
				+ ", parentId=" + parentId + ", permissions=" + permissions + ", created=" + created + ", lastModified="
				+ lastModified + "]";
	}

}
