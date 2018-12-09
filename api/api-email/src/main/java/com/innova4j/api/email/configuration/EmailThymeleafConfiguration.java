/**
 * 
 */
package com.innova4j.api.email.configuration;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author innova4j-team
 *
 */
@Configuration
//The template engine is instantiated at this level to decouple the administration of language literals.
public class EmailThymeleafConfiguration {

	/**
	 * 
	 * @return the template engine.
	 */
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());

		return templateEngine;
	}

	/**
	 * 
	 * @return the template resolver.
	 */
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:templates");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		return templateResolver;
	}

}
