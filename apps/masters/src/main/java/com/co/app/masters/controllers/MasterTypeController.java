/**
 * 
 */
package com.co.app.masters.controllers;

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
	public List<MasterTypeDto> getAll(Optional<MasterTypeDto> dto) {
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
	public Page<MasterTypeDto> getAll(@RequestBody PageableQueryDto<MasterTypeDto> request) {
		if (request.getDto().isPresent()) {
			MasterTypeDto dto = request.getDto().get();
			return service.getAll(dto, request.getPageable().getPageable());
		}

		return service.getAll(request.getPageable().getPageable());
	}

}
