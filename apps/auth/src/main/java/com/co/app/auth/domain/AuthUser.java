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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.Type;

import com.co.app.commons.domain.BaseDomain;
import com.co.app.auth.dto.AuthUserDto;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_user")
public class AuthUser extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 8764457507861330729L;

	public static final String NICKNAME = "nickname";

	public static final Function<AuthUserDto, AuthUser> CONVERTER = new Function<AuthUserDto, AuthUser>() {
		@Override
		public AuthUser apply(AuthUserDto t) {
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
		}
	};

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

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void addRole(String role) {
		if (this.roles == null) {
			this.roles = new ArrayList<String>();
		}

		this.roles.add(role);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthUser [nickname=" + nickname + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", roles=" + roles + ", password=" + password + ", locked=" + locked + ", enabled=" + enabled
				+ ", created=" + created + ", lastModified=" + lastModified + "]";
	}

	@Override
	public boolean equals(Object arg0) {
		AuthUser user = (AuthUser) arg0;

		return new EqualsBuilder().append(nickname, user.getNickname()).append(name, user.getName())
				.append(lastName, user.getLastName()).append(email, user.getEmail())
				.append(password, user.getPassword()).append(roles, user.getRoles()).append(locked, user.isLocked())
				.append(enabled, user.isEnabled()).append(created, user.getCreated())
				.append(lastModified, user.getLastModified()).build();
	}

}
