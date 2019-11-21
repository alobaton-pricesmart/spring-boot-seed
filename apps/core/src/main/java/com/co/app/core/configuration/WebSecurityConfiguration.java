/**
 * 
 */
package com.co.app.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall fireWall = new StrictHttpFirewall();
		fireWall.setAllowUrlEncodedSlash(true);
		fireWall.setAllowUrlEncodedPercent(true);
		return fireWall;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/actuator/**").antMatchers("/v2/api-docs", "/configuration/**", "/error",
				"/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**");

		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}

}
