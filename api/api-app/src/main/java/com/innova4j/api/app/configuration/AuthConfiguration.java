/**
 * 
 */
package com.innova4j.api.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.innova4j.api.auth.configuration.AuthorizationServerConfiguration;
import com.innova4j.api.auth.configuration.ResourceServerConfiguration;
import com.innova4j.api.auth.configuration.WebSecurityConfiguration;

/**
 * @author innova4j-team
 *
 */
@Configuration
@PropertySource("classpath:/auth/auth.properties")
@Import({ com.innova4j.api.auth.configuration.AuthConfiguration.class, AuthorizationServerConfiguration.class,
		ResourceServerConfiguration.class, WebSecurityConfiguration.class })
public class AuthConfiguration {
	/**
	 * Put your beans here...
	 */

}
