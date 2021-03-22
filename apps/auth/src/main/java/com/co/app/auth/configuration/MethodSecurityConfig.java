/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.co.app.auth.authorize.CustomMethodSecurityExpressionHandler;
import com.co.app.auth.services.AuthUserService;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private AuthUserService userService;

	/**
	 * Creates an expression handler for the method security context.
	 */
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return new CustomMethodSecurityExpressionHandler(userService);
	}
}