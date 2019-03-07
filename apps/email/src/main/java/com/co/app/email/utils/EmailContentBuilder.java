/**
 * 
 */
package com.co.app.email.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author alobaton
 *
 */
public class EmailContentBuilder {

	private TemplateEngine emailTemplateEngine;
	private Map<String, String> parameters;
	private String template;

	public EmailContentBuilder() {
		this.parameters = new HashMap<String, String>();
	}

	public EmailContentBuilder emailTemplateEngine(@NotNull TemplateEngine emailTemplateEngine) {
		this.emailTemplateEngine = emailTemplateEngine;
		return this;
	}

	public EmailContentBuilder parameters(@NotNull Map<String, String> parameters) {
		this.parameters = parameters;
		return this;
	}

	public EmailContentBuilder parameter(@NotNull String key, @NotNull String value) {
		this.parameters.put(key, value);
		return this;
	}

	public EmailContentBuilder template(@NotNull String template) {
		this.template = template;
		return this;
	}

	public String build() {
		Context context = new Context();

		for (Entry<String, String> entry : parameters.entrySet()) {
			context.setVariable(entry.getKey(), entry.getValue());
		}

		return emailTemplateEngine.process(template, context);
	}
}
