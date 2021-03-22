package com.co.app.auth.configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.RequestContextFilter;

import com.co.app.commons.dto.ExceptionMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExceptionHandlerFilter extends RequestContextFilter {
	private static final Log LOGGER = LogFactory.getLog(ExceptionHandlerFilter.class);

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			LOGGER.error(e);

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			ExceptionMessageDto exceptionMessageDto = mapper.readValue(e.getResponseBodyAsString(),
					ExceptionMessageDto.class);

			HttpStatus status = e.getStatusCode();
			exceptionMessageDto.setStatus(status.value());
			exceptionMessageDto.setError(status.name());
			exceptionMessageDto.setTimestamp(String.valueOf(System.currentTimeMillis()));
			exceptionMessageDto.setPath(request.getRequestURI());

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String traza = sw.toString();
			exceptionMessageDto.setTraza(traza);

			setHeaders(response);
			response.setStatus(status.value());
			response.getWriter().write(convertObjectToJson(exceptionMessageDto));
		} catch (Exception e) {
			LOGGER.error(e);

			ExceptionMessageDto exceptionMessageDto = ExceptionMessageDto.builder()
					.timestamp(String.valueOf(System.currentTimeMillis()))
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(HttpStatus.INTERNAL_SERVER_ERROR.name())
					.path(request.getRequestURI()).build();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String traza = sw.toString();
			exceptionMessageDto.setTraza(traza);

			setHeaders(response);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().write(convertObjectToJson(exceptionMessageDto));
		}
	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	private void setHeaders(HttpServletResponse response) {
		response.setHeader("Content-Type", "application/json");
		response.setHeader("Accept", "application/json");
	}
}