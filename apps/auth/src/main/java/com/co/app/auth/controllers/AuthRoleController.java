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

import com.co.app.auth.dto.AuthRoleDto;
import com.co.app.auth.services.role.AuthRoleService;
import com.co.app.commons.controllers.BasePagedController;
import com.co.app.commons.dto.PageableQueryDto;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/roles")
public class AuthRoleController implements BasePagedController<AuthRoleDto> {

	@Autowired
	private AuthRoleService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#create(java.lang.Object)
	 */
	@Override
	public AuthRoleDto create(@Valid @RequestBody AuthRoleDto dto) {
		return service.create(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#get(java.lang.String)
	 */
	@Override
	public AuthRoleDto get(@PathVariable String id) {
		return service.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#getAll(java.lang.Object)
	 */
	@Override
	public List<AuthRoleDto> getAll(Optional<AuthRoleDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#update(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public AuthRoleDto update(@PathVariable String id, @Valid @RequestBody AuthRoleDto dto) {
		dto.setId(id);

		return service.update(dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#delete(java.lang.String)
	 */
	@Override
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.co.app.commons.controllers.BasePagedController#getAll(java.lang.Object,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<AuthRoleDto> getAll(@RequestBody PageableQueryDto<AuthRoleDto> request) {
		if (request.getDto().isPresent()) {
			AuthRoleDto dto = request.getDto().get();
			return service.getAll(dto, request.getPageable().getPageable());
		}

		return service.getAll(request.getPageable().getPageable());
	}

}
