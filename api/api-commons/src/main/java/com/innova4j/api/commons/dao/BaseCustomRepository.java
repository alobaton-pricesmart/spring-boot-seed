/**
 * 
 */
package com.innova4j.api.commons.dao;

import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * @author innova4j-team
 *
 */
public interface BaseCustomRepository<T> {

	T customSave(@NotNull T domain);

	T customGet(@NotNull Map<String, Object> pk);

	void customDelete(@NotNull Map<String, Object> pk);

	boolean customExists(@NotNull Map<String, Object> pk);
}