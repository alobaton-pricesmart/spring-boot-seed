/**
 * 
 */
package com.co.app.auth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.co.app.auth.domain.CustomUserDetails;
import com.co.app.auth.repository.AuthUserRepository;
import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.message.constants.MessageConstants;

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
		AuthUser user = repository.findById(username).orElseThrow(
				() -> new RegisterNotFoundException(MessageConstants.ERROR_AUTH_INVALID_EMAIL_OR_PASSWORD));

		CustomUserDetails userDetails = new CustomUserDetails(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return userDetails;
	}
}
