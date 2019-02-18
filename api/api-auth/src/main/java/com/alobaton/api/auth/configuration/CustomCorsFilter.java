/**
 * 
 */
package com.alobaton.api.auth.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author alobaton
 *
 */
@Component
public class CustomCorsFilter extends GenericFilterBean {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse rs = (HttpServletResponse) response;
		HttpServletRequest rq = (HttpServletRequest) request;
		rs.setHeader("Access-Control-Allow-Origin", "*");
		rs.setHeader("Access-Control-Allow-Credentials", "true");
		rs.setHeader("Access-Control-Max-Age", "3600");
		rs.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, OPTIONS");
		rs.setHeader("Access-Control-Allow-Headers", "*");

		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(rq.getMethod())) {
			rs.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		chain.doFilter(request, response);
	}
}