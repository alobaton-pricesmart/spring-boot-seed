/**
 * 
 */
package com.co.app.commons.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

import com.co.app.commons.domain.BaseDomain;
import com.co.app.commons.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtil.FECHA_PATTERN_DD_MM_YYYY_HH_MM_SS, timezone = DateUtil.ZONE_ID)
	@JsonSerialize(as = LocalDateTime.class)
	protected LocalDateTime created;

	private String createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtil.FECHA_PATTERN_DD_MM_YYYY_HH_MM_SS, timezone = DateUtil.ZONE_ID)
	@JsonSerialize(as = LocalDateTime.class)
	protected LocalDateTime lastModified;

	private String modifiedBy;

	public static final BiFunction<BaseDomain, BaseDto, BaseDto> CONVERTER_DTO = (BaseDomain t, BaseDto dto) -> {
		dto.setCreated(t.getCreated());
		dto.setCreatedBy(t.getCreatedBy());
		dto.setLastModified(t.getLastModified());
		dto.setModifiedBy(t.getModifiedBy());

		return dto;
	};

}