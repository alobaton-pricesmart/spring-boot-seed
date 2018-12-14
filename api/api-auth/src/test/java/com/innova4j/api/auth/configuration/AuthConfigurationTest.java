/**
 * 
 */
package com.innova4j.api.auth.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author innova4j-team
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.innova4j.api.auth.dao" })
@EntityScan(basePackages = { "com.innova4j.api.auth.domain" })
public class AuthConfigurationTest {
}
