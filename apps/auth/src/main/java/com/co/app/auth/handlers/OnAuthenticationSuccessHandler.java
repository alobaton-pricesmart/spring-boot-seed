/**
 * 
 */
package com.co.app.auth.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @author alobaton
 *
 */
public class OnAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO: Store session at distributed memory API...

		// Set response status...
		response.setStatus(HttpServletResponse.SC_OK);
	}
}