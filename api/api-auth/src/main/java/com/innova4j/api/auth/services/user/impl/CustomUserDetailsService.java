/**
 * 
 */
package com.innova4j.api.auth.services.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.innova4j.api.auth.domain.CustomUserDetails;
import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.user.AuthUserService;

/**
 * @author innova4j-team
 *
 */
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUserDto user = new AuthUserDto();
		user.setNickname(username);

		user = service.customGet(user);
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
