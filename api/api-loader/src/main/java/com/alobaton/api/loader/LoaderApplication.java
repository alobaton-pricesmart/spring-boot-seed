/**
 * 
 */
package com.alobaton.api.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alobaton
 *
 */
@SpringBootApplication(scanBasePackages = { "com.alobaton.api.loader" })
public class LoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoaderApplication.class, args);
	}
}
