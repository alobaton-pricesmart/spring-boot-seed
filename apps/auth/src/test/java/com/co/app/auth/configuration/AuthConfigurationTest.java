/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alobaton
 *
 */
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = { "com.co.app.auth.dao" })
@EntityScan(basePackages = { "com.co.app.auth.domain" })
@EnableJpaAuditing
@EnableTransactionManagement
public class AuthConfigurationTest {
}
