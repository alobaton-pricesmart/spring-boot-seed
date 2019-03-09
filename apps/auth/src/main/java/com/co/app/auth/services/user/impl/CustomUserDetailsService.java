/**
 * 
 */
package com.co.app.auth.services.user.impl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.co.app.auth.dao.AuthUserRepository;
import com.co.app.auth.domain.AuthUser;
import com.co.app.auth.domain.CustomUserDetails;
import com.co.app.commons.exception.RegisterNotFoundException;

/**
 * @author alobaton
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		AuthUser user = new AuthUser();
		user.setNickname(username);

		Example<AuthUser> example = Example.of(user);

		user = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(AuthUser.class, Strings.EMPTY, username));
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Username %s not found", username));
		}

		CustomUserDetails userDetails = new CustomUserDetails(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return userDetails;
	}
}
