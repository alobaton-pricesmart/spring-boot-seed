/**
 * 
 */
package com.co.app.admin.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @author alobaton
 *
 */

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@EnableOAuth2Client
public class AdminConfiguration extends WebSecurityConfigurerAdapter {
	// @Value("${spring.boot.admin.context-path}")
	// private String adminContextPath;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll().and().httpBasic().and().csrf().disable();
	}
}
