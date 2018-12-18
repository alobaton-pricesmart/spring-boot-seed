/**
 * 
 */
package com.innova4j.api.masters.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.commons.controllers.BasePagedController;
import com.innova4j.api.masters.dto.MasterDto;
import com.innova4j.api.masters.services.MasterService;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/masters")
public class MasterController implements BasePagedController<MasterDto> {
	@Autowired
	private MasterService service;

	@Override
	public MasterDto create(@Valid @RequestBody MasterDto dto) {
		return service.create(dto);
	}

	@Override
	public MasterDto get(@PathVariable String id) {
		return service.get(id);
	}

	@Override
	public List<MasterDto> getAll(@Valid @RequestBody MasterDto dto) {
		return service.getAll(dto);
	}

	@Override
	public MasterDto update(@PathVariable String id, @Valid @RequestBody MasterDto dto) {
		dto.setId(id);

		return service.update(dto);
	}

	@Override
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@Override
	public Page<MasterDto> getAll(@Valid @RequestBody MasterDto dto, @NotNull Pageable pageable) {
		return service.getAll(dto, pageable);
	}

}
