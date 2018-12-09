/**
 * 
 */
package com.innova4j.api.app.configuration.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author innova4j-team
 *
 */
@Configuration
@PropertySource("classpath:/auth/auth.properties")
@Import({ com.innova4j.api.auth.configuration.AuthConfiguration.class })
public class AuthConfiguration {
	/**
	 * Put your beans here...
	 */

}
