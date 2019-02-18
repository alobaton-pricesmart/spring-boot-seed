/**
 * 
 */
package com.alobaton.api.loader.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Data source configuration class.
 * 
 * @author alobaton
 *
 */
@Configuration
@PropertySource("classpath:/dao/dao.properties")
// Put here your base packages for repositories here...
@EnableJpaRepositories(basePackages = { "com.alobaton.api.auth.dao" })
//Put here your base packages for entities here...
@EntityScan(basePackages = { "com.alobaton.api.auth.domain" })
@EnableTransactionManagement
public class DaoConfiguration {
	/**
	 * Put your beans here...
	 */

}