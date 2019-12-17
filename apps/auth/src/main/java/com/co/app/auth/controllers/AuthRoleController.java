/**
 * 
 */
package com.co.app.auth.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.domain.AuthRole;
import com.co.app.auth.dto.AuthRoleDto;
import com.co.app.auth.services.role.AuthRoleService;
import com.co.app.commons.controllers.BasePagedController;
import com.querydsl.core.types.Predicate;

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
	 * @see com.co.app.commons.controllers.BaseController#get(java.lang.String)
	 */
	@Override
	@PreAuthorize("customHasPermission('read:role')")
	public AuthRoleDto get(@PathVariable String id) {
		return AuthRoleDto.CONVERTER.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('read:roles')")
	public List<AuthRoleDto> getAll(Predicate predicate) {
		return service.getAll(predicate).stream().map(AuthRoleDto.CONVERTER).collect(Collectors.<AuthRoleDto>toList());
	}

	@Override
	@PreAuthorize("customHasPermission('read:roles')")
	public Page<AuthRoleDto> getAll(Predicate predicate, Pageable pageable, boolean isPaged) {
		Page<AuthRole> page = service.getAll(predicate, isPaged ? pageable : Pageable.unpaged());

		return new PageImpl<>(
				page.getContent().stream().map(AuthRoleDto.CONVERTER).collect(Collectors.<AuthRoleDto>toList()),
				pageable, page.getTotalElements());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#create(java.lang.Object)
	 */
	@Override
	@PreAuthorize("customHasPermission('craete:role')")
	public AuthRoleDto create(@Valid @RequestBody AuthRoleDto dto) {
		return AuthRoleDto.CONVERTER.apply(service.create(AuthRole.CONVERTER.apply(dto)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#update(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	@PreAuthorize("customHasPermission('update:role')")
	public AuthRoleDto update(@PathVariable String id, @Valid @RequestBody AuthRoleDto dto) {
		dto.setId(id);
		return AuthRoleDto.CONVERTER.apply(service.update(AuthRole.CONVERTER.apply(dto)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.co.app.commons.controllers.BaseController#delete(java.lang.String)
	 */
	@Override
	@PreAuthorize("customHasPermission('delete:role')")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

}
