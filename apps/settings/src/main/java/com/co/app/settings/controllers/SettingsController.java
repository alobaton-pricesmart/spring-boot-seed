/**
 * 
 */
package com.co.app.settings.controllers;

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

import com.co.app.commons.controllers.BasePagedController;
import com.co.app.settings.domain.Settings;
import com.co.app.settings.dto.SettingsDto;
import com.co.app.settings.services.SettingsService;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/settings")
public class SettingsController implements BasePagedController<SettingsDto> {

	@Autowired
	private SettingsService service;

	@Override
	@PreAuthorize("customHasPermission('create:setting')")
	public SettingsDto create(@Valid @RequestBody SettingsDto dto) {
		return SettingsDto.CONVERTER.apply(service.create(Settings.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('read:setting')")
	public SettingsDto get(@PathVariable String id) {
		return SettingsDto.CONVERTER.apply(service.get(id));
	}

	@Override
	@PreAuthorize("customHasPermission('update:setting')")
	public SettingsDto update(@PathVariable String id, @Valid @RequestBody SettingsDto dto) {
		dto.setId(id);

		return SettingsDto.CONVERTER.apply(service.create(Settings.CONVERTER.apply(dto)));
	}

	@Override
	@PreAuthorize("customHasPermission('delete:setting')")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	public List<SettingsDto> getAll(Predicate predicate) {
		return service.getAll(predicate).stream().map(SettingsDto.CONVERTER).collect(Collectors.<SettingsDto>toList());
	}

	@Override
	public Page<SettingsDto> getAll(Predicate predicate, Pageable pageable, boolean isPaged) {
		Page<Settings> page = service.getAll(predicate, isPaged ? pageable : Pageable.unpaged());

		return new PageImpl<>(
				page.getContent().stream().map(SettingsDto.CONVERTER).collect(Collectors.<SettingsDto>toList()),
				pageable, page.getTotalElements());
	}

}
