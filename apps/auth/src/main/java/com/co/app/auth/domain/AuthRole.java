/**
 * 
 */
package com.co.app.auth.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.commons.domain.BaseDomain;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_role")
public class AuthRole extends BaseDomain {

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

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
		return "AuthRole [id=" + id + ", name=" + name + ", description=" + description + ", parentId=" + parentId
				+ ", permissions=" + permissions + "]";
	}

}
