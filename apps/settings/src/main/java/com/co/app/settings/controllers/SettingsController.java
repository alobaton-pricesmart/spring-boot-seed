/**
 * 
 */
package com.co.app.settings.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.commons.controllers.BaseController;
import com.co.app.settings.dto.SettingsDto;
import com.co.app.settings.services.SettingsService;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/settings")
public class SettingsController implements BaseController<SettingsDto> {

	@Autowired
	private SettingsService service;

	@Override
	@PreAuthorize("customHasPermission('create:setting')")
	public SettingsDto create(@Valid @RequestBody SettingsDto dto) {
		return service.create(dto);
	}

	@Override
	@PreAuthorize("customHasPermission('read:setting')")
	public SettingsDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	@PreAuthorize("customHasPermission('read:settings')")
	public List<SettingsDto> getAll(@Valid @RequestParam Optional<SettingsDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();
	}

	@Override
	@PreAuthorize("customHasPermission('update:setting')")
	public SettingsDto update(@PathVariable String id, @Valid @RequestBody SettingsDto dto) {
		dto.setId(id);

		return service.update(dto);
	}

	@Override
	@PreAuthorize("customHasPermission('delete:setting')")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

}
