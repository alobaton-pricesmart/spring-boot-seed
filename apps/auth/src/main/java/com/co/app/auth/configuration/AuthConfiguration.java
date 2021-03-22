/**
 * 
 */
package com.co.app.auth.configuration;

import java.util.Locale;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.co.app.email.configuration.ThymeleafConfiguration;
import com.co.app.email.utils.EmailServiceFactoryObject;
import com.co.app.message.configuration.MessageSourceConfiguration;
import com.co.app.message.service.MessageService;
import com.co.app.message.service.impl.MessageServiceImpl;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = { "com.co.app.auth.repository" })
@EntityScan(basePackages = { "com.co.app.commons.domain", "com.co.app.commons.converter" })
//Import your property sources here...
//Import your app modules here...
@Import({ MessageSourceConfiguration.class, ThymeleafConfiguration.class })
public class AuthConfiguration {

	@Bean
	public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

	@Bean
	public LocaleResolver localResolver() {
		SessionLocaleResolver localResolver = new SessionLocaleResolver();
		localResolver.setDefaultLocale(new Locale("es", "CO"));
		return localResolver;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}

	@Bean
	public EmailServiceFactoryObject emailServiceFactoryObject() {
		return new EmailServiceFactoryObject();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
