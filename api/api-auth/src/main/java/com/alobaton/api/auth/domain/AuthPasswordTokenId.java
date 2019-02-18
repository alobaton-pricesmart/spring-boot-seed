/**
 * 
 */
package com.alobaton.api.auth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author alobaton
 *
 */
@Embeddable
public class AuthPasswordTokenId implements Serializable {

	private static final long serialVersionUID = 3076852464331727336L;

	@NotNull
	@Column(name = "nickname")
	private String nickname;

	@NotNull
	@Column(name = "token")
	private String token;

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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthPasswordTokenId [nickname=" + nickname + ", token=" + token + "]";
	}
}
