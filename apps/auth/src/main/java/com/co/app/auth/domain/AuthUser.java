/**
 * 
 */
package com.co.app.auth.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.co.app.auth.dto.AuthUserDto;
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
@Table(name = "auth_user")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "nickname" }, doNotUseGetters = true, callSuper = false)
public class AuthUser extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 8764457507861330729L;

	public static final String NICKNAME = "nickname";

	@Id
	@NotNull
	@Column(name = "nickname")
	private String nickname;

	@NotNull
	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Email
	@Column(name = "email")
	private String email;

	@NotNull
	@Type(type = "json")
	@Column(name = "roles", columnDefinition = "json")
	private List<String> roles;

	@NotNull
	@Column(name = "password")
	private String password;

	@Column(name = "locked")
	private boolean locked;

	@Column(name = "enabled")
	private boolean enabled;

	public void addRole(String role) {
		if (this.roles == null) {
			this.roles = new ArrayList<String>();
		}

		this.roles.add(role);
	}

	public static final Function<AuthUserDto, AuthUser> CONVERTER = (AuthUserDto t) -> {
		AuthUser domain = new AuthUser();
		domain.setNickname(t.getNickname());
		domain.setName(t.getName());
		domain.setLastName(t.getLastName());
		domain.setEmail(t.getEmail());
		domain.setPassword(t.getPassword());
		domain.setRoles(t.getRoles());
		domain.setEnabled(t.isEnabled());
		domain.setCreated(t.getCreated());
		domain.setLastModified(t.getLastModified());

		return domain;
	};

}
