/**
 * 
 */
package com.co.app.email.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author alobaton
 *
 */
@Configuration
@Import({ ThymeleafConfiguration.class })
public class EmailConfiguration {
	/**
	 * Put your beans here...
	 */

}
