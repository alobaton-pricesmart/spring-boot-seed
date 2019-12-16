/**
 * 
 */
package com.co.app.commons.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

/**
 * 
 * @author alobaton
 * 
 * @param <T>
 * @param <D>
 */
@RestController
public interface BasePagedController<T> extends BaseController<T> {

	/**
	 * Get <T>.
	 * 
	 * @param parameters
	 */
	@PostMapping("/paged")
	public @ResponseBody Page<T> getAll(@QuerydslPredicate Predicate predicate, Pageable pageable,
			@RequestParam(required = false) boolean isPaged);

}