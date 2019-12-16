/**
 * 
 */
package com.co.app.auth.services.client.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.app.auth.domain.AuthClientDetails;
import com.co.app.auth.services.client.AuthClientDetailsService;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@Service
public class AuthClientDetailsServiceImpl implements AuthClientDetailsService {

	@Autowired
	private CustomClientDetailsService service;

	@Override
	public AuthClientDetails create(AuthClientDetails domain) {
		service.addClientDetails(domain);

		return domain;
	}

	@Override
	public AuthClientDetails get(String id) {
		AuthClientDetails domain = (AuthClientDetails) service.loadClientByClientId(id);

		return domain;
	}

	@Override
	public AuthClientDetails update(AuthClientDetails domain) {
		service.updateClientDetails(domain);

		return get(domain.getClientId());
	}

	@Override
	public boolean exists(String id) {
		try {
			service.loadClientByClientId(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<AuthClientDetails> getAll(Predicate predicate) {
		return service.listClientDetails().stream().map(clientDetails -> (AuthClientDetails) clientDetails)
				.collect(Collectors.<AuthClientDetails>toList());
	}

	@Override
	public AuthClientDetails customUpdate(Map<String, Object> model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthClientDetails delete(String id) {
		AuthClientDetails domain = get(id);

		service.removeClientDetails(id);

		return domain;
	}

}
