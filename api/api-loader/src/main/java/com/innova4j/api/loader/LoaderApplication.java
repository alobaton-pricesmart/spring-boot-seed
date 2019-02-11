/**
 * 
 */
package com.innova4j.api.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alobaton
 *
 */
@SpringBootApplication(scanBasePackages = { "com.innova4j.api.loader" })
public class LoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoaderApplication.class, args);
	}
}
