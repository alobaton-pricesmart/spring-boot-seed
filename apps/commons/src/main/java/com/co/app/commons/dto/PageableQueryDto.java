/**
 * 
 */
package com.co.app.commons.dto;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@Getter
@Setter
@ToString
public class PageableQueryDto<T> {

	@NotNull
	private CustomPageImpl pageable;
	private Optional<T> dto;

	public PageableQueryDto() {
		dto = Optional.empty();
	}

	public class CustomPageImpl extends PageImpl<T> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CustomPageImpl() {
			super(new ArrayList<>());
		}

	}
}
