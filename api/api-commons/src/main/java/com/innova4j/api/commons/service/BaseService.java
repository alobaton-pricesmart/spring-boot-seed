/**
 * 
 */
package com.innova4j.api.commons.service;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author innova4j-team
 *
 * @param <T>
 */
public interface BaseService<T> {

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
	 * @param id The id.
	 * @return The <T>.
	 */
	T get(String id);

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
	 * @param id The id
	 */
	T delete(String id);

	/**
	 * True if exists by id.
	 * 
	 * @param id The id.
	 * @return True if exists, false otherwise.
	 */
	boolean exists(String id);

}
