/**
 * 
 */
package com.innova4j.api.commons.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author innova4j-team
 * 
 * @param <T>
 */
public interface BasePagedService<T> extends BaseService<T> {
	/**
	 * Get all paged.
	 * 
	 * @param pageable Pageable request
	 * @return The paged result
	 */
	Page<T> getAll(Map<String, Object> parameters, Pageable pageable);
}
