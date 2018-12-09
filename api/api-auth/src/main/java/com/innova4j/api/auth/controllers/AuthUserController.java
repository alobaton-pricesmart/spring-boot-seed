/**
 * 
 */
package com.innova4j.api.auth.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.auth.domain.builder.AuthUserBuilder;
import com.innova4j.api.auth.dto.AuthUserDto;
import com.innova4j.api.auth.services.user.AuthUserService;
import com.innova4j.api.commons.controllers.BasePagedController;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/users")
public class AuthUserController implements BasePagedController<AuthUserDto> {

	@Autowired
	private AuthUserService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public AuthUserDto create(@Valid AuthUserDto dto) {
		AuthUserBuilder builder = new AuthUserBuilder().nickname(dto.getNickname()).name(dto.getName())
				.lastName(dto.getLastName()).email(dto.getEmail()).roles(dto.getRoles())
				.password(encoder.encode(dto.getPassword())).locked(Boolean.FALSE).enabled(Boolean.TRUE);

		service.create(builder.build());

		return dto;
	}

	@Override
	public AuthUserDto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthUserDto> getAll(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthUserDto update(String id, @Valid AuthUserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<AuthUserDto> getAll(Map<String, Object> parameters, @NotNull Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
