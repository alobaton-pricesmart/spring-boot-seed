/**
 * 
 */
package com.innova4j.api.auth.services.token.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.google.common.collect.ImmutableMap;
import com.innova4j.api.auth.AuthConstants;
import com.innova4j.api.auth.dao.AuthRefreshTokenRepository;
import com.innova4j.api.auth.dao.AuthTokenRepository;
import com.innova4j.api.auth.domain.AuthRefreshToken;
import com.innova4j.api.auth.domain.AuthRefreshTokenId;
import com.innova4j.api.auth.domain.AuthToken;
import com.innova4j.api.auth.domain.AuthTokenId;
import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.commons.utils.DateUtils;

/**
 * @author innova4j-team
 *
 */
public class CustomTokenStore implements TokenStore {
	@Autowired
	private AuthenticationKeyGenerator authenticationKeyGenerator;

	@Autowired
	private HashEncoder encoder;

	@Autowired
	private AuthTokenRepository tokenRepository;

	@Autowired
	private AuthRefreshTokenRepository refreshTokenRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * readAuthentication(org.springframework.security.oauth2.common.
	 * OAuth2AccessToken)
	 */
	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * readAuthentication(java.lang.String)
	 */
	@Override
	public OAuth2Authentication readAuthentication(String token) {
		AuthTokenId id = new AuthTokenId();
		id.setTokenId(encoder.encode(token));

		AuthToken domain = new AuthToken();
		domain.setId(id);

		Example<AuthToken> example = Example.of(domain);

		Optional<AuthToken> optional = tokenRepository.findOne(example);

		return optional.isPresent() ? optional.get().getAuthentication() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * storeAccessToken(org.springframework.security.oauth2.common.
	 * OAuth2AccessToken,
	 * org.springframework.security.oauth2.provider.OAuth2Authentication)
	 */
	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		if (readAccessToken(token.getValue()) != null) {
			removeAccessToken(token);
		}

		String authenticationId = encoder.encode(authenticationKeyGenerator.extractKey(authentication));

		AuthTokenId id = new AuthTokenId();
		id.setAuthenticationId(authenticationId);

		AuthToken domain = new AuthToken();
		domain.setId(id);

		Example<AuthToken> example = Example.of(domain);

		if (tokenRepository.exists(example)) {
			tokenRepository.customDelete(ImmutableMap.<String, Object>builder()
					.put(AuthConstants.AUTHENTICATION_ID, authenticationId).build());
		}

		id.setNickname(authentication.isClientOnly() ? null : authentication.getName());
		id.setClientId(authentication.getOAuth2Request().getClientId());
		id.setTokenId(encoder.encode(token.getValue()));
		id.setRefreshTokenId(
				encoder.encode(token.getRefreshToken() != null ? token.getRefreshToken().getValue() : null));

		domain.setToken(token);
		domain.setAuthentication(authentication);
		domain.setExpiresAt(DateUtils.toLocalDateTime(token.getExpiration()));

		tokenRepository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.oauth2.provider.token.TokenStore#readAccessToken
	 * (java.lang.String)
	 */
	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		AuthTokenId id = new AuthTokenId();
		id.setTokenId(encoder.encode(tokenValue));

		AuthToken domain = new AuthToken();
		domain.setId(id);

		Example<AuthToken> example = Example.of(domain);

		Optional<AuthToken> optional = tokenRepository.findOne(example);

		return optional.isPresent() ? optional.get().getToken() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * removeAccessToken(org.springframework.security.oauth2.common.
	 * OAuth2AccessToken)
	 */
	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		tokenRepository.customDelete(ImmutableMap.<String, Object>builder()
				.put(AuthConstants.TOKEN_ID, encoder.encode(token.getValue())).build());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * storeRefreshToken(org.springframework.security.oauth2.common.
	 * OAuth2RefreshToken,
	 * org.springframework.security.oauth2.provider.OAuth2Authentication)
	 */
	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		String authenticationId = encoder.encode(authenticationKeyGenerator.extractKey(authentication));

		AuthRefreshTokenId id = new AuthRefreshTokenId();
		id.setAuthenticationId(authenticationId);

		AuthRefreshToken domain = new AuthRefreshToken();
		domain.setId(id);

		Example<AuthRefreshToken> example = Example.of(domain);

		if (refreshTokenRepository.exists(example)) {
			refreshTokenRepository.customDelete(ImmutableMap.<String, Object>builder()
					.put(AuthConstants.AUTHENTICATION_ID, authenticationId).build());
		}

		id.setTokenId(encoder.encode(refreshToken.getValue()));

		domain.setToken(refreshToken);
		domain.setAuthentication(authentication);

		refreshTokenRepository.save(domain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * readRefreshToken(java.lang.String)
	 */
	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		AuthRefreshTokenId id = new AuthRefreshTokenId();
		id.setTokenId(encoder.encode(tokenValue));

		AuthRefreshToken domain = new AuthRefreshToken();
		domain.setId(id);

		Example<AuthRefreshToken> example = Example.of(domain);

		Optional<AuthRefreshToken> optional = refreshTokenRepository.findOne(example);

		return optional.isPresent() ? optional.get().getToken() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * readAuthenticationForRefreshToken(org.springframework.security.oauth2.common.
	 * OAuth2RefreshToken)
	 */
	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		AuthRefreshTokenId id = new AuthRefreshTokenId();
		id.setTokenId(encoder.encode(token.getValue()));

		AuthRefreshToken domain = new AuthRefreshToken();
		domain.setId(id);

		Example<AuthRefreshToken> example = Example.of(domain);

		Optional<AuthRefreshToken> optional = refreshTokenRepository.findOne(example);

		return optional.isPresent() ? optional.get().getAuthentication() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * removeRefreshToken(org.springframework.security.oauth2.common.
	 * OAuth2RefreshToken)
	 */
	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		refreshTokenRepository.customDelete(ImmutableMap.<String, Object>builder()
				.put(AuthConstants.TOKEN_ID, encoder.encode(token.getValue())).build());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * removeAccessTokenUsingRefreshToken(org.springframework.security.oauth2.common
	 * .OAuth2RefreshToken)
	 */
	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		refreshTokenRepository.customDelete(ImmutableMap.<String, Object>builder()
				.put(AuthConstants.REFRESH_TOKEN_ID, encoder.encode(refreshToken.getValue())).build());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.oauth2.provider.token.TokenStore#getAccessToken(
	 * org.springframework.security.oauth2.provider.OAuth2Authentication)
	 */
	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		String authenticationId = encoder.encode(authenticationKeyGenerator.extractKey(authentication));

		AuthTokenId id = new AuthTokenId();
		id.setAuthenticationId(authenticationId);

		AuthToken domain = new AuthToken();
		domain.setId(id);

		Example<AuthToken> example = Example.of(domain);

		Optional<AuthToken> optional = tokenRepository.findOne(example);

		return optional.isPresent() ? optional.get().getToken() : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * findTokensByClientIdAndUserName(java.lang.String, java.lang.String)
	 */
	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		AuthTokenId id = new AuthTokenId();
		id.setClientId(clientId);
		id.setNickname(userName);

		AuthToken domain = new AuthToken();
		domain.setId(id);

		Example<AuthToken> example = Example.of(domain);

		List<AuthToken> result = tokenRepository.findAll(example);

		return result.stream().map(token -> token.getToken()).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.TokenStore#
	 * findTokensByClientId(java.lang.String)
	 */
	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		AuthTokenId id = new AuthTokenId();
		id.setClientId(clientId);

		AuthToken domain = new AuthToken();
		domain.setId(id);

		Example<AuthToken> example = Example.of(domain);

		List<AuthToken> result = tokenRepository.findAll(example);

		return result.stream().map(token -> token.getToken()).collect(Collectors.toList());
	}

}
