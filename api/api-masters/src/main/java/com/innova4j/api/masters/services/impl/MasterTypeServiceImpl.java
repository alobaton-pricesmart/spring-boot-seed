/**
 * 
 */
package com.innova4j.api.masters.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.innova4j.api.commons.exception.RegisterNotFoundException;
import com.innova4j.api.masters.dao.MasterTypeRepository;
import com.innova4j.api.masters.domain.MasterType;
import com.innova4j.api.masters.dto.MasterTypeDto;
import com.innova4j.api.masters.services.MasterTypeService;

/**
 * @author alobaton
 *
 */
@Service
public class MasterTypeServiceImpl implements MasterTypeService {

	@Autowired
	private MasterTypeRepository repository;

	@Override
	public Page<MasterTypeDto> getAll(MasterTypeDto dto, Pageable pageable) {
		Example<MasterType> example = Example.of(MasterType.CONVERTER.apply(dto));

		Page<MasterType> result = repository.findAll(example, pageable);

		return new PageImpl<MasterTypeDto>(
				result.getContent().stream().map(MasterTypeDto.CONVERTER).collect(Collectors.<MasterTypeDto>toList()),
				pageable, result.getTotalElements());
	}

	@Override
	public MasterTypeDto create(MasterTypeDto dto) {
		MasterType domain = repository.save(MasterType.CONVERTER.apply(dto));

		return MasterTypeDto.CONVERTER.apply(domain);
	}

	@Override
	public MasterTypeDto get(String id) {
		MasterType domain = repository.getOne(id);

		return MasterTypeDto.CONVERTER.apply(domain);
	}

	@Override
	public MasterTypeDto customGet(@NotNull MasterTypeDto dto) {
		Example<MasterType> example = Example.of(MasterType.CONVERTER.apply(dto));

		MasterType domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(MasterType.class, Strings.EMPTY, dto.toString()));

		return MasterTypeDto.CONVERTER.apply(domain);
	}

	@Override
	public List<MasterTypeDto> getAll() {
		return repository.findAll().stream().map(MasterTypeDto.CONVERTER).collect(Collectors.<MasterTypeDto>toList());
	}

	@Override
	public List<MasterTypeDto> getAll(MasterTypeDto dto) {
		Example<MasterType> example = Example.of(MasterType.CONVERTER.apply(dto));

		List<MasterType> result = repository.findAll(example);

		return result.stream().map(MasterTypeDto.CONVERTER).collect(Collectors.<MasterTypeDto>toList());
	}

	@Override
	public MasterTypeDto update(MasterTypeDto dto) {
		MasterType domain = repository.save(MasterType.CONVERTER.apply(dto));

		return MasterTypeDto.CONVERTER.apply(domain);
	}

	@Override
	public MasterTypeDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MasterTypeDto delete(String id) {
		MasterTypeDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
