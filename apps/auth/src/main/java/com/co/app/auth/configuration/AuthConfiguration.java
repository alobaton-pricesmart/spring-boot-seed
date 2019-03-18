/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.co.app.auth.dao" })
@EntityScan(basePackages = { "com.co.app.auth.domain" })
@EnableTransactionManagement
public class AuthConfiguration {

}
