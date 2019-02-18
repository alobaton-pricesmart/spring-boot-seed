/**
 * 
 */
package com.alobaton.api.masters.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.alobaton.api.masters.dao" })
@EntityScan(basePackages = { "com.alobaton.api.masters.domain" })
@EnableTransactionManagement
public class MastersConfiguration {

}
