/**
 * 
 */
package com.co.app.auth.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.services.AuthClientDetailsService;
import com.co.app.commons.controllers.BaseController;
import com.co.app.commons.domain.AuthClientDetails;
import com.co.app.commons.dto.AuthClientDetailsDto;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/clients")
public class AuthClientController implements BaseController<AuthClientDetailsDto, String> {

	@Autowired
	private AuthClientDetailsService service;

	@Override
	@PreAuthorize("customHasPermission('read:client')")
	public AuthClientDetailsDto get(@PathVariable String id) {
		return AuthClientDetailsDto.CONVERTER_DTO.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('read:clients')")
	public List<AuthClientDetailsDto> getAll(@QuerydslPredicate(root = AuthClientDetails.class) Predicate predicate,
			@RequestParam Map<String, String> requestParams) {
		return service.getAll(predicate).stream().map(AuthClientDetailsDto.CONVERTER_DTO)
				.collect(Collectors.<AuthClientDetailsDto>toList());
	}

	@Override
	@PreAuthorize("customHasPermission('craete:client')")
	public AuthClientDetailsDto create(@Valid @RequestBody AuthClientDetailsDto dto) {
		return AuthClientDetailsDto.CONVERTER_DTO
				.apply(service.create(AuthClientDetailsDto.CONVERTER_ENTITY.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('update:client')")
	public AuthClientDetailsDto update(@PathVariable String id, @Valid @RequestBody AuthClientDetailsDto dto) {
		dto.setClientId(id);
		return AuthClientDetailsDto.CONVERTER_DTO
				.apply(service.update(AuthClientDetailsDto.CONVERTER_ENTITY.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:client')")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

}
