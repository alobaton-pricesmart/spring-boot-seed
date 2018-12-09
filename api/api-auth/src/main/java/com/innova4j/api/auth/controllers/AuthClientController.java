/**
 * 
 */
package com.innova4j.api.auth.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.auth.dto.AuthClientDetailsDto;
import com.innova4j.api.auth.services.client.impl.CustomClientDetailsService;
import com.innova4j.api.commons.controllers.BaseController;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/clients")
public class AuthClientController implements BaseController<AuthClientDetailsDto> {

	@Autowired
	private CustomClientDetailsService service;

	@Override
	public AuthClientDetailsDto create(@Valid AuthClientDetailsDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthClientDetailsDto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthClientDetailsDto> getAll(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthClientDetailsDto update(String id, @Valid AuthClientDetailsDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
