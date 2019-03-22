/**
 * 
 */
package com.co.app.commons.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.app.commons.dto.PageableQueryDto;

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
	public @ResponseBody Page<T> getAll(@RequestBody PageableQueryDto<T> request);

}