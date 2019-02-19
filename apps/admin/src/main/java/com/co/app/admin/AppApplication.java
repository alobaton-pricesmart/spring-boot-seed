package com.co.app.admin;

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
@SpringBootApplication(scanBasePackages = { "com.co.app.admin", "com.co.app.commons",
		"com.co.app.email", "com.co.app.message", "com.co.app.auth", "com.co.app.masters",
		"com.co.app.settings" })
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
