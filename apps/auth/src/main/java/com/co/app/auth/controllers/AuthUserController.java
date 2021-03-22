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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.auth.services.AuthUserService;
import com.co.app.commons.controllers.BasePagedController;
import com.co.app.commons.domain.AuthUser;
import com.co.app.commons.dto.AuthUserDto;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/users")
public class AuthUserController implements BasePagedController<AuthUserDto, String> {

	@Autowired
	private AuthUserService authUserService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@PreAuthorize("customHasPermission('read:users')")
	public Page<AuthUserDto> getAll(@QuerydslPredicate(root = AuthUser.class) Predicate predicate,
			@RequestParam Map<String, String> requestParams, Pageable pageable) {
		Page<AuthUser> page = authUserService.getAll(predicate, pageable);

		return new PageImpl<>(
				page.getContent().stream().map(AuthUserDto.CONVERTER_DTO).collect(Collectors.<AuthUserDto>toList()),
				pageable, page.getTotalElements());
	}

	@Override
	@PreAuthorize("customHasPermission('read:users')")
	public List<AuthUserDto> getAll(@QuerydslPredicate(root = AuthUser.class) Predicate predicate,
			@RequestParam Map<String, String> requestParams) {
		return authUserService.getAll(predicate).stream().map(AuthUserDto.CONVERTER_DTO)
				.collect(Collectors.<AuthUserDto>toList());
	}

	@Override
	@PreAuthorize("customHasPermission('read:user')")
	public AuthUserDto get(@PathVariable String id) {
		return AuthUserDto.CONVERTER_DTO.apply(authUserService.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('create:user')")
	public AuthUserDto create(@Valid @RequestBody AuthUserDto dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		return AuthUserDto.CONVERTER_DTO.apply(authUserService.create(AuthUserDto.CONVERTER_ENTITY.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('update:user')")
	public AuthUserDto update(@PathVariable String id, @Valid @RequestBody AuthUserDto dto) {
		dto.setNickname(id);
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		return AuthUserDto.CONVERTER_DTO.apply(authUserService.update(AuthUserDto.CONVERTER_ENTITY.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:user')")
	public void delete(@PathVariable String id) {
		authUserService.delete(id);
	}

}
