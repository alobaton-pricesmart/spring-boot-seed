package com.co.app.auth.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.co.app.commons.domain.AuthClientDetails;

public class CustomClientDetails implements ClientDetails {
	private static final long serialVersionUID = -1L;

	private AuthClientDetails authClientDetails;

	public CustomClientDetails(@NotNull AuthClientDetails authClientDetails) {
		this.authClientDetails = authClientDetails;
	}

	@Override
	public String getClientId() {
		return authClientDetails.getClientId();
	}

	@Override
	public Set<String> getResourceIds() {
		return authClientDetails.getResourceIds();
	}

	@Override
	public boolean isSecretRequired() {
		return authClientDetails.isSecretRequired();
	}

	@Override
	public String getClientSecret() {
		return authClientDetails.getClientSecret();
	}

	@Override
	public boolean isScoped() {
		return authClientDetails.isScoped();
	}

	@Override
	public Set<String> getScope() {
		return authClientDetails.getScope();
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return authClientDetails.getAuthorizedGrantTypes();
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return authClientDetails.getRegisteredRedirectUri();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		if (authClientDetails.getAuthorities() == null) {
			return Collections.emptyList();
		}

		String arr[] = new String[authClientDetails.getAuthorities().size()];
		return AuthorityUtils.createAuthorityList(authClientDetails.getAuthorities().toArray(arr));
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return authClientDetails.getAccessTokenValiditySeconds();
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return authClientDetails.getRefreshTokenValiditySeconds();
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return authClientDetails.isAutoApprove();
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return authClientDetails.getAdditionalInformation();
	}

	public static final Function<ClientDetails, AuthClientDetails> CONVERTER_ENTITY = (ClientDetails t) -> {
		AuthClientDetails authClientDetailsEntity = new AuthClientDetails();

		authClientDetailsEntity.setClientId(t.getClientId());
		authClientDetailsEntity.setResourceIds(t.getResourceIds());
		authClientDetailsEntity.setSecretRequired(t.isSecretRequired());
		authClientDetailsEntity.setClientSecret(t.getClientSecret());
		authClientDetailsEntity.setScoped(t.isScoped());
		authClientDetailsEntity.setScope(t.getScope());
		authClientDetailsEntity.setAuthorizedGrantTypes(t.getAuthorizedGrantTypes());
		authClientDetailsEntity.setRegisteredRedirectUri(t.getRegisteredRedirectUri());
		authClientDetailsEntity.setAuthorities(t.getAuthorities());
		authClientDetailsEntity.setAccessTokenValiditySeconds(t.getAccessTokenValiditySeconds());
		authClientDetailsEntity.setRefreshTokenValiditySeconds(t.getRefreshTokenValiditySeconds());
		authClientDetailsEntity.setAutoApprove(t.isAutoApprove(StringUtils.EMPTY));
		authClientDetailsEntity.setAdditionalInformation(t.getAdditionalInformation());

		return authClientDetailsEntity;
	};

}
