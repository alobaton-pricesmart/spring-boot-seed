/**
 * 
 */
package com.innova4j.api.auth.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author innova4j-team
 *
 */
@Entity
@Table(name = "auth_refresh_token")
public class AuthRefreshToken {

	@EmbeddedId
	private AuthRefreshTokenId id;
	@NotNull
	private OAuth2RefreshToken token;
	@NotNull
	private String authentication;

	/**
	 * @return the id
	 */
	public AuthRefreshTokenId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(AuthRefreshTokenId id) {
		this.id = id;
	}

	/**
	 * @return the token
	 */
	public OAuth2RefreshToken getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(OAuth2RefreshToken token) {
		this.token = token;
	}

	/**
	 * @return the authentication
	 */
	public OAuth2Authentication getAuthentication() {
		try {
			return OAuth2AuthenticationConverter.deserialize(authentication);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param authentication the authentication to set
	 */
	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = OAuth2AuthenticationConverter.serialize(authentication);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthRefreshToken [id=" + id + ", token=" + token + ", authentication=" + authentication + "]";
	}

}
