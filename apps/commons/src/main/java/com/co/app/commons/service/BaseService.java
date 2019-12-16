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
 * @param <I>
 */
public interface BaseService<T, I> {

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
	 * @param id The <I>.
	 * @return The <T>.
	 */
	T get(I id);

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
