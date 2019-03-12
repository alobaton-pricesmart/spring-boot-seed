package com.co.app.cache.services.impl;

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

import com.co.app.cache.dao.CacheRepository;
import com.co.app.cache.domain.Cache;
import com.co.app.cache.dto.CacheDto;
import com.co.app.cache.services.CacheService;
import com.co.app.commons.exception.RegisterNotFoundException;


/**
 * @author luis.colmenarez
 *
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private CacheRepository repository;
	
	@Override
	public Page<CacheDto> getAll(CacheDto dto, Pageable pageable) {
		Example<Cache> example = Example.of(Cache.CONVERTER.apply(dto));

		Page<Cache> result = repository.findAll(example, pageable);

		return new PageImpl<CacheDto>(
				result.getContent().stream().map(CacheDto.CONVERTER).collect(Collectors.<CacheDto>toList()), pageable,
				result.getTotalElements());
	}

	@Override
	public CacheDto create(CacheDto dto) {
		Cache domain = repository.save(Cache.CONVERTER.apply(dto));

		return CacheDto.CONVERTER.apply(domain);
	}

	@Override
	public CacheDto get(String id) {
		Cache domain = repository.getOne(id);

		return CacheDto.CONVERTER.apply(domain);
	}

	@Override
	public CacheDto customGet(@NotNull CacheDto dto) {
		Example<Cache> example = Example.of(Cache.CONVERTER.apply(dto));

		Cache domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(Cache.class, Strings.EMPTY, dto.toString()));

		return CacheDto.CONVERTER.apply(domain);
	}

	@Override
	public List<CacheDto> getAll() {
		return repository.findAll().stream().map(CacheDto.CONVERTER).collect(Collectors.<CacheDto>toList());

	}

	@Override
	public List<CacheDto> getAll(CacheDto dto) {
		Example<Cache> example = Example.of(Cache.CONVERTER.apply(dto));

		List<Cache> result = repository.findAll(example);

		return result.stream().map(CacheDto.CONVERTER).collect(Collectors.<CacheDto>toList());
	}

	@Override
	public CacheDto update(CacheDto dto) {
		Cache CacheType = repository.save(Cache.CONVERTER.apply(dto));

		return CacheDto.CONVERTER.apply(CacheType);
	}

	@Override
	public CacheDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CacheDto delete(String id) {
		CacheDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
