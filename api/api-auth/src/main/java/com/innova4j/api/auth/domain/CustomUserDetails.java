/**
 * 
 */
package com.innova4j.api.auth.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innova4j.api.auth.dto.AuthUserDto;

/**
 * @author alobaton
 *
 */
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = -4402146711326898924L;

	private AuthUserDto user;

	/**
	 * Create custom user details from AuthUser
	 * 
	 * @param user
	 */
	public CustomUserDetails(@NotNull AuthUserDto user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<String> roles = this.user.getRoles();
		if (roles == null) {
			return Collections.emptyList();
		}

		return roles.stream().map(role -> (GrantedAuthority) () -> role).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return user.getNickname();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired
	 * ()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked(
	 * )
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !this.user.isLocked();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}

}