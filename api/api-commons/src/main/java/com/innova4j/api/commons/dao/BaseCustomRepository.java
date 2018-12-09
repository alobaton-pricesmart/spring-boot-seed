/**
 * 
 */
package com.innova4j.api.commons.dao;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author innova4j-team
 *
 */
public interface BaseCustomRepository<T> {

	T customSave(@NotNull T domain);

	T customGet(@NotNull Map<String, Object> pk);

	List<T> customGetAll(@NotNull Map<String, Object> parameters);

	Page<T> customGetAll(@NotNull Map<String, Object> parameters, Pageable pageable);

	void customDelete(@NotNull Map<String, Object> pk);

	boolean customExists(@NotNull Map<String, Object> pk);
}