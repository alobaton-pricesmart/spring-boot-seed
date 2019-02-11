/**
 * 
 */
package com.innova4j.api.loader.runners.auth.loaders;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.innova4j.api.auth.dao.AuthUserRepository;
import com.innova4j.api.auth.domain.AuthUser;

/**
 * @author alobaton
 *
 */
@Component
public class AuthUserLoader {

	@Autowired
	private AuthUserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Async
	public void load(String nickname, String name, String lastName, String email, String password, String... role) {
		if (repository.existsById(nickname)) {
			repository.deleteById(nickname);
		}

		AuthUser user = new AuthUser();
		user.setNickname(nickname);
		user.setName(name);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setRoles(new HashSet<String>(Arrays.asList(role)));

		repository.save(user);
	}
}
