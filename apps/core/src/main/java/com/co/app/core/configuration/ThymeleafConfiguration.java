/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * @author alobaton
 *
 */
@Configuration
public class ThymeleafConfiguration {

	@Bean("emailTemplateEngine")
	public SpringTemplateEngine emailTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		templateEngine.setEnableSpringELCompiler(true);

		// Support for thymeleaf layout dialect...
		templateEngine.addDialect(new LayoutDialect());
		// Support for thymlead security dialect...
		templateEngine.addDialect(new SpringSecurityDialect());

		return templateEngine;
	}

	@Bean("thymeleafTemplateResolver")
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		return templateResolver;
	}

}
