package com.co.app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author alobaton
 *
 */
//Exclude EmbeddedMongoAutoConfiguration since it is used only for tests.
//@SpringBootApplication(exclude = { EmbeddedMongoAutoConfiguration.class })
//Put your base packages of your modules here...
@SpringBootApplication(scanBasePackages = { "com.co.app.core", "com.co.app.commons", "com.co.app.email",
		"com.co.app.message", "com.co.app.auth", "com.co.app.masters", "com.co.app.settings", "com.co.app.memory" })
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
