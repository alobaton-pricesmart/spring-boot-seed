/**
 * 
 */
package com.co.app.commons.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 * 
 * @param <T>
 * @param <I>
 */
public interface BasePagedService<T, I> extends BaseService<T, I> {
	/**
	 * Get all <T>
	 * 
	 * @param predicate The querydsl predicate
	 * @param pageable  The paged request
	 * @return
	 */
	Page<T> getAll(Predicate predicate, Pageable pageable);
}
