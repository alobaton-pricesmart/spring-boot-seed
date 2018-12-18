/**
 * 
 */
package com.innova4j.api.masters.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.innova4j.api.masters.services.MasterService;
import com.innova4j.api.masters.services.MasterTypeService;
import com.innova4j.api.masters.services.impl.MasterServiceImpl;
import com.innova4j.api.masters.services.impl.MasterTypeServiceImpl;

/**
 * @author innova4j-team
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.innova4j.api.masters.dao" })
@EntityScan(basePackages = { "com.innova4j.api.masters.domain" })
@EnableTransactionManagement
public class MastersConfiguration {

	@Bean
	public MasterService masterService() {
		return new MasterServiceImpl();
	}

	@Bean
	public MasterTypeService masterTypeService() {
		return new MasterTypeServiceImpl();
	}

}
