/**
 * 
 */
package com.co.app.admin.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @author alobaton
 *
 */

@Configuration
@EnableAdminServer
public class AdminConfiguration extends WebSecurityConfigurerAdapter {
	@Value("${spring.boot.admin.context-path}")
	private String adminContextPath;

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
				.authorizeRequests()
				// Spring Boot Admin public endpoints...
				.antMatchers(String.format("%s/assets/**", this.adminContextPath),
						String.format("%s/login", this.adminContextPath))
				.permitAll()
				// Application public endpoints...
				.antMatchers(String.format("%s/login**", this.adminContextPath)).permitAll()
				// Set up admin access...
				.antMatchers("/admin**").hasRole("ADMIN")
				// Setup full authentication access...
				.anyRequest().authenticated();
	}
}
