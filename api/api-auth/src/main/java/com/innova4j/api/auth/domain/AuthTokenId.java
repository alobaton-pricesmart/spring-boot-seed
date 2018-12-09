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
public class AuthTokenId implements Serializable {
	private static final long serialVersionUID = -2388030375397690113L;

	@NotNull
	private String nickname;
	@NotNull
	private String clientId;
	@NotNull
	private String tokenId;
	@NotNull
	private String authenticationId;
	@NotNull
	private String refreshTokenId;

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
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

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

	/**
	 * @return the refreshTokenId
	 */
	public String getRefreshTokenId() {
		return refreshTokenId;
	}

	/**
	 * @param refreshTokenId the refreshTokenId to set
	 */
	public void setRefreshTokenId(String refreshTokenId) {
		this.refreshTokenId = refreshTokenId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthTokenId [nickname=" + nickname + ", clientId=" + clientId + ", tokenId=" + tokenId
				+ ", authenticationId=" + authenticationId + ", refreshTokenId=" + refreshTokenId + "]";
	}

}
