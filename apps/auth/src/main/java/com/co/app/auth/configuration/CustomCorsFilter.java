/**
 * 
 */
package com.co.app.auth.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author alobaton
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Profile("dev")
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
		// NOTA: Las siguientes lineas deberá habilitarlas, solo y unicamente si esta
		// trabajando de manera local sin usar la arquitectura Netflix. Ya que el
		// Gateway se encarga de administrar los CORS en ese caso.
		rs.setHeader("Access-Control-Allow-Origin", "*");
		rs.setHeader("Access-Control-Allow-Credentials", "true");
		rs.setHeader("Access-Control-Max-Age", "3600");
		rs.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE,OPTIONS,HEAD");
		rs.setHeader("Access-Control-Allow-Headers", "*");

		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(rq.getMethod())) {
			rs.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(rq, rs);
		}
	}

}