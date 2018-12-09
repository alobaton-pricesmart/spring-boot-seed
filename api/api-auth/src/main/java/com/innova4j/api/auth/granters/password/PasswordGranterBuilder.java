/**
 * 
 */
package com.innova4j.api.auth.granters.password;

import javax.validation.constraints.NotNull;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.auth.services.token.AuthPasswordTokenService;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
public class PasswordGranterBuilder {

	private AuthorizationServerTokenServices tokenServices;
	private ClientDetailsService clientDetailsService;
	private OAuth2RequestFactory requestFactory;
	private AuthPasswordTokenService passwordTokenService;
	private AuthUserService userService;
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
	public PasswordGranterBuilder passwordTokenService(@NotNull AuthPasswordTokenService passwordService) {
		this.passwordTokenService = passwordService;
		return this;
	}

	/**
	 * @param userService the userService to set
	 * @return The auth password token granter builder
	 */
	public PasswordGranterBuilder userService(@NotNull AuthUserService userService) {
		this.userService = userService;
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

		granter.setPasswordTokenService(passwordTokenService);
		granter.setUserService(userService);
		granter.setEncoder(encoder);

		return granter;
	}

}
