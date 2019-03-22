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
import com.co.app.masters.dto.MasterDto;
import com.co.app.masters.services.MasterService;

/**
 * @author alobaton
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
	public List<MasterDto> getAll(Optional<MasterDto> dto) {
		if (dto.isPresent()) {
			return service.getAll(dto.get());
		}

		return service.getAll();
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
	public Page<MasterDto> getAll(@RequestBody PageableQueryDto<MasterDto> request) {
		if (request.getDto().isPresent()) {
			MasterDto dto = request.getDto().get();
			return service.getAll(dto, request.getPageable().getPageable());
		}

		return service.getAll(request.getPageable().getPageable());
	}

}
