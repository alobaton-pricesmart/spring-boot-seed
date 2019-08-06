/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.co.app.masters.configuration.MastersConfiguration;
import com.co.app.settings.configuration.SettingsConfiguration;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
// Import your property sources here...
@PropertySource("classpath:/auth/auth.properties")
@PropertySource("classpath:/email/email.properties")
//Import your app modules here...
@Import({ MastersConfiguration.class, SettingsConfiguration.class })
public class CoreConfiguration {
}
