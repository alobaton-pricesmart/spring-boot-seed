/**
 * 
 */
package com.innova4j.api.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.innova4j.api.email.service.EmailService;
import com.innova4j.api.email.service.impl.EmailServiceImpl;
import com.innova4j.api.masters.configuration.MastersConfiguration;
import com.innova4j.api.settings.configuration.SettingsConfiguration;

/**
 * @author alobaton
 *
 */
@Configuration
// Import your property sources here...
@PropertySource("classpath:/auth/auth.properties")
@PropertySource("classpath:/email/email.properties")
//Import your app modules here...
@Import({ MastersConfiguration.class, SettingsConfiguration.class })
public class AppConfiguration {
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
