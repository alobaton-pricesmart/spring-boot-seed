package com.co.app.memory.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author luis.colmenarez
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.co.app.memory.dao" })
@EntityScan(basePackages = { "com.co.app.memory.domain" })
@EnableTransactionManagement
public class MemoryConfiguration {

}
