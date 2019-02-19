/**
 * 
 */
package com.co.app.auth.granters.password;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.co.app.auth.domain.AuthPasswordToken;
import com.co.app.auth.domain.AuthPasswordTokenId;
import com.co.app.auth.domain.CustomUserDetails;
import com.co.app.auth.dto.AuthUserDto;
import com.co.app.auth.services.encoder.HashEncoder;
import com.co.app.auth.services.token.AuthPasswordTokenService;
import com.co.app.auth.services.user.AuthUserService;

/**
 * @author alobaton
 *
 */
public class PasswordGranter extends AbstractTokenGranter {

	private static final String AUTH_PASSWORD_GRANT_TYPE = "auth_password";
	private static final String PASSWORD_TOKEN_PARAM = "password_token";

	private AuthUserService userService;
	private AuthPasswordTokenService passwordTokenService;
	private HashEncoder encoder;

	public PasswordGranter(@NotNull AuthorizationServerTokenServices tokenServices,
			@NotNull ClientDetailsService clientDetailsService, @NotNull OAuth2RequestFactory requestFactory) {
		super(tokenServices, clientDetailsService, requestFactory, AUTH_PASSWORD_GRANT_TYPE);
	}

	/**
	 * @param passwordService the passwordService to set
	 */
	public void setPasswordTokenService(@NotNull AuthPasswordTokenService passwordTokenService) {
		this.passwordTokenService = passwordTokenService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(@NotNull AuthUserService userService) {
		this.userService = userService;
	}

	/**
	 * @param encoder the encoder to set
	 */
	public void setEncoder(@NotNull HashEncoder encoder) {
		this.encoder = encoder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.token.AbstractTokenGranter#
	 * getOAuth2Authentication(org.springframework.security.oauth2.provider.
	 * ClientDetails, org.springframework.security.oauth2.provider.TokenRequest)
	 */
	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
		Map<String, String> params = tokenRequest.getRequestParameters();

		String token = params.containsKey(PASSWORD_TOKEN_PARAM) ? params.get(PASSWORD_TOKEN_PARAM) : StringUtils.EMPTY;

		AuthPasswordTokenId id = new AuthPasswordTokenId();
		id.setToken(encoder.encode(token));

		AuthPasswordToken passwordToken = new AuthPasswordToken();
		passwordToken.setId(id);

		passwordToken = passwordTokenService.customGet(passwordToken);
		if (passwordToken.isExpired()) {
			throw new UnauthorizedUserException("Authorization code has expired.");
		}

		AuthUserDto user = new AuthUserDto();
		user.setNickname(passwordToken.getId().getNickname());

		user = userService.customGet(user);

		CustomUserDetails userDetails = new CustomUserDetails(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(tokenRequest.createOAuth2Request(client),
				authentication);
		return oAuth2Authentication;
	}

}
