/**
 * 
 */
package com.co.app.auth.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.services.AuthPermissionService;
import com.co.app.commons.controllers.BasePagedController;
import com.co.app.commons.domain.AuthPermission;
import com.co.app.commons.dto.AuthPermissionDto;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/permissions")
public class AuthPermissionController implements BasePagedController<AuthPermissionDto, String> {

	@Autowired
	private AuthPermissionService service;

	@Override
	@PreAuthorize("customHasPermission('read:permission')")
	public AuthPermissionDto get(String id) {
		return AuthPermissionDto.CONVERTER_DTO.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('read:permissions')")
	public List<AuthPermissionDto> getAll(@QuerydslPredicate(root = AuthPermission.class) Predicate predicate,
			@RequestParam Map<String, String> requestParams) {
		return service.getAll(predicate).stream().map(AuthPermissionDto.CONVERTER_DTO)
				.collect(Collectors.<AuthPermissionDto>toList());
	}

	@Override
	@PreAuthorize("customHasPermission('read:permissions')")
	public Page<AuthPermissionDto> getAll(@QuerydslPredicate(root = AuthPermission.class) Predicate predicate,
			@RequestParam Map<String, String> requestParams, Pageable pageable) {
		Page<AuthPermission> page = service.getAll(predicate, pageable);

		return new PageImpl<>(page.getContent().stream().map(AuthPermissionDto.CONVERTER_DTO)
				.collect(Collectors.<AuthPermissionDto>toList()), pageable, page.getTotalElements());
	}

	@Override
	@PreAuthorize("customHasPermission('craete:permission')")
	public AuthPermissionDto create(@Valid AuthPermissionDto dto) {
		return AuthPermissionDto.CONVERTER_DTO.apply(service.create(AuthPermissionDto.CONVERTER_ENTITY.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('update:permission')")
	public AuthPermissionDto update(String id, @Valid AuthPermissionDto dto) {
		dto.setId(id);

		return AuthPermissionDto.CONVERTER_DTO.apply(service.update(AuthPermissionDto.CONVERTER_ENTITY.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:permission')")
	public void delete(String id) {
		service.delete(id);

	}

}
