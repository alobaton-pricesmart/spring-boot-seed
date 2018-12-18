/**
 * 
 */
package com.innova4j.api.settings.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.commons.controllers.BaseController;
import com.innova4j.api.settings.dto.SettingsDto;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/settings")
public class SettingsController implements BaseController<SettingsDto> {

	@Override
	public SettingsDto create(@Valid SettingsDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettingsDto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SettingsDto> getAll(@Valid SettingsDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SettingsDto update(String id, @Valid SettingsDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
