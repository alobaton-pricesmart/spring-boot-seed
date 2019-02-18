package com.alobaton.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author alobaton
 *
 */
// Exclude EmbeddedMongoAutoConfiguration since it is used only for tests.
// @SpringBootApplication(exclude = { EmbeddedMongoAutoConfiguration.class })
//Put your base packages of your modules here...
@SpringBootApplication(scanBasePackages = { "com.alobaton.api.app", "com.innova4j.api.commons",
		"com.alobaton.api.email", "com.alobaton.api.message", "com.alobaton.api.auth", "com.alobaton.api.masters",
		"com.alobaton.api.settings" })
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
