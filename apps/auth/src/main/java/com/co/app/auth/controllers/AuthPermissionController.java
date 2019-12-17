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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.domain.AuthPermission;
import com.co.app.auth.dto.AuthPermissionDto;
import com.co.app.auth.services.permission.AuthPermissionService;
import com.co.app.commons.controllers.BasePagedController;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/permissions")
public class AuthPermissionController implements BasePagedController<AuthPermissionDto> {

	@Autowired
	private AuthPermissionService service;

	@Override
	@PreAuthorize("customHasPermission('read:permission')")
	public AuthPermissionDto get(String id) {
		return AuthPermissionDto.CONVERTER.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('read:permissions')")
	public List<AuthPermissionDto> getAll(Predicate predicate) {
		return service.getAll(predicate).stream().map(AuthPermissionDto.CONVERTER)
				.collect(Collectors.<AuthPermissionDto>toList());
	}

	@Override
	@PreAuthorize("customHasPermission('read:permissions')")
	public Page<AuthPermissionDto> getAll(Predicate predicate, Pageable pageable, boolean isPaged) {
		Page<AuthPermission> page = service.getAll(predicate, isPaged ? pageable : Pageable.unpaged());

		return new PageImpl<>(page.getContent().stream().map(AuthPermissionDto.CONVERTER)
				.collect(Collectors.<AuthPermissionDto>toList()), pageable, page.getTotalElements());
	}

	@Override
	@PreAuthorize("customHasPermission('craete:permission')")
	public AuthPermissionDto create(@Valid AuthPermissionDto dto) {
		return AuthPermissionDto.CONVERTER.apply(service.create(AuthPermission.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('update:permission')")
	public AuthPermissionDto update(String id, @Valid AuthPermissionDto dto) {
		dto.setId(id);

		return AuthPermissionDto.CONVERTER.apply(service.update(AuthPermission.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:permission')")
	public void delete(String id) {
		service.delete(id);

	}

}
