package com.co.app.auth.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class LoggedUserUtil {

	private LoggedUserUtil() {
	}

	public static String getToken() {
		OAuth2Authentication oAuth2Authentication = oauth2Authentication();
		if (oAuth2Authentication != null) {
			final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
			return details.getTokenValue();
		} else {
			return null;
		}
	}

	public static OAuth2Authentication oauth2Authentication() {
		return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
	}

	public static Set<String> getAuthorities() {
		OAuth2Authentication oauth = oauth2Authentication();
		if (oauth == null) {
			return Collections.emptySet();
		}
		Collection<GrantedAuthority> authorities = oauth.getAuthorities();
		return authorities == null ? Collections.emptySet()
				: authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
	}

	public static Boolean containsPermission(String permision) {
		Set<String> permissions = getAuthorities();
		return permissions.contains(permision);
	}

	public static String getUsername() {
		OAuth2Authentication oAuth2Authentication = oauth2Authentication();
		return oAuth2Authentication != null ? oAuth2Authentication.getName() : null;
	}
}
