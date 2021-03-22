/**
 * 
 */
package com.co.app.auth.granters.password;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.co.app.auth.domain.CustomUserDetails;
import com.co.app.auth.repository.AuthPasswordTokenRepository;
import com.co.app.auth.repository.AuthUserRepository;
import com.co.app.auth.services.HashEncoder;
import com.co.app.commons.domain.AuthPasswordToken;
import com.co.app.commons.domain.AuthPasswordTokenId;
import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.message.constants.MessageConstants;

/**
 * @author alobaton
 *
 */
// Dont set @component, define Bean manually at AuthorizationServerConfiguration.
public class PasswordGranter extends AbstractTokenGranter {

	private static final String AUTH_PASSWORD_GRANT_TYPE = "auth_password";
	private static final String PASSWORD_TOKEN_PARAM = "password_token";

	@Autowired
	private AuthUserRepository userRepository;

	@Autowired
	private AuthPasswordTokenRepository passwordTokenRepopsitory;

	@Autowired
	private HashEncoder encoder;

	public PasswordGranter(@NotNull AuthorizationServerTokenServices tokenServices,
			@NotNull ClientDetailsService clientDetailsService, @NotNull OAuth2RequestFactory requestFactory) {
		super(tokenServices, clientDetailsService, requestFactory, AUTH_PASSWORD_GRANT_TYPE);
	}

	/**
	 * @return the userRepository
	 */
	public AuthUserRepository getUserRepository() {
		return userRepository;
	}

	/**
	 * @param userRepository the userRepository to set
	 */
	public void setUserRepository(AuthUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * @return the passwordTokenRepopsitory
	 */
	public AuthPasswordTokenRepository getPasswordTokenRepopsitory() {
		return passwordTokenRepopsitory;
	}

	/**
	 * @param passwordTokenRepopsitory the passwordTokenRepopsitory to set
	 */
	public void setPasswordTokenRepopsitory(AuthPasswordTokenRepository passwordTokenRepopsitory) {
		this.passwordTokenRepopsitory = passwordTokenRepopsitory;
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

		passwordToken = passwordTokenRepopsitory.findOne(Example.of(passwordToken))
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_PASSWPRD_TOKEN, id.toString()));
		if (passwordToken.isExpired()) {
			throw new UnauthorizedUserException("Authorization code has expired.");
		}

		String nickname = passwordToken.getId().getNickname();
		AuthUser user = userRepository.findById(nickname)
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_USER_NAME, nickname));
		if (user == null) {
			throw new UsernameNotFoundException(
					String.format("Username %s not found", passwordToken.getId().getNickname()));
		}

		CustomUserDetails userDetails = new CustomUserDetails(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
		return new OAuth2Authentication(tokenRequest.createOAuth2Request(client), authentication);
	}

}
