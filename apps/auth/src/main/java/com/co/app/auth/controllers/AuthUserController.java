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
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.domain.AuthUser;
import com.co.app.auth.dto.AuthUserDto;
import com.co.app.auth.services.user.AuthUserService;
import com.co.app.commons.controllers.BasePagedController;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/users")
public class AuthUserController implements BasePagedController<AuthUserDto> {

	@Autowired
	private AuthUserService service;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@PreAuthorize("customHasPermission('create:user')")
	public AuthUserDto create(@Valid @RequestBody AuthUserDto dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		return AuthUserDto.CONVERTER.apply(service.create(AuthUser.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('read:user')")
	public AuthUserDto get(@PathVariable String id) {
		return AuthUserDto.CONVERTER.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('update:user')")
	public AuthUserDto update(@PathVariable String id, @Valid @RequestBody AuthUserDto dto) {
		dto.setNickname(id);
		return AuthUserDto.CONVERTER.apply(service.update(AuthUser.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:user')")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	@PreAuthorize("customHasPermission('read:users')")
	public Page<AuthUserDto> getAll(@QuerydslPredicate(root = AuthUser.class) Predicate predicate, Pageable pageable,
			@RequestParam(required = false) boolean isPaged) {
		Page<AuthUser> page = service.getAll(predicate, isPaged ? pageable : Pageable.unpaged());

		return new PageImpl<>(
				page.getContent().stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList()),
				pageable, page.getTotalElements());
	}

	@Override
	public List<AuthUserDto> getAll(Predicate predicate) {
		return service.getAll(predicate).stream().map(AuthUserDto.CONVERTER).collect(Collectors.<AuthUserDto>toList());
	}

}
