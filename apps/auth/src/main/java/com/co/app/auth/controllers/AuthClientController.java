/**
 * 
 */
package com.co.app.auth.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.dto.AuthClientDetailsDto;
import com.co.app.auth.services.client.AuthClientDetailsService;
import com.co.app.commons.controllers.BaseController;

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
	public AuthClientDetailsDto create(@Valid @RequestBody AuthClientDetailsDto dto) {
		return service.create(dto);
	}

	@Override
	public AuthClientDetailsDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	public List<AuthClientDetailsDto> getAll(@Valid @RequestParam Optional<AuthClientDetailsDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();
	}

	@Override
	public AuthClientDetailsDto update(@PathVariable String id, @Valid @RequestBody AuthClientDetailsDto dto) {
		dto.setClientId(id);
		return service.update(dto);
	}

	@Override
	public void delete(@PathVariable String id) {
		service.delete(id);
	}
}
