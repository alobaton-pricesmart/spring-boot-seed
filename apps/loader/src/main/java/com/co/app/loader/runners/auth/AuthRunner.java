/**
 * 
 */
package com.co.app.loader.runners.auth;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.co.app.loader.runners.auth.loaders.AuthClientDetailsLoader;
import com.co.app.loader.runners.auth.loaders.AuthUserLoader;

/**
 * @author alobaton
 *
 */
@Component
@Order(1)
public class AuthRunner implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(AuthRunner.class);

	@Autowired
	private AuthClientDetailsLoader clientDetailsLoader;

	@Autowired
	private AuthUserLoader userLoader;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		String[] resourceIds = new String[] { "api", "admin" };

		// Load API client
		String[] grantTypes = new String[] { "authorization_code" };
		String[] authorities = new String[] { "ADMIN" };
		String[] scopes = new String[] { "read", "write" };
		String[] redirectUri = new String[] { "https://www.google.com/" };
		clientDetailsLoader.load("api-client", "kIQwOMQz5s82uSc3KWZF1vgOVCpUTxWp", resourceIds, grantTypes, authorities,
				scopes, redirectUri);
		LOGGER.info("API client loaded");

		// Load admin user
		userLoader.load("admin", "Administrator", StringUtils.EMPTY, "admin@domain.com", "d2PJNVXG", "ADMIN");
		LOGGER.info("Admin user loaded");
	}

}
