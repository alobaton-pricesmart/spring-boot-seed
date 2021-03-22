/**
 * 
 */
package com.co.app.auth.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.app.auth.domain.CustomClientDetails;
import com.co.app.auth.services.AuthClientDetailsService;
import com.co.app.commons.domain.AuthClientDetails;
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
		CustomClientDetails customClientDetails = new CustomClientDetails(domain);
		service.addClientDetails(customClientDetails);
		return domain;
	}

	@Override
	public AuthClientDetails get(String id) {
		CustomClientDetails customClientDetails = (CustomClientDetails) service.loadClientByClientId(id);
		return CustomClientDetails.CONVERTER_ENTITY.apply(customClientDetails);
	}

	@Override
	public AuthClientDetails update(AuthClientDetails domain) {
		CustomClientDetails customClientDetails = new CustomClientDetails(domain);
		service.updateClientDetails(customClientDetails);
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
		return service.listClientDetails().stream().map(CustomClientDetails.CONVERTER_ENTITY)
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
