/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author innova4j-team
 *
 */
@Embeddable
public class AuthRefreshTokenId implements Serializable {

	private static final long serialVersionUID = 5822825564789229231L;

	@NotNull
	private String tokenId;
	@NotNull
	private String authenticationId;

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the authenticationId
	 */
	public String getAuthenticationId() {
		return authenticationId;
	}

	/**
	 * @param authenticationId the authenticationId to set
	 */
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthRefreshTokenId [tokenId=" + tokenId + ", authenticationId=" + authenticationId + "]";
	}

}
