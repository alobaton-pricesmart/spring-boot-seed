package com.co.app.memory.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.commons.controllers.BasePagedController;
import com.co.app.commons.dto.PageableQueryDto;
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
	public List<MemoryDto> getAll(Optional<MemoryDto> dto) {
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
	public Page<MemoryDto> getAll(@RequestBody PageableQueryDto<MemoryDto> request) {
		if (request.getDto().isPresent()) {
			MemoryDto dto = request.getDto().get();
			return service.getAll(dto, request.getPageable().getPageable());
		}

		return service.getAll(request.getPageable().getPageable());
	}
}
