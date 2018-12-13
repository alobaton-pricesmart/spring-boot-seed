/**
 * 
 */
package com.innova4j.api.commons.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author innova4j-team
 * 
 * @param <T>
 */
public interface BasePagedService<T> extends BaseService<T> {

	Page<T> getAll(T dto, Pageable pageable);
}
