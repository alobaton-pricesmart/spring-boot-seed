/**
 * 
 */
package com.co.app.commons.repository;

import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * @author alobaton
 *
 */
public interface BaseCustomRepository<T> {

	T customSave(@NotNull Map<String, Object> domain);

	void customDelete(@NotNull Map<String, Object> domain);
}