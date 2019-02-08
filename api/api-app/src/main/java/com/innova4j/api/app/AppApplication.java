package com.innova4j.api.app;

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
@SpringBootApplication(scanBasePackages = { "com.innova4j.api.app", "com.innova4j.api.commons",
		"com.innova4j.api.email", "com.innova4j.api.message", "com.innova4j.api.auth", "com.innova4j.api.masters",
		"com.innova4j.api.settings" })
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
