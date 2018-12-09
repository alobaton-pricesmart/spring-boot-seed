/**
 * 
 */
package com.innova4j.api.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.innova4j.api.auth.services.client.impl.CustomClientDetailsService;
import com.innova4j.api.auth.services.encoder.HashEncoder;
import com.innova4j.api.auth.services.encoder.impl.HashEncoderImpl;
import com.innova4j.api.auth.services.token.AuthPasswordTokenService;
import com.innova4j.api.auth.services.token.AuthTokenService;
import com.innova4j.api.auth.services.token.impl.AuthPasswordTokenServiceImpl;
import com.innova4j.api.auth.services.token.impl.AuthTokenServiceImpl;
import com.innova4j.api.auth.services.token.impl.CustomTokenStore;
import com.innova4j.api.auth.services.user.AuthUserService;
import com.innova4j.api.auth.services.user.impl.AuthUserServiceImpl;
import com.innova4j.api.auth.services.user.impl.CustomUserDetailsService;

/**
 * @author innova4j-team
 *
 */
@Configuration
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
	public AuthTokenService tokenService() {
		return new AuthTokenServiceImpl();
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
