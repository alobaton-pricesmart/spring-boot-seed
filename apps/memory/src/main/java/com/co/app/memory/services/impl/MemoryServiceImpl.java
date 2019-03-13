package com.co.app.memory.services.impl;

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

import com.co.app.memory.dao.MemoryRepository;
import com.co.app.memory.domain.Memory;
import com.co.app.memory.dto.MemoryDto;
import com.co.app.memory.services.MemoryService;
import com.co.app.commons.exception.RegisterNotFoundException;


/**
 * @author luis.colmenarez
 *
 */
@Service
public class MemoryServiceImpl implements MemoryService {

	@Autowired
	private MemoryRepository repository;
	
	@Override
	public Page<MemoryDto> getAll(MemoryDto dto, Pageable pageable) {
		Example<Memory> example = Example.of(Memory.CONVERTER.apply(dto));

		Page<Memory> result = repository.findAll(example, pageable);

		return new PageImpl<MemoryDto>(
				result.getContent().stream().map(MemoryDto.CONVERTER).collect(Collectors.<MemoryDto>toList()), pageable,
				result.getTotalElements());
	}

	@Override
	public MemoryDto create(MemoryDto dto) {
		Memory domain = repository.save(Memory.CONVERTER.apply(dto));

		return MemoryDto.CONVERTER.apply(domain);
	}

	@Override
	public MemoryDto get(String id) {
		Memory domain = repository.getOne(id);

		return MemoryDto.CONVERTER.apply(domain);
	}

	@Override
	public MemoryDto customGet(@NotNull MemoryDto dto) {
		Example<Memory> example = Example.of(Memory.CONVERTER.apply(dto));

		Memory domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(Memory.class, Strings.EMPTY, dto.toString()));

		return MemoryDto.CONVERTER.apply(domain);
	}

	@Override
	public List<MemoryDto> getAll() {
		return repository.findAll().stream().map(MemoryDto.CONVERTER).collect(Collectors.<MemoryDto>toList());

	}

	@Override
	public List<MemoryDto> getAll(MemoryDto dto) {
		Example<Memory> example = Example.of(Memory.CONVERTER.apply(dto));

		List<Memory> result = repository.findAll(example);

		return result.stream().map(MemoryDto.CONVERTER).collect(Collectors.<MemoryDto>toList());
	}

	@Override
	public MemoryDto update(MemoryDto dto) {
		Memory domain = repository.save(Memory.CONVERTER.apply(dto));

		return MemoryDto.CONVERTER.apply(domain);
	}

	@Override
	public MemoryDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MemoryDto delete(String id) {
		MemoryDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
