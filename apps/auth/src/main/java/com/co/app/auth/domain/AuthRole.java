/**
 * 
 */
package com.co.app.auth.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
			domain.setDescription(t.getDescription());
			domain.setGroupId(t.getGroupId());
			domain.setParentId(t.getParentId());
			domain.setPermissions(t.getPermissions().stream().map(AuthPermission.CONVERTER)
					.collect(Collectors.<AuthPermission>toList()));

			return domain;
		}
	};

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

	// LDAP groupId mapping.
	@Column(name = "group_id")
	private String groupId;

	@NotNull
	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description;

	@Column(name = "parent_id")
	private String parentId;

	@Type(type = "json")
	@OneToMany(mappedBy = "auth_role", cascade = CascadeType.ALL)
	@JoinColumn(name = "permissions", columnDefinition = "json")
	private List<AuthPermission> permissions;

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
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public List<AuthPermission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<AuthPermission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "AuthRole [id=" + id + ", groupId=" + groupId + ", description=" + description + ", parentId=" + parentId
				+ ", permissions=" + permissions + ", created=" + created + ", lastModified=" + lastModified + "]";
	}

}
