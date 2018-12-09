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
	 * @param domain The <T>.
	 * @return The created <T>.
	 */
	public T create(T domain);

	/**
	 * Get a <T>
	 * 
	 * @param id The id.
	 * @return The <T>.
	 */
	public T get(String id);

	/**
	 * 
	 * @param pk
	 * @return
	 */
	public T customGet(@NotNull Map<String, Object> pk);

	/**
	 * Get all <T>.
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * Get all <T>.
	 * 
	 * @return
	 */
	public List<T> getAll(Map<String, Object> parameters);

	/**
	 * Update a <T>
	 * 
	 * @param domain The <T>
	 * @return The updated <T>
	 */
	public T update(T domain);

	/**
	 * 
	 * @param domain
	 * @return The updated <T>
	 */
	public T customUpdate(Map<String, Object> domain);

	/**
	 * Delte a <T>
	 * 
	 * @param id The id
	 */
	public T delete(String id);

	/**
	 * True if exists by id.
	 * 
	 * @param id The id.
	 * @return True if exists, false otherwise.
	 */
	public boolean exists(String id);

}
