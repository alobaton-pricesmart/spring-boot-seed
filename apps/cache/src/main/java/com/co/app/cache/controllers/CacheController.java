package com.co.app.cache.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.cache.dto.CacheDto;
import com.co.app.cache.services.CacheService;
import com.co.app.commons.controllers.BasePagedController;

/**
 * @author luis.colmenarez
 *
 */
@RestController
@RequestMapping("/cache")
public class CacheController implements BasePagedController<CacheDto> {
	
	@Autowired
	private CacheService service;
	
	@Override
	public CacheDto create(@Valid @RequestBody CacheDto dto) {
		return service.create(dto);
	}

	@Override
	public CacheDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	public List<CacheDto> getAll(@Valid @RequestParam CacheDto dto) {
		return service.getAll(dto);
	}

	@Override
	public CacheDto update(@PathVariable String key, @Valid @RequestBody CacheDto dto) {
		dto.setKey(key);

		return service.update(dto);
	}

	@Override
	public void delete(@PathVariable String key) {
		service.delete(key);
	}

	@Override
	public Page<CacheDto> getAll(@Valid @RequestParam CacheDto dto, @NotNull @RequestParam Pageable pageable) {
		return service.getAll(dto, pageable);
	}
}
