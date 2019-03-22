/**
 * 
 */
package com.co.app.commons.dto;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageImpl;

/**
 * @author alobaton
 *
 */
public class PageableQueryDto<T> {

	@NotNull
	private CustomPageImpl pageable;
	private Optional<T> dto;

	public PageableQueryDto() {
		dto = Optional.empty();
	}

	public CustomPageImpl getPageable() {
		return pageable;
	}

	public void setPageable(CustomPageImpl pageable) {
		this.pageable = pageable;
	}

	public Optional<T> getDto() {
		return dto;
	}

	public void setDto(Optional<T> dto) {
		this.dto = dto;
	}

	@Override
	public String toString() {
		return "PageableQueryDto [pageable=" + pageable + ", dto=" + dto + "]";
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
