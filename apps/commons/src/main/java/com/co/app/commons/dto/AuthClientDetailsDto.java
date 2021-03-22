/**
 * 
 */
package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.AuthClientDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "clientId" }, doNotUseGetters = true, callSuper = false)
public class AuthClientDetailsDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "clientId {error.general.isRequired}")
	private String clientId;
	@NotNull(message = "resourceIds {error.general.isRequired}")
	private Set<String> resourceIds;
	@NotNull(message = "secretRequired {error.general.isRequired}")
	private boolean secretRequired;
	@NotNull(message = "clientSecret {error.general.isRequired}")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String clientSecret;
	@NotNull(message = "scoped {error.general.isRequired}")
	private boolean scoped;
	@NotNull(message = "scope {error.general.isRequired}")
	private Set<String> scope;
	@NotNull(message = "authorizedGrantTypes {error.general.isRequired}")
	private Set<String> authorizedGrantTypes;
	@NotNull(message = "registeredRedirectUri {error.general.isRequired}")
	private Set<String> registeredRedirectUri;
	@NotNull(message = "authorities {error.general.isRequired}")
	private Set<String> authorities;
	@NotNull(message = "accessTokenValiditySeconds {error.general.isRequired}")
	private Integer accessTokenValiditySeconds;
	@NotNull(message = "refreshTokenValiditySeconds {error.general.isRequired}")
	private Integer refreshTokenValiditySeconds;
	private boolean autoApprove;
	private Map<String, Object> additionalInformation;

	public static final Function<AuthClientDetails, AuthClientDetailsDto> CONVERTER_DTO = (AuthClientDetails t) -> {
		AuthClientDetailsDto dto = new AuthClientDetailsDto();

		dto.setClientId(t.getClientId());
		dto.setResourceIds(t.getResourceIds());
		dto.setSecretRequired(t.isSecretRequired());
		dto.setClientSecret(t.getClientSecret());
		dto.setScoped(t.isScoped());
		dto.setScope(t.getScope());
		dto.setAuthorizedGrantTypes(t.getAuthorizedGrantTypes());
		dto.setRegisteredRedirectUri(t.getRegisteredRedirectUri());
		dto.setAuthorities(t.getAuthorities());
		dto.setAccessTokenValiditySeconds(t.getAccessTokenValiditySeconds());
		dto.setRefreshTokenValiditySeconds(t.getRefreshTokenValiditySeconds());
		dto.setAutoApprove(t.isAutoApprove());
		dto.setAdditionalInformation(t.getAdditionalInformation());

		return dto;
	};

	public static final Function<AuthClientDetailsDto, AuthClientDetails> CONVERTER_ENTITY = (
			AuthClientDetailsDto t) -> {
		AuthClientDetails domain = new AuthClientDetails();

		domain.setClientId(t.getClientId());
		domain.setResourceIds(t.getResourceIds());
		domain.setSecretRequired(t.isSecretRequired());
		domain.setClientSecret(t.getClientSecret());
		domain.setScoped(t.isScoped());
		domain.setScope(t.getScope());
		domain.setAuthorizedGrantTypes(t.getAuthorizedGrantTypes());
		domain.setRegisteredRedirectUri(t.getRegisteredRedirectUri());
		domain.setAuthorities(t.getAuthorities());
		domain.setAccessTokenValiditySeconds(t.getAccessTokenValiditySeconds());
		domain.setRefreshTokenValiditySeconds(t.getRefreshTokenValiditySeconds());
		domain.setAutoApprove(t.isAutoApprove());
		domain.setAdditionalInformation(t.getAdditionalInformation());

		return domain;
	};

}
