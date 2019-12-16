/**
 * 
 */
package com.co.app.settings.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.co.app.commons.CommonsConstants;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.settings.dao.SettingsRepository;
import com.co.app.settings.domain.Settings;
import com.co.app.settings.services.SettingsService;
import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 *
 */
@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsRepository repository;

	@Override
	public Settings create(Settings domain) {
		return repository.save(domain);
	}

	@Override
	public Settings get(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(Settings.class, CommonsConstants.ID, id));
	}

	@Override
	public Settings delete(String id) {
		Settings domain = get(id);

		repository.deleteById(id);

		return domain;
	}

	@Override
	public boolean exists(String id) {
		return repository.existsById(id);
	}

	@Override
	public List<Settings> getAll(Predicate predicate) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(repository.findAll(predicate).iterator(), Spliterator.ORDERED),
				false).collect(Collectors.<Settings>toList());
	}

	@Override
	public Settings update(Settings domain) {
		return repository.save(domain);
	}

	@Override
	public Settings customUpdate(Map<String, Object> model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Page<Settings> getAll(Predicate predicate, Pageable pageable) {
		return repository.findAll(predicate, pageable);
	}

}
