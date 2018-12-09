package com.innova4j.api.app.configuration.auth;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.innova4j.api.auth.granters.password.PasswordGranter;
import com.innova4j.api.auth.granters.password.PasswordGranterBuilder;
import com.innova4j.api.auth.services.client.impl.CustomClientDetailsService;
import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.auth.services.token.AuthPasswordTokenService;
import com.innova4j.api.auth.services.token.impl.CustomTokenStore;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static final Log LOGGER = LogFactory.getLog(AuthorizationServerConfiguration.class);

	@Value("${spring.profiles.active:local}")
	private String activeProfile;

	@Value("${auth.code.secret}")
	private String secret;

	@Autowired
	private CustomClientDetailsService clientDetailsService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthUserService userService;

	@Autowired
	private CustomTokenStore tokenStore;

	@Autowired
	private AuthPasswordTokenService passwordTokenService;

	@Autowired
	private HashEncoder encoder;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	/**
	 * 
	 * @return The client details service.
	 */
	@Bean
	public ClientDetailsService clientDetailsService() {
		return clientDetailsService;
	}

	/**
	 * 
	 * @return The token store.
	 */
	@Bean
	public TokenStore tokenStore() {
		return tokenStore;
	}

	/**
	 * 
	 * @return The authentication key generator.
	 */
	@Bean
	public AuthenticationKeyGenerator authenticationKeyGenerator() {
		return new DefaultAuthenticationKeyGenerator();
	}

	/**
	 * 
	 * @return The exception translator.
	 */
	@Bean
	public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				if ("local".equals(activeProfile)) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);

					LOGGER.debug(sw.toString());
				}

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
		clients.withClientDetails(clientDetailsService());
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
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService).tokenGranter(tokenGranter(endpoints))
				.exceptionTranslator(webResponseExceptionTranslator());
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