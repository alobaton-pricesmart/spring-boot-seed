package com.co.app.memory.controllers;

import java.util.List;
import java.util.Optional;

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

import com.co.app.commons.controllers.BasePagedController;
import com.co.app.memory.dto.MemoryDto;
import com.co.app.memory.services.MemoryService;

/**
 * @author luis.colmenarez
 *
 */
@RestController
@RequestMapping("/distributed-memory")
public class MemoryController implements BasePagedController<MemoryDto> {

	@Autowired
	private MemoryService service;

	@Override
	public MemoryDto create(@Valid @RequestBody MemoryDto dto) {
		return service.create(dto);
	}

	@Override
	public MemoryDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	public List<MemoryDto> getAll(@Valid @RequestParam Optional<MemoryDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();
	}

	@Override
	public MemoryDto update(@PathVariable String key, @Valid @RequestBody MemoryDto dto) {
		dto.setKey(key);

		return service.update(dto);
	}

	@Override
	public void delete(@PathVariable String key) {
		service.delete(key);
	}

	@Override
	public Page<MemoryDto> getAll(@Valid @RequestParam Optional<MemoryDto> dto,
			@NotNull @RequestParam Pageable pageable) {
		if (dto.isPresent()) {
			return service.getAll(dto.get(), pageable);
		}

		return service.getAll(pageable);
	}
}
