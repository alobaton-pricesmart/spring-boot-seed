/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Data source configuration class.
 * 
 * @author alobaton
 *
 */
@Configuration
@PropertySource("classpath:/dao/dao.properties")
public class DaoConfiguration {
	/**
	 * Put your beans here...
	 */

}