/**
 * 
 */
package com.co.app.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.csrf.CsrfFilter;

import com.co.app.auth.handlers.OnAuthenticationSuccessHandler;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String LOGIN = "/login";

	/**
	 * Put here your public access endpoints...
	 */
	private static final String[] PUBLIC_ACCESS = new String[] { "/webjars/**", "/css/**", "/js/**", "/img/**", "/",
			"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/actuator**", LOGIN, "/reset-password",
			"/update-password", "/oauth/authorize", "/oauth/confirm_access", "/oauth/recovery-password",
			"/oauth/register" };
	/**
	 * Put here your ADMIN access endpoints...
	 */
	private static final String[] ADMIN_ACCESS = new String[] { "/clients**" };
	/**
	 * Put here your client access endpoints...
	 */
	private static final String[] CLIENT_ACCESS = new String[] { "/oauth/register" };

	@Value("${resource.id}")
	private String resourceId;

	@Autowired
	private ExceptionHandlerFilter exceptionHandlerFilter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2
	 * .config.annotation.web.configurers.ResourceServerSecurityConfigurer)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		OnAuthenticationSuccessHandler successHandler = new OnAuthenticationSuccessHandler();

		// CORS...
		http.cors().and().csrf().disable().addFilterAfter(exceptionHandlerFilter, CsrfFilter.class);

		// Login and logout...
		http.formLogin().loginPage(LOGIN).permitAll()//
				.successHandler(successHandler).defaultSuccessUrl("/")//
				.and().logout().logoutSuccessUrl(LOGIN).permitAll()//
				// If a user try to access a resource without having enough permissions
				.and().exceptionHandling().accessDeniedPage(LOGIN);

		http.authorizeRequests()//
				// OAuth OPTIONS requests...
				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()//
				// Public access...
				.antMatchers(PUBLIC_ACCESS).permitAll()//
				// Set up admin access...
				.antMatchers(ADMIN_ACCESS).hasRole("ADMIN")//
				// Client credentials access...
				.antMatchers(CLIENT_ACCESS).access("#oauth2.isClient()")
				// Setup full authentication access...
				.anyRequest().authenticated();
	}

}
