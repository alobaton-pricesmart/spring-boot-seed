/**
 * 
 */
package com.innova4j.api.masters.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.innova4j.api.commons.controllers.BasePagedController;
import com.innova4j.api.masters.dto.MasterDto;

/**
 * @author innova4j-team
 *
 */
public class MasterController implements BasePagedController<MasterDto> {

	@Override
	public MasterDto create(@Valid MasterDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MasterDto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MasterDto> getAll(@Valid MasterDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MasterDto update(String id, @Valid MasterDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<MasterDto> getAll(@Valid MasterDto dto, @NotNull Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
