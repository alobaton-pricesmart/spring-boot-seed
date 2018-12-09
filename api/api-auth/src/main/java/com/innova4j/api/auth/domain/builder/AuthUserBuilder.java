/**
 * 
 */
package com.innova4j.api.auth.domain.builder;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.innova4j.api.auth.domain.AuthUser;

/**
 * @author innova4j-team
 *
 */
public class AuthUserBuilder {

	private String nickname;
	private String name;
	private String lastName;
	private String email;
	private Set<String> roles;
	private String password;
	private boolean locked;
	private boolean enabled;

	/**
	 * @param nickname the nickname to set
	 */
	public AuthUserBuilder nickname(@NotNull String nickname) {
		this.nickname = nickname;
		return this;
	}

	/**
	 * @param name the name to set
	 */
	public AuthUserBuilder name(@NotNull String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public AuthUserBuilder lastName(@NotNull String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * @param email the email to set
	 */
	public AuthUserBuilder email(@NotNull String email) {
		this.email = email;
		return this;
	}

	/**
	 * @param roles the roles to set
	 */
	public AuthUserBuilder roles(@NotNull Set<String> roles) {
		this.roles = roles;
		return this;
	}

	/**
	 * @param password the password to set
	 */
	public AuthUserBuilder password(@NotNull String password) {
		this.password = password;
		return this;
	}

	/**
	 * @param locked the locked to set
	 */
	public AuthUserBuilder locked(boolean locked) {
		this.locked = locked;
		return this;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public AuthUserBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public AuthUser build() {
		AuthUser user = new AuthUser();
		user.setNickname(nickname);
		user.setName(name);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setRoles(roles);
		user.setPassword(password);
		user.setLocked(locked);
		user.setEnabled(enabled);

		return user;
	}

}
