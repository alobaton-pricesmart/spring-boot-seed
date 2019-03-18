/**
 * 
 */
package com.co.app.commons.controllers;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	public @ResponseBody Page<T> getAll(@Valid Optional<T> dto, @NotNull Pageable pageable);

}