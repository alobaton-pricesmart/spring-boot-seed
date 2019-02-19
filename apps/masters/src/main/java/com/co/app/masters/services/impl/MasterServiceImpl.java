/**
 * 
 */
package com.co.app.masters.services.impl;

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

import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.masters.dao.MasterRepository;
import com.co.app.masters.domain.Master;
import com.co.app.masters.dto.MasterDto;
import com.co.app.masters.services.MasterService;

/**
 * @author alobaton
 *
 */
@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterRepository repository;

	@Override
	public Page<MasterDto> getAll(MasterDto dto, Pageable pageable) {
		Example<Master> example = Example.of(Master.CONVERTER.apply(dto));

		Page<Master> result = repository.findAll(example, pageable);

		return new PageImpl<MasterDto>(
				result.getContent().stream().map(MasterDto.CONVERTER).collect(Collectors.<MasterDto>toList()), pageable,
				result.getTotalElements());
	}

	@Override
	public MasterDto create(MasterDto dto) {
		Master domain = repository.save(Master.CONVERTER.apply(dto));

		return MasterDto.CONVERTER.apply(domain);
	}

	@Override
	public MasterDto get(String id) {
		Master domain = repository.getOne(id);

		return MasterDto.CONVERTER.apply(domain);
	}

	@Override
	public MasterDto customGet(@NotNull MasterDto dto) {
		Example<Master> example = Example.of(Master.CONVERTER.apply(dto));

		Master domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(Master.class, Strings.EMPTY, dto.toString()));

		return MasterDto.CONVERTER.apply(domain);
	}

	@Override
	public List<MasterDto> getAll() {
		return repository.findAll().stream().map(MasterDto.CONVERTER).collect(Collectors.<MasterDto>toList());
	}

	@Override
	public List<MasterDto> getAll(MasterDto dto) {
		Example<Master> example = Example.of(Master.CONVERTER.apply(dto));

		List<Master> result = repository.findAll(example);

		return result.stream().map(MasterDto.CONVERTER).collect(Collectors.<MasterDto>toList());
	}

	@Override
	public MasterDto update(MasterDto dto) {
		Master masterType = repository.save(Master.CONVERTER.apply(dto));

		return MasterDto.CONVERTER.apply(masterType);
	}

	@Override
	public MasterDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MasterDto delete(String id) {
		MasterDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
