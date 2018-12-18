/**
 * 
 */
package com.innova4j.api.masters.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova4j.api.commons.controllers.BasePagedController;
import com.innova4j.api.masters.dto.MasterTypeDto;

/**
 * @author innova4j-team
 *
 */
@RestController
@RequestMapping("/master-types")
public class MasterTypeController implements BasePagedController<MasterTypeDto> {

	@Override
	public MasterTypeDto create(@Valid MasterTypeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MasterTypeDto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MasterTypeDto> getAll(@Valid MasterTypeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MasterTypeDto update(String id, @Valid MasterTypeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<MasterTypeDto> getAll(@Valid MasterTypeDto dto, @NotNull Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
