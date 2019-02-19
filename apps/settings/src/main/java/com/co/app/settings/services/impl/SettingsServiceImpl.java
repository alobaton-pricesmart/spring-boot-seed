/**
 * 
 */
package com.co.app.settings.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.settings.dao.SettingsRepository;
import com.co.app.settings.domain.Settings;
import com.co.app.settings.dto.SettingsDto;
import com.co.app.settings.services.SettingsService;

/**
 * @author alobaton
 *
 */
@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsRepository repository;

	@Override
	public SettingsDto create(SettingsDto dto) {
		Settings domain = repository.save(Settings.CONVERTER.apply(dto));

		return SettingsDto.CONVERTER.apply(domain);
	}

	@Override
	public SettingsDto get(String id) {
		Settings domain = repository.getOne(id);

		return SettingsDto.CONVERTER.apply(domain);
	}

	@Override
	public SettingsDto customGet(@NotNull SettingsDto dto) {
		Example<Settings> example = Example.of(Settings.CONVERTER.apply(dto));

		Settings domain = repository.findOne(example)
				.orElseThrow(() -> new RegisterNotFoundException(Settings.class, Strings.EMPTY, dto.toString()));

		return SettingsDto.CONVERTER.apply(domain);
	}

	@Override
	public List<SettingsDto> getAll() {
		return repository.findAll().stream().map(SettingsDto.CONVERTER).collect(Collectors.<SettingsDto>toList());
	}

	@Override
	public List<SettingsDto> getAll(SettingsDto dto) {
		Example<Settings> example = Example.of(Settings.CONVERTER.apply(dto));

		List<Settings> result = repository.findAll(example);

		return result.stream().map(SettingsDto.CONVERTER).collect(Collectors.<SettingsDto>toList());
	}

	@Override
	public SettingsDto update(SettingsDto dto) {
		Settings masterType = repository.save(Settings.CONVERTER.apply(dto));

		return SettingsDto.CONVERTER.apply(masterType);
	}

	@Override
	public SettingsDto customUpdate(Map<String, Object> dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SettingsDto delete(String id) {
		SettingsDto dto = get(id);

		repository.deleteById(id);

		return dto;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

}
