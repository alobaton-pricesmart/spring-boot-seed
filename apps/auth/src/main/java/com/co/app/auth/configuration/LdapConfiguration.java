package com.co.app.auth.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "ldap")
public class LdapConfiguration {

	private String urls;
	private String baseDn;
	private String username;
	private String password;
	private String userDnPattern;
	private boolean enabled;

	/**
	 * @return the urls
	 */
	public String getUrls() {
		return urls;
	}

	/**
	 * @param urls the urls to set
	 */
	public void setUrls(String urls) {
		this.urls = urls;
	}

	/**
	 * @return the baseDn
	 */
	public String getBaseDn() {
		return baseDn;
	}

	/**
	 * @param baseDn the baseDn to set
	 */
	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userDnPattern
	 */
	public String getUserDnPattern() {
		return userDnPattern;
	}

	/**
	 * @param userDnPattern the userDnPattern to set
	 */
	public void setUserDnPattern(String userDnPattern) {
		this.userDnPattern = userDnPattern;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
