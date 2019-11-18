/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.co.app.auth.handlers.OnAuthenticationSuccessHandler;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	/**
	 * Put here your public access endpoints...
	 */
	private static final String[] PUBLIC_ACCESS = new String[] { "/webjars/**", "/css/**", "/js/**", "/img/**", "/",
			"/login", "/oauth/authorize", "/oauth/confirm_access", "/reset-password**", "/update-password",
			"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/actuator**" };
	/**
	 * Put here your public access endpoints...
	 */
	private static final String[] ADMIN_ACCESS = new String[] { "/clients**" };
	/**
	 * Put here your client access endpoints...
	 */
	/**
	 * private static final String[] CLIENT_ACCESS = new String[] {};
	 */

	@Value("${app.name}")
	private String resourceId;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		return new JwtAccessTokenConverter();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(Boolean.TRUE);
		return defaultTokenServices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2
	 * .config.annotation.web.configurers.ResourceServerSecurityConfigurer)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceId).tokenServices(tokenServices());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		OnAuthenticationSuccessHandler successHandler = new OnAuthenticationSuccessHandler();

		// CORS...
		http.csrf().disable();

		// Login and logout...
		http.formLogin().loginPage("/login").permitAll()//
				.successHandler(successHandler).defaultSuccessUrl("/")//
				.and().logout().logoutSuccessUrl("/login").permitAll()//
				// If a user try to access a resource without having enough permissions
				.and().exceptionHandling().accessDeniedPage("/login");

		http.authorizeRequests()//
				// OAuth OPTIONS requests...
				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()//
				// Public access...
				.antMatchers(PUBLIC_ACCESS).permitAll()//
				// Set up admin access...
				.antMatchers(ADMIN_ACCESS).hasRole("ADMIN")//
				// Client credentials access...
				// .antMatchers(CLIENT_ACCESS).access("#oauth2.isClient()")
				// Setup full authentication access...
				.anyRequest().authenticated();
	}

}
