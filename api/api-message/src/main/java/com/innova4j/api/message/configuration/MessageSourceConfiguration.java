/**
 * 
 */
package com.innova4j.api.message.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.innova4j.api.message.MessageConstants;

/**
 * @author alobaton
 *
 */
// Import this configuration in your main module.
// The message source is instantiated at this level to decouple the administration of language literals.
@Configuration
public class MessageSourceConfiguration {
	private static final String[] BASENAMES = new String[] { "classpath:messages/error/messages" };

	/**
	 * 
	 * @return The message source.
	 */
	public MessageSource messageSource() {
		Locale.setDefault(new Locale(MessageConstants.DEFAULT_LOCALE));

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

		messageSource.setBasenames(BASENAMES);
		return messageSource;
	}

	/**
	 * Put your beans here...
	 */
}
