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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_permission")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" }, doNotUseGetters = true, callSuper = false)
public class AuthPermission extends BaseDomain {

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

	public static final Function<AuthPermissionDto, AuthPermission> CONVERTER = (AuthPermissionDto t) -> {
		AuthPermission domain = new AuthPermission();

		domain.setId(t.getId());
		domain.setDescription(t.getDescription());
		domain.setClient((AuthClientDetails) AuthClientDetails.CONVERTER.apply(t.getClient()));

		return domain;
	};

}
