/**
 * 
 */
package com.co.app.auth.dto;

import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.co.app.commons.dto.BaseDto;
import com.co.app.auth.domain.AuthUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * 
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
public class AuthUserDto extends BaseDto {

	public static final Function<AuthUser, AuthUserDto> CONVERTER = new Function<AuthUser, AuthUserDto>() {
		@Override
		public AuthUserDto apply(AuthUser t) {
			AuthUserDto dto = new AuthUserDto();
			dto.setNickname(t.getNickname());
			dto.setName(t.getName());
			dto.setLastName(t.getLastName());
			dto.setEmail(t.getEmail());
			dto.setRoles(t.getRoles());
			dto.setEnabled(t.isEnabled());
			dto.setCreated(t.getCreated());
			dto.setLastModified(t.getLastModified());

			return dto;
		}
	};

	@NotNull
	private String nickname;
	@NotNull
	private String name;
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private List<String> roles;
	@JsonProperty(access = Access.WRITE_ONLY)
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
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
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
}