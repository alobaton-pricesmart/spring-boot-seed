package com.co.app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

import com.co.app.core.configuration.RibbonConfiguration;

/**
 * 
 * @author alobaton
 *
 */
@EnableDiscoveryClient
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
//Exclude EmbeddedMongoAutoConfiguration since it is used only for tests.
//@SpringBootApplication(exclude = { EmbeddedMongoAutoConfiguration.class })
//Put your base packages of your modules here...
@SpringBootApplication(scanBasePackages = { "com.co.app.core", "com.co.app.commons", "com.co.app.email",
		"com.co.app.message" })
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
