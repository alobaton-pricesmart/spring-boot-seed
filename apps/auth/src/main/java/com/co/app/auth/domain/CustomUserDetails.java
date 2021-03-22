/**
 * 
 */
package com.co.app.auth.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.domain.AuthUserRole;

/**
 * @author alobaton
 *
 */
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = -1L;

	private AuthUser authUser;

	/**
	 * Create custom user details from AuthUser
	 * 
	 * @param user
	 */
	public CustomUserDetails(@NotNull AuthUser authUser) {
		this.authUser = authUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<AuthUserRole> authUserRoleList = this.authUser.getAuthUserRoleList();
		if (authUserRoleList == null || authUserRoleList.isEmpty()) {
			return Collections.emptyList();
		}

		return authUserRoleList.stream()
				.map(authUserRole -> (GrantedAuthority) () -> authUserRole.getAuthUserRolePk().getRoleId())
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return authUser.getPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return authUser.getNickname();
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
		return !this.authUser.isLocked();
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
		return this.authUser.isEnabled();
	}

}