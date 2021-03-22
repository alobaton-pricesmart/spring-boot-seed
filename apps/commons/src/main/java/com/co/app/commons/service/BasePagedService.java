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
 * @param <K>
 */
public interface BasePagedService<T, K> extends BaseService<T, K> {
	/**
	 * Get all <T>
	 * 
	 * @param predicate The querydsl predicate
	 * @param pageable  The paged request
	 * @return
	 */
	Page<T> getAll(Predicate predicate, Pageable pageable);
}
