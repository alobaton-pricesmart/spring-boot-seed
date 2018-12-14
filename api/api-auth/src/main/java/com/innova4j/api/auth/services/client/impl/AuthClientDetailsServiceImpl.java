/**
 * 
 */
package com.innova4j.api.auth.services.client.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.innova4j.api.auth.domain.AuthClientDetails;
import com.innova4j.api.auth.dto.AuthClientDetailsDto;
import com.innova4j.api.auth.services.client.AuthClientDetailsService;

/**
 * @author innova4j-team
 *
 */
public class AuthClientDetailsServiceImpl implements AuthClientDetailsService {

	@Autowired
	private CustomClientDetailsService service;

	@Override
	public AuthClientDetailsDto create(AuthClientDetailsDto dto) {
		ClientDetails domain = AuthClientDetails.CONVERTER.apply(dto);

		service.addClientDetails(domain);

		return dto;
	}

	@Override
	public AuthClientDetailsDto get(String id) {
		AuthClientDetails domain = (AuthClientDetails) service.loadClientByClientId(id);

		return AuthClientDetailsDto.CONVERTER.apply(domain);
	}

	@Override
	public AuthClientDetailsDto customGet(@NotNull AuthClientDetailsDto dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<AuthClientDetailsDto> getAll() {
		return service.listClientDetails().stream().map(AuthClientDetailsDto.CONVERTER)
				.collect(Collectors.<AuthClientDetailsDto>toList());
	}

	@Override
	public List<AuthClientDetailsDto> getAll(AuthClientDetailsDto dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthClientDetailsDto update(AuthClientDetailsDto dto) {
		service.updateClientDetails(AuthClientDetails.CONVERTER.apply(dto));

		return get(dto.getClientId());
	}

	@Override
	public AuthClientDetailsDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public AuthClientDetailsDto delete(String id) {
		AuthClientDetailsDto dto = AuthClientDetailsDto.CONVERTER.apply(service.loadClientByClientId(id));

		service.removeClientDetails(id);

		return dto;
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

}
