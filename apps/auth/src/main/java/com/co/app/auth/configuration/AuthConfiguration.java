/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.co.app.email.utils.EmailServiceFactoryObject;
import com.co.app.message.configuration.MessageSourceConfiguration;
import com.co.app.message.service.MessageService;
import com.co.app.message.service.impl.MessageServiceImpl;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.co.app.auth.dao" })
@EntityScan(basePackages = { "com.co.app.auth.domain" })
@EnableTransactionManagement
@Import({ MessageSourceConfiguration.class })
public class AuthConfiguration {
	/**
	 * Put your beans here...
	 */

	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}

	@Bean
	public EmailServiceFactoryObject emailServiceFactoryObject() {
		return new EmailServiceFactoryObject();
	}
}
