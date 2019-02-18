/**
 * 
 */
package com.alobaton.api.auth.dto;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
public class AuthClientDetailsDto {

	public static final Function<ClientDetails, AuthClientDetailsDto> CONVERTER = new Function<ClientDetails, AuthClientDetailsDto>() {
		@Override
		public AuthClientDetailsDto apply(ClientDetails t) {
			AuthClientDetailsDto dto = new AuthClientDetailsDto();

			dto.setClientId(t.getClientId());
			dto.setResourceIds(t.getResourceIds());
			dto.setSecretRequired(t.isSecretRequired());
			dto.setClientSecret(t.getClientSecret());
			dto.setScoped(t.isScoped());
			dto.setScope(t.getScope());
			dto.setAuthorizedGrantTypes(t.getAuthorizedGrantTypes());
			dto.setRegisteredRedirectUri(t.getRegisteredRedirectUri());
			dto.setAuthorities(t.getAuthorities().stream().map(authority -> authority.getAuthority())
					.collect(Collectors.<String>toSet()));
			dto.setAccessTokenValiditySeconds(t.getAccessTokenValiditySeconds());
			dto.setRefreshTokenValiditySeconds(t.getRefreshTokenValiditySeconds());
			dto.setAutoApprove(t.isAutoApprove(Strings.EMPTY));
			dto.setAdditionalInformation(t.getAdditionalInformation());

			return dto;
		}
	};

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
	 * @return the resourceIds
	 */
	public Set<String> getResourceIds() {
		return resourceIds;
	}

	/**
	 * @param resourceIds the resourceIds to set
	 */
	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	/**
	 * @return the secretRequired
	 */
	public boolean isSecretRequired() {
		return secretRequired;
	}

	/**
	 * @param secretRequired the secretRequired to set
	 */
	public void setSecretRequired(boolean secretRequired) {
		this.secretRequired = secretRequired;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the scoped
	 */
	public boolean isScoped() {
		return scoped;
	}

	/**
	 * @param scoped the scoped to set
	 */
	public void setScoped(boolean scoped) {
		this.scoped = scoped;
	}

	/**
	 * @return the scope
	 */
	public Set<String> getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	/**
	 * @return the authorizedGrantTypes
	 */
	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	/**
	 * @param authorizedGrantTypes the authorizedGrantTypes to set
	 */
	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	/**
	 * @return the registeredRedirectUri
	 */
	public Set<String> getRegisteredRedirectUri() {
		return registeredRedirectUri;
	}

	/**
	 * @param registeredRedirectUri the registeredRedirectUri to set
	 */
	public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}

	/**
	 * @return the authorities
	 */
	public Set<String> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the accessTokenValiditySeconds
	 */
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	/**
	 * @param accessTokenValiditySeconds the accessTokenValiditySeconds to set
	 */
	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	/**
	 * @return the refreshTokenValiditySeconds
	 */
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	/**
	 * @param refreshTokenValiditySeconds the refreshTokenValiditySeconds to set
	 */
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

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
	 * @return the additionalInformation
	 */
	public Map<String, Object> getAdditionalInformation() {
		return additionalInformation;
	}

	/**
	 * @param additionalInformation the additionalInformation to set
	 */
	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

}
