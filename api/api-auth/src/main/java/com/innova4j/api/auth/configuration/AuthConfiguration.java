/**
 * 
 */
package com.innova4j.api.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.innova4j.api.auth.services.client.impl.CustomClientDetailsService;
import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.auth.services.encoder.impl.HashEncoderImpl;
import com.innova4j.api.auth.services.token.AuthPasswordTokenService;
import com.innova4j.api.auth.services.token.impl.AuthPasswordTokenServiceImpl;
import com.innova4j.api.auth.services.token.impl.CustomTokenStore;
import com.innova4j.api.auth.services.user.AuthUserService;
import com.innova4j.api.auth.services.user.impl.AuthUserServiceImpl;
import com.innova4j.api.auth.services.user.impl.CustomUserDetailsService;

/**
 * @author innova4j-team
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.innova4j.api.auth.dao")
@PropertySource("dao/dao.properties")
@EnableTransactionManagement
public class AuthConfiguration {

	@Bean
	public CustomClientDetailsService clientDetailsService() {
		return new CustomClientDetailsService();
	}

	@Bean
	public CustomUserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public AuthUserService userService() {
		return new AuthUserServiceImpl();
	}

	@Bean
	public CustomTokenStore tokenStore() {
		return new CustomTokenStore();
	}

	@Bean
	public AuthPasswordTokenService passwordTokenService() {
		return new AuthPasswordTokenServiceImpl();
	}

	@Bean
	public HashEncoder encoder() {
		return new HashEncoderImpl();
	}
}
