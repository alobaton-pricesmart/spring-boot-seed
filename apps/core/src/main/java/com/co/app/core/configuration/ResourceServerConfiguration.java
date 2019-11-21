/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${resource.id}")
	private String resourceId;

	/**
	 * Put here your public access endpoints...
	 */
	private static final String[] PUBLIC_ACCESS = new String[] { "/webjars/**", "/css/**", "/js/**", "/img/**", "/",
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// CORS...
		http.csrf().disable();
		http.authorizeRequests()
				// Public access...
				.antMatchers(PUBLIC_ACCESS).permitAll()//
				// Set up admin access...
				.antMatchers(ADMIN_ACCESS).hasRole("ADMIN")//
				// Client credentials access...
				// .antMatchers(CLIENT_ACCESS).access("#oauth2.isClient()")
				// Setup full authentication access...
				.anyRequest().authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId);
	}

}
