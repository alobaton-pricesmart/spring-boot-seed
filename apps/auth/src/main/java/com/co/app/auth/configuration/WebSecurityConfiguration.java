/**
 * 
 */
package com.co.app.auth.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.co.app.auth.services.impl.CustomUserDetailsService;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Log LOGGER = LogFactory.getLog(WebSecurityConfiguration.class);

	@Autowired
	private LdapConfiguration ldapConfiguration;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurityConfiguration() {
		super();
		// This property allow to share the security context throught a thread pool.
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#userDetailsService()
	 */
	@Override
	protected UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#authenticationManagerBean()
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (ldapConfiguration.isEnabled()) {
			LOGGER.info("LDAP enabled");
			auth.ldapAuthentication().contextSource().url(ldapConfiguration.getUrls() + ldapConfiguration.getBaseDn())
					.managerDn(ldapConfiguration.getUsername()).managerPassword(ldapConfiguration.getPassword()).and()
					.userDnPatterns(ldapConfiguration.getUserDnPattern());
		}

		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall fireWall = new StrictHttpFirewall();
		fireWall.setAllowUrlEncodedSlash(true);
		fireWall.setAllowUrlEncodedPercent(true);
		return fireWall;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/actuator/**").antMatchers("/v2/api-docs", "/configuration/**", "/error",
				"/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**");

		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}

}
