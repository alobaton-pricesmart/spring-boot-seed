/**
 * 
 */
package com.innova4j.api.commons.controllers;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author innova4j-team
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
	public @ResponseBody Page<T> getAll(@RequestParam Map<String, Object> parameters, @NotNull Pageable pageable);

}