/**
 * 
 */
package com.co.app.auth.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.dto.AuthUserDto;
import com.co.app.auth.services.user.AuthUserService;
import com.co.app.commons.controllers.BasePagedController;
import com.co.app.commons.dto.PageableQueryDto;

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
	public List<AuthUserDto> getAll(Optional<AuthUserDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();
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
	public Page<AuthUserDto> getAll(@RequestBody PageableQueryDto<AuthUserDto> request) {
		if (request.getDto().isPresent()) {
			AuthUserDto dto = request.getDto().get();
			return service.getAll(dto, request.getPageable().getPageable());
		}

		return service.getAll(request.getPageable().getPageable());
	}

}
