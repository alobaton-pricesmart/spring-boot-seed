/**
 * 
 */
package com.co.app.commons.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alobaton
 * @param <T> DTO
 */
@RestController
public interface BaseController<T> {
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
	public @ResponseBody T get(@PathVariable String id);

	/**
	 * Get <T>.
	 * 
	 * @param parameters
	 */
	@GetMapping
	public @ResponseBody List<T> getAll(@Valid T dto);

	/**
	 * Update <T>.
	 * 
	 * @param id     The <T> id.
	 * @param domain The <T>.
	 * @return The updated <T>.
	 */
	@PatchMapping("/{id}")
	public @ResponseBody T update(@PathVariable String id, @Valid @RequestBody T dto);

	/**
	 * Delete <T>.
	 * 
	 * @param id The <T> id.
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id);
}