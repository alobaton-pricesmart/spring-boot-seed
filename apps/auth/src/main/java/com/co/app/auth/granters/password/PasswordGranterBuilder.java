/**
 * 
 */
package com.co.app.auth.granters.password;

import javax.validation.constraints.NotNull;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.co.app.auth.dao.AuthPasswordTokenRepository;
import com.co.app.auth.dao.AuthUserRepository;
import com.co.app.auth.services.encoder.HashEncoder;

/**
 * @author alobaton
 *
 */
public class PasswordGranterBuilder {

	private AuthorizationServerTokenServices tokenServices;
	private ClientDetailsService clientDetailsService;
	private OAuth2RequestFactory requestFactory;
	private AuthPasswordTokenRepository passwordTokenRepository;
	private AuthUserRepository userRepository;
	private HashEncoder encoder;

	/**
	 * @param tokenServices the tokenServices to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder tokenServices(@NotNull AuthorizationServerTokenServices tokenServices) {
		this.tokenServices = tokenServices;
		return this;
	}

	/**
	 * @param clientDetailsService the clientDetailsService to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder clientDetailsService(@NotNull ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
		return this;
	}

	/**
	 * @param requestFactory the requestFactory to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder requestFactory(@NotNull OAuth2RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
		return this;
	}

	/**
	 * @param passwordService the passwordService to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder passwordTokenRepository(@NotNull AuthPasswordTokenRepository passwordRepository) {
		this.passwordTokenRepository = passwordRepository;
		return this;
	}

	/**
	 * @param userService the userService to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder userRepository(@NotNull AuthUserRepository userRepository) {
		this.userRepository = userRepository;
		return this;
	}

	/**
	 * @param encoder the encoder to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder encoder(@NotNull HashEncoder encoder) {
		this.encoder = encoder;
		return this;
	}

	/**
	 * Build authorization password token granter.
	 * 
	 * @return The auth password token granter
	 */
	public PasswordGranter build() {
		PasswordGranter granter = new PasswordGranter(tokenServices, clientDetailsService, requestFactory);

		granter.setPasswordTokenRepopsitory(passwordTokenRepository);
		granter.setUserRepository(userRepository);
		granter.setEncoder(encoder);

		return granter;
	}

}
