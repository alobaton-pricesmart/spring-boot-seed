/**
 * 
 */
package com.co.app.auth.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.domain.AuthClientDetails;
import com.co.app.auth.dto.AuthClientDetailsDto;
import com.co.app.auth.services.client.AuthClientDetailsService;
import com.co.app.commons.controllers.BaseController;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/clients")
public class AuthClientController implements BaseController<AuthClientDetailsDto> {

	@Autowired
	private AuthClientDetailsService service;

	@Override
	@PreAuthorize("customHasPermission('craete:client')")
	public AuthClientDetailsDto create(@Valid @RequestBody AuthClientDetailsDto dto) {
		return AuthClientDetailsDto.CONVERTER
				.apply(service.create((AuthClientDetails) AuthClientDetails.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('read:client')")
	public AuthClientDetailsDto get(@PathVariable String id) {
		return AuthClientDetailsDto.CONVERTER.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('update:client')")
	public AuthClientDetailsDto update(@PathVariable String id, @Valid @RequestBody AuthClientDetailsDto dto) {
		dto.setClientId(id);
		return AuthClientDetailsDto.CONVERTER
				.apply(service.update((AuthClientDetails) AuthClientDetails.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:client')")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	public List<AuthClientDetailsDto> getAll(Predicate predicate) {
		return service.getAll(predicate).stream().map(AuthClientDetailsDto.CONVERTER)
				.collect(Collectors.<AuthClientDetailsDto>toList());
	}
}
