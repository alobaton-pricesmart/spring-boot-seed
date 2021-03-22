/**
 * 
 */
package com.co.app.commons.controllers;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
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
public interface BasePagedController<T, K> extends BaseController<T, K> {

	/**
	 * Get <T>.
	 * 
	 * @param parameters
	 */
	@GetMapping("search/paged")
	public @ResponseBody Page<T> getAll(@QuerydslPredicate Predicate predicate,
			@RequestParam Map<String, String> requestParams, Pageable pageable);

}