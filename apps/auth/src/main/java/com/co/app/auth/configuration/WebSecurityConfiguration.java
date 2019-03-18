/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.co.app.auth.services.user.impl.CustomUserDetailsService;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Log LOGGER = LogFactory.getLog(WebSecurityConfiguration.class);

	@Value("${ldap.urls}")
	private String ldapUrls;

	@Value("${ldap.base.dn}")
	private String ldapBaseDn;

	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;

	@Value("${ldap.password}")
	private String ldapPrincipalPassword;

	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;

	@Value("${ldap.enabled}")
	private String ldapEnabled;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#userDetailsService()
	 */
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
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
		if (Boolean.parseBoolean(this.ldapEnabled)) {
			LOGGER.info("Autenticando contra LADP");
			auth.ldapAuthentication().contextSource().url(this.ldapUrls + this.ldapBaseDn)
					.managerDn(ldapSecurityPrincipal).managerPassword(this.ldapPrincipalPassword).and()
					.userDnPatterns(this.ldapUserDnPattern);
			auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		} else {
			LOGGER.info("Autenticando contra DB");
			auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		}
	}
}
