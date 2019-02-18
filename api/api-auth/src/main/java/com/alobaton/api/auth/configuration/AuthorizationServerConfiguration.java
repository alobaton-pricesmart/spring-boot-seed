package com.alobaton.api.auth.configuration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.alobaton.api.auth.granters.password.PasswordGranter;
import com.alobaton.api.auth.granters.password.PasswordGranterBuilder;
import com.alobaton.api.auth.services.client.impl.CustomClientDetailsService;
import com.alobaton.api.auth.services.encoder.HashEncoder;
import com.alobaton.api.auth.services.token.AuthPasswordTokenService;
import com.alobaton.api.auth.services.user.AuthUserService;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static final Log LOGGER = LogFactory.getLog(AuthorizationServerConfiguration.class);

	@Autowired
	private CustomClientDetailsService customClientDetailsService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthUserService userService;

	@Autowired
	private AuthPasswordTokenService passwordTokenService;

	@Autowired
	private HashEncoder encoder;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Bean
	public AuthenticationKeyGenerator authenticationKeyGenerator() {
		return new DefaultAuthenticationKeyGenerator();
	}

	@Bean
	public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);

				LOGGER.debug(sw.toString());

				ResponseEntity<OAuth2Exception> response = super.translate(e);
				return response;
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.
	 * oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(customClientDetailsService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.
	 * oauth2.config.annotation.web.configurers.
	 * AuthorizationServerEndpointsConfigurer)
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter)
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
				.tokenGranter(tokenGranter(endpoints)).exceptionTranslator(webResponseExceptionTranslator());
	}

	/**
	 * The application token granter.
	 * 
	 * @param endpoints The authorization endpoints server configurer.
	 * @return The composite token granter.
	 */
	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		List<TokenGranter> granters = new ArrayList<TokenGranter>(Arrays.asList(endpoints.getTokenGranter()));

		PasswordGranter passwordGranter = new PasswordGranterBuilder().tokenServices(endpoints.getTokenServices())
				.clientDetailsService(endpoints.getClientDetailsService())
				.requestFactory(endpoints.getOAuth2RequestFactory()).passwordTokenService(passwordTokenService)
				.userService(userService).encoder(encoder).build();
		granters.add(passwordGranter);

		return new CompositeTokenGranter(granters);
	}

}