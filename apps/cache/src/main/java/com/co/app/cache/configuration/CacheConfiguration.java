package com.co.app.cache.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author luis.colmenarez
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.co.app.cache.dao" })
@EntityScan(basePackages = { "com.co.app.cache.domain" })
@EnableTransactionManagement
public class CacheConfiguration {

}
