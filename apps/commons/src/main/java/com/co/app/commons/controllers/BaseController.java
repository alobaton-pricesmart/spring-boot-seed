/**
 * 
 */
package com.co.app.commons.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

/**
 * @author alobaton
 * @param <T> DTO
 */
@RestController
public interface BaseController<T, K> {
	/**
	 * Creates a <T>.
	 * 
	 * @param domain The <T> to create.
	 * @return The created <T>.
	 */
	@PostMapping
	public @ResponseBody T create(@Valid @RequestBody T dto);

	/**
	 * Get a <T> information.
	 * 
	 * @param id The <T> id.
	 * @return The <T> information.
	 */
	@GetMapping("/{id}")
	public @ResponseBody T get(@PathVariable K id);

	/**
	 * Get <T>.
	 * 
	 * @param parameters
	 */
	@GetMapping("search/list")
	public @ResponseBody List<T> getAll(@QuerydslPredicate Predicate predicate,
			@RequestParam Map<String, String> requestParams);

	/**
	 * Update <T>.
	 * 
	 * @param id     The <T> id.
	 * @param domain The <T>.
	 * @return The updated <T>.
	 */
	@PatchMapping("/{id}")
	public @ResponseBody T update(@PathVariable K id, @Valid @RequestBody T dto);

	/**
	 * Delete <T>.
	 * 
	 * @param id The <T> id.
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable K id);
}