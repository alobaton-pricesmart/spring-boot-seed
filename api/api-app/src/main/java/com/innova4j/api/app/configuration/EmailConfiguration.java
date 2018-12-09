/**
 * 
 */
package com.innova4j.api.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.innova4j.api.email.configuration.EmailThymeleafConfiguration;
import com.innova4j.api.email.service.EmailService;
import com.innova4j.api.email.service.impl.EmailServiceImpl;

/**
 * @author innova4j-team
 *
 */
@Configuration
@Import({ EmailThymeleafConfiguration.class, com.innova4j.api.email.configuration.EmailConfiguration.class })
@PropertySource("classpath:/email/email.properties")
public class EmailConfiguration {
	/**
	 * Put your beans here...
	 */

	/**
	 * Override with your custom email service implementation here...
	 * 
	 * @return
	 */
	@Bean
	public EmailService emailService() {
		return new EmailServiceImpl();
	}
}
