/**
 * 
 */
package com.co.app.auth.domain;

import java.util.Map;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.auth.dto.AuthPermissionDto;
import com.co.app.commons.domain.BaseDomain;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_permission")
public class AuthPermission extends BaseDomain {

	public static final Function<AuthPermissionDto, AuthPermission> CONVERTER = new Function<AuthPermissionDto, AuthPermission>() {
		@Override
		public AuthPermission apply(AuthPermissionDto t) {
			AuthPermission domain = new AuthPermission();

			domain.setId(t.getId());
			domain.setDescription(t.getDescription());
			domain.setClient((AuthClientDetails) AuthClientDetails.CONVERTER.apply(t.getClient()));

			return domain;
		}
	};

	@Id
	@NotNull
	@Column(name = "id")
	private String id;

	@Type(type = "json")
	@Column(name = "description", columnDefinition = "json")
	private Map<String, String> description;

	@NotNull
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private AuthClientDetails client;

	@NotNull
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private AuthRole role;

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
	public AuthClientDetails getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(AuthClientDetails client) {
		this.client = client;
	}

	/**
	 * @return the role
	 */
	public AuthRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(AuthRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AuthPermission [id=" + id + ", description=" + description + ", client=" + client + ", created="
				+ created + ", lastModified=" + lastModified + "]";
	}

}
