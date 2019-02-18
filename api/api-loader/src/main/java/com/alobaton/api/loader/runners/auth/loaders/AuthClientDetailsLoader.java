/**
 * 
 */
package com.alobaton.api.loader.runners.auth.loaders;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alobaton.api.auth.dao.AuthClientDetailsRepository;
import com.alobaton.api.auth.domain.AuthClientDetails;

/**
 * @author alobaton
 *
 */
@Component
public class AuthClientDetailsLoader {

	@Autowired
	private AuthClientDetailsRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Async
	public void load(String id, String secret, String[] resourceIds, String[] grantTypes, String[] authorities,
			String[] scopes) {
		// Delete preiovus data.
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}

		// Build client details.
		AuthClientDetails details = new AuthClientDetails();

		details.setClientId(id);
		details.setResourceIds(new HashSet<String>(Arrays.asList(resourceIds)));
		details.setSecretRequired(Boolean.TRUE);
		details.setClientSecret(passwordEncoder.encode(secret));
		details.setScoped(Boolean.TRUE);
		details.setScope(new HashSet<String>(Arrays.asList(scopes)));
		details.setAutoApprove(Boolean.TRUE);
		details.setAuthorizedGrantTypes(new HashSet<String>(Arrays.asList(grantTypes)));
		details.setAuthorities(new HashSet<String>(Arrays.asList(authorities)));

		repository.save(details);
	}
}
