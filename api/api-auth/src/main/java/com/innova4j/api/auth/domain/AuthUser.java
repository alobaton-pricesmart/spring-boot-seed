/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.innova4j.api.commons.domain.BaseDomain;

/**
 * @author innova4j-team
 *
 */
@Entity
@Table(name = "auth_user")
public class AuthUser extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 8764457507861330729L;

	public static final String NICKNAME = "nickname";

	@Id
	@NotNull
	private String nickname;
	@NotNull
	private String name;
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private Set<String> roles;
	@NotNull
	private String password;
	private boolean locked;
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
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
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

}
