/**
 * 
 */
package com.co.app.commons.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author alobaton
 * 
 * @param <T>
 * @param <I>
 */
public interface BasePagedService<T, I> extends BaseService<T, I> {

	Page<T> getAll(Pageable pageable);

	Page<T> getAll(T dto, Pageable pageable);
}
