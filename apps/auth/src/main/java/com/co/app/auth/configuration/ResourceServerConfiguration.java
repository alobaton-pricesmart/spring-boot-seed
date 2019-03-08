/**
 * 
 */
package com.co.app.auth.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
	// private static final String[] PUBLIC_ACCESS = new String[] {};
	/**
	 * Put here your client access endpoints...
	 */
	// private static final String[] CLIENT_ACCESS = new String[] {};

	@Value("${app.name}")
	private String resourceId;

	@Autowired
	private CustomCorsFilter customCorsFilter;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(Boolean.TRUE);
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter(corsConfigurationSource());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		return converter;
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

		http
				// CORS...
				.csrf().disable()//
				// Filters...
				.addFilterBefore(customCorsFilter, SessionManagementFilter.class)//
				.authorizeRequests()//
				// OAuth OPTIONS requests...
				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()//
				// Application public endpoints...
				.antMatchers("/webjars/**", "/reset-password**", "/update-password").permitAll()//
				// Public access...
				// .antMatchers(PUBLIC_ACCESS).permitAll()
				// Swagger documentation public endpoints...
				.antMatchers("/v2/api-docs", "/webjars/**", "/swagger-resources/**", "/swagger-ui.html**").anonymous()//
				// Set up admin access...
				.antMatchers("/clients**", "/masters**", "/settings**").hasRole("ADMIN")//
				// Client credentials access...
				// .antMatchers(CLIENT_ACCESS).access("#oauth2.isClient()")
				// Setup full authentication access...
				.anyRequest().authenticated()//
				// Login and logout...
				.and().formLogin().loginPage("/login").permitAll()//
				.and().logout().permitAll()//
		// .and().httpBasic()//
		;
	}

}
