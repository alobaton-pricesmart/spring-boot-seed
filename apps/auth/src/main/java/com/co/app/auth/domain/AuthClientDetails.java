/**
 * 
 */
package com.co.app.auth.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.co.app.auth.dto.AuthClientDetailsDto;
import com.co.app.commons.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "auth_client_details")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "clientId" }, doNotUseGetters = true, callSuper = false)
public class AuthClientDetails extends BaseDomain implements ClientDetails {

	private static final long serialVersionUID = 808870501996988945L;

	public static final String CLIENT_ID = "clientId";

	@Id
	@NotNull
	@Column(name = "client_id")
	private String clientId;

	@NotNull
	@Type(type = "json")
	@Column(name = "resource_ids", columnDefinition = "json")
	private Set<String> resourceIds;

	@Column(name = "secret_required")
	private boolean secretRequired;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "scoped")
	private boolean scoped;

	@Type(type = "json")
	@Column(name = "scope", columnDefinition = "json")
	private Set<String> scope;

	@Type(type = "json")
	@Column(name = "authorized_grant_types", columnDefinition = "json")
	private Set<String> authorizedGrantTypes;

	@Type(type = "json")
	@Column(name = "registered_redirect_uri", columnDefinition = "json")
	private Set<String> registeredRedirectUri;

	@Type(type = "json")
	@Column(name = "authorities", columnDefinition = "json")
	private Set<String> authorities;

	@Column(name = "access_token_validity_seconds")
	private Integer accessTokenValiditySeconds;

	@Column(name = "refresh_token_validity_seconds")
	private Integer refreshTokenValiditySeconds;

	@Column(name = "auto_approve")
	private boolean autoApprove;

	@Type(type = "json")
	@Column(name = "additional_information", columnDefinition = "json")
	@MapKeyColumn()
	private Map<String, Object> additionalInformation;

	@Override
	public boolean isAutoApprove(String scope) {
		return this.autoApprove;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		if (this.authorities == null) {
			this.authorities = new HashSet<>();
		}

		String arr[] = new String[authorities.size()];
		return AuthorityUtils.createAuthorityList(authorities.toArray(arr));
	}
	
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = AuthorityUtils.authorityListToSet(authorities);
	}

	public static final Function<AuthClientDetailsDto, ClientDetails> CONVERTER = (AuthClientDetailsDto t) -> {
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
