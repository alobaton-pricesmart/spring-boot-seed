/**
 * 
 */
package com.co.app.masters.controllers;

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
import com.co.app.masters.dto.MasterTypeDto;
import com.co.app.masters.services.MasterTypeService;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/master-types")
public class MasterTypeController implements BasePagedController<MasterTypeDto> {

	@Autowired
	private MasterTypeService service;

	@Override
	public MasterTypeDto create(@Valid @RequestBody MasterTypeDto dto) {
		return service.create(dto);
	}

	@Override
	public MasterTypeDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	public List<MasterTypeDto> getAll(@Valid @RequestParam Optional<MasterTypeDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();
	}

	@Override
	public MasterTypeDto update(@PathVariable String id, @Valid @RequestBody MasterTypeDto dto) {
		dto.setId(id);

		return service.update(dto);
	}

	@Override
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	public Page<MasterTypeDto> getAll(@Valid @RequestParam Optional<MasterTypeDto> dto,
			@NotNull @RequestParam Pageable pageable) {
		if (dto.isPresent()) {
			return service.getAll(dto.get(), pageable);
		}

		return service.getAll(pageable);
	}

}
