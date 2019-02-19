/**
 * 
 */
package com.co.app.commons.service;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author alobaton
 *
 * @param <T>
 * @param <I>
 */
public interface BaseService<T, I> {

	/**
	 * Creates a <T>.
	 * 
	 * @param dto The <T>.
	 * @return The created <T>.
	 */
	T create(T dto);

	/**
	 * Get a <T>
	 * 
	 * @param id The <I>.
	 * @return The <T>.
	 */
	T get(I id);

	/**
	 * 
	 * @param pk
	 * @return
	 */
	T customGet(@NotNull T dto);

	/**
	 * Get all <T>.
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * Get all <T>
	 * 
	 * @param dto
	 * @return
	 */
	List<T> getAll(T dto);

	/**
	 * Update a <T>
	 * 
	 * @param dto The <T>
	 * @return The updated <T>
	 */
	T update(T dto);

	/**
	 * 
	 * @param dto
	 * @return The updated <T>
	 */
	T customUpdate(Map<String, Object> dto);

	/**
	 * Delte a <T>
	 * 
	 * @param id The <I>
	 */
	T delete(I id);

	/**
	 * True if exists by <I>.
	 * 
	 * @param id The <I>.
	 * @return True if exists, false otherwise.
	 */
	boolean exists(I id);

}
