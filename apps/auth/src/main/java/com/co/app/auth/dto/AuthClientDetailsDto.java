/**
 * 
 */
package com.co.app.auth.dto;

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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class AuthClientDetailsDto {

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

	public static final Function<ClientDetails, AuthClientDetailsDto> CONVERTER = (ClientDetails t) -> {
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
	};

}
