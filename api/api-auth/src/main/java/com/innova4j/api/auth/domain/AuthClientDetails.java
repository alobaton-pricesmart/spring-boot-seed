/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.innova4j.api.commons.domain.BaseDomain;

/**
 * @author innova4j-team
 *
 */
@Entity
@Table(name = "auth_client_details")
public class AuthClientDetails extends BaseDomain implements ClientDetails {

	private static final long serialVersionUID = 808870501996988945L;

	public static final String CLIENT_ID = "clientId";

	@Id
	@NotNull
	private String clientId;
	@NotNull
	private Set<String> resourceIds;
	@NotNull
	private boolean secretRequired;
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String clientSecret;
	@NotNull
	private boolean scoped;
	@NotNull
	private Set<String> scope;
	@NotNull
	private Set<String> authorizedGrantTypes;
	@NotNull
	private Set<String> registeredRedirectUri;
	@NotNull
	private Set<String> authorities;
	@NotNull
	private Integer accessTokenValiditySeconds;
	@NotNull
	private Integer refreshTokenValiditySeconds;
	private boolean autoApprove;
	private Map<String, Object> additionalInformation;

	/**
	 * @return the autoApprove
	 */
	public boolean isAutoApprove() {
		return autoApprove;
	}

	/**
	 * @param autoApprove the autoApprove to set
	 */
	public void setAutoApprove(boolean autoApprove) {
		this.autoApprove = autoApprove;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @param resourceIds the resourceIds to set
	 */
	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	/**
	 * @param secretRequired the secretRequired to set
	 */
	public void setSecretRequired(boolean secretRequired) {
		this.secretRequired = secretRequired;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @param scoped the scoped to set
	 */
	public void setScoped(boolean scoped) {
		this.scoped = scoped;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	/**
	 * @param authorizedGrantTypes the authorizedGrantTypes to set
	 */
	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	/**
	 * @param registeredRedirectUri the registeredRedirectUri to set
	 */
	public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = AuthorityUtils.authorityListToSet(authorities);
	}

	/**
	 * @param accessTokenValiditySeconds the accessTokenValiditySeconds to set
	 */
	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	/**
	 * @param refreshTokenValiditySeconds the refreshTokenValiditySeconds to set
	 */
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	/**
	 * @param additionalInformation the additionalInformation to set
	 */
	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Override
	public String getClientId() {
		return this.clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		return this.resourceIds;
	}

	@Override
	public boolean isSecretRequired() {
		return this.secretRequired;
	}

	@Override
	public String getClientSecret() {
		return this.clientSecret;
	}

	@Override
	public boolean isScoped() {
		return this.scoped;
	}

	@Override
	public Set<String> getScope() {
		return this.scope;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return this.authorizedGrantTypes;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return this.registeredRedirectUri;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		String arr[] = new String[authorities.size()];
		return AuthorityUtils.createAuthorityList(authorities.toArray(arr));
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return this.accessTokenValiditySeconds;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return this.refreshTokenValiditySeconds;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return this.autoApprove;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return this.additionalInformation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthClientDetails [clientId=" + clientId + ", resourceIds=" + resourceIds + ", secretRequired="
				+ secretRequired + ", clientSecret=" + clientSecret + ", scoped=" + scoped + ", scope=" + scope
				+ ", authorizedGrantTypes=" + authorizedGrantTypes + ", registeredRedirectUri=" + registeredRedirectUri
				+ ", authorities=" + authorities + ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
				+ ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds + ", autoApprove=" + autoApprove
				+ ", additionalInformation=" + additionalInformation + ", created=" + created + ", lastModified="
				+ lastModified + "]";
	}

}
