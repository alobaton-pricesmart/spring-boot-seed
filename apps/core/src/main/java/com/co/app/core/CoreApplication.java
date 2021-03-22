package com.co.app.core;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

import com.co.app.commons.utils.DateUtil;
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
@SpringBootApplication()
public class CoreApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CoreApplication.class);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(DateUtil.ZONE_ID));
	}
}
