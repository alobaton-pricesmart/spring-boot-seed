/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.co.app.message.configuration.MessageSourceConfiguration;
import com.co.app.message.service.MessageService;
import com.co.app.message.service.impl.MessageServiceImpl;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
// Import your property sources here...
//Import your app modules here...
@Import({ MessageSourceConfiguration.class })
public class CoreConfiguration {
	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}
}
