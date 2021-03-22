/**
 * 
 */
package com.co.app.commons.service;

import java.util.List;
import java.util.Map;

import com.querydsl.core.types.Predicate;

/**
 * 
 * @author alobaton
 *
 * @param <T>
 * @param<K>
 */
public interface BaseService<T, K> {

	/**
	 * Creates a <T>.
	 * 
	 * @param domain The <T>.
	 * @return The created <T>.
	 */
	T create(T domain);

	/**
	 * Get a <T>
	 * 
	 * @param id The<K>.
	 * @return The <T>.
	 */
	T get(K id);

	/**
	 * Get all <T>
	 * 
	 * @return
	 */
	List<T> getAll(Predicate predicate);

	/**
	 * Update a <T>
	 * 
	 * @param domain The <T>
	 * @return The updated <T>
	 */
	T update(T domain);

	/**
	 * 
	 * @param domain
	 * @return The updated <T>
	 */
	T customUpdate(Map<String, Object> domain);

	/**
	 * Delte a <T>
	 * 
	 * @param id The<K>
	 */
	T delete(K id);

	/**
	 * True if exists by<K>.
	 * 
	 * @param id The<K>.
	 * @return True if exists, false otherwise.
	 */
	boolean exists(K id);

}
