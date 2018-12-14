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

	T customSave(@NotNull Map<String, Object> domain);

	void customDelete(@NotNull Map<String, Object> domain);
}