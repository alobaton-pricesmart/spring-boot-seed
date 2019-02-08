/**
 * 
 */
package com.innova4j.api.auth.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.user.AuthUserService;
import com.innova4j.api.commons.controllers.BasePagedController;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/users")
public class AuthUserController implements BasePagedController<AuthUserDto> {

	@Autowired
	private AuthUserService service;

	@Override
	public AuthUserDto create(@Valid @RequestBody AuthUserDto dto) {
		return service.create(dto);
	}

	@Override
	public AuthUserDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	public List<AuthUserDto> getAll(@Valid @RequestBody AuthUserDto dto) {
		return service.getAll(dto);
	}

	@Override
	public AuthUserDto update(@PathVariable String id, @Valid @RequestBody AuthUserDto dto) {
		dto.setNickname(id);
		return service.update(dto);
	}

	@Override
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	public Page<AuthUserDto> getAll(@Valid @RequestBody AuthUserDto dto, @NotNull Pageable pageable) {
		return service.getAll(dto, pageable);
	}

}
