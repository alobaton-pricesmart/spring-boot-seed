package com.innova4j.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author innova4j-team
 *
 */
// Exclude EmbeddedMongoAutoConfiguration since it is used only for tests.
// @SpringBootApplication(exclude = { EmbeddedMongoAutoConfiguration.class })
// Put your base packages here...
@ComponentScan(lazyInit = true, basePackages = { "com.innova4j.api.auth.controllers",
		"com.innova4j.api.masters.controllers", "com.innova4j.api.settings.controllers" })
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
