/**
 * 
 */
package com.innova4j.api.settings.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.innova4j.api.settings.dao" })
@EntityScan(basePackages = { "com.innova4j.api.settings.domain" })
@EnableTransactionManagement
public class SettingsConfiguration {

}
