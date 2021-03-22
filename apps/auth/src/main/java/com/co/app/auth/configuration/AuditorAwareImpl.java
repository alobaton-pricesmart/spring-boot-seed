package com.co.app.auth.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.co.app.auth.utils.LoggedUserUtil;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String username = LoggedUserUtil.getUsername();
		return Optional.of(username);
	}
}