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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
	/**
	 * Put here your full authentication access endpoints...
	 */
	// private static final String[] FULL_AUTHENTICATION_ACCESS = new String[] {};

	@Value("${app.name}")
	private String resourceId;

	@Value("${spring.boot.admin.context-path}")
	private String adminContextPath;

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
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(String.format("%s/", this.adminContextPath));

		http
				// CORS...
				.csrf().disable()
				// Filters...
				.addFilterBefore(customCorsFilter, SessionManagementFilter.class)
				// OAuth OPTIONS requests...
				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
				// Public access...
				// .antMatchers(PUBLIC_ACCESS).permitAll()
				// Spring Boot Admin public endpoints...
				.antMatchers(String.format("%s/assets/**", this.adminContextPath),
						String.format("%s/login", this.adminContextPath))
				.permitAll()
				// Swagger documentation public endpoints...
				.antMatchers("/v2/api-docs", "/webjars/**", "/swagger-resources/**", "/swagger-ui.html**").permitAll()
				// Application public endpoints...
				.antMatchers("/reset-password**", "/update-password").permitAll()
				// Client credentials access...
				// .antMatchers(CLIENT_ACCESS).access("#oauth2.isClient()")
				// Set up admin access...
				.antMatchers("/clients**", "/masters**", "/settings**").hasRole("ADMIN")
				// Setup full authentication access...
				.anyRequest().authenticated()
				// Spring Boot Admin login and logout...
				.and().formLogin().loginPage(String.format("%s/login", this.adminContextPath))
				.successHandler(successHandler).and().logout()
				.logoutUrl(String.format("%s/logout", this.adminContextPath))
				// Enable HTTP-Basic support. This is needed for the Spring Boot Admin
				// Client to register.
				.and().httpBasic();
	}

}
