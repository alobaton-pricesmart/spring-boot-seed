/**
 * 
 */
package com.innova4j.api.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.innova4j.api.masters.configuration.MastersConfiguration;
import com.innova4j.api.settings.configuration.SettingsConfiguration;

/**
 * @author innova4j-team
 *
 */
@Configuration
//Import your app modules here...
@Import({ MastersConfiguration.class, SettingsConfiguration.class })
public class AppConfiguration {
	/**
	 * Put your beans here...
	 */
}
