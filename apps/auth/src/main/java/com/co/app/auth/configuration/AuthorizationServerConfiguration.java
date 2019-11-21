package com.co.app.auth.configuration;

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
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.co.app.auth.dao.AuthPasswordTokenRepository;
import com.co.app.auth.dao.AuthUserRepository;
import com.co.app.auth.granters.password.PasswordGranter;
import com.co.app.auth.granters.password.PasswordGranterBuilder;
import com.co.app.auth.services.client.impl.CustomClientDetailsService;
import com.co.app.auth.services.encoder.HashEncoder;
import com.co.app.auth.services.user.impl.CustomUserDetailsService;

/**
 * @author alobaton
 *
 */
@Configuration(value = "authorizationServerConfiguration")
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static final Log LOGGER = LogFactory.getLog(AuthorizationServerConfiguration.class);

	@Autowired
	private CustomClientDetailsService customClientDetailsService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthUserRepository userRepository;

	@Autowired
	private AuthPasswordTokenRepository passwordTokenRepository;

	@Autowired
	private HashEncoder encoder;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		return new JwtAccessTokenConverter();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

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

				LOGGER.error(sw.toString());

				return super.translate(e);
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
		endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager).userDetailsService(customUserDetailsService)
				.tokenGranter(tokenGranter(endpoints)).exceptionTranslator(webResponseExceptionTranslator());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	/**
	 * The application token granter.
	 * 
	 * @param endpoints The authorization endpoints server configurer.
	 * @return The composite token granter.
	 */
	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		List<TokenGranter> granters = new ArrayList<>(Arrays.asList(endpoints.getTokenGranter()));

		PasswordGranter passwordGranter = new PasswordGranterBuilder().tokenServices(endpoints.getTokenServices())
				.clientDetailsService(endpoints.getClientDetailsService())
				.requestFactory(endpoints.getOAuth2RequestFactory()).passwordTokenRepository(passwordTokenRepository)
				.userRepository(userRepository).encoder(encoder).build();
		granters.add(passwordGranter);

		return new CompositeTokenGranter(granters);
	}

}