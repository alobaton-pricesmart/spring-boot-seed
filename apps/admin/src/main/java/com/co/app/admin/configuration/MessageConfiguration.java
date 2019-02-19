/**
 * 
 */
package com.co.app.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.co.app.message.configuration.MessageSourceConfiguration;
import com.co.app.message.service.MessageService;
import com.co.app.message.service.impl.MessageServiceImpl;

/**
 * @author alobaton
 *
 */
@Configuration
@Import(MessageSourceConfiguration.class)
public class MessageConfiguration {
	/**
	 * Put your beans here...
	 */

	/**
	 * Override with your custom message service implementation here...
	 * 
	 * @return
	 */
	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}

}
