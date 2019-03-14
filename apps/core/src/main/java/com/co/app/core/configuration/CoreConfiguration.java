/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.co.app.email.service.EmailService;
import com.co.app.email.service.impl.EmailServiceImpl;
import com.co.app.masters.configuration.MastersConfiguration;
import com.co.app.settings.configuration.SettingsConfiguration;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableJpaAuditing
// Import your property sources here...
@PropertySource("classpath:/auth/auth.properties")
@PropertySource("classpath:/email/email.properties")
//Import your app modules here...
@Import({ MastersConfiguration.class, SettingsConfiguration.class })
public class CoreConfiguration {
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
