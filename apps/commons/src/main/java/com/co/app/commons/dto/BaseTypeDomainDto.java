package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.validation.constraints.NotNull;

import com.co.app.commons.domain.BaseTypeDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(of = { "code" }, doNotUseGetters = true, callSuper = false)
public class BaseTypeDomainDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "code {error.general.isRequired}")
	private Long code;

	private boolean status;

	@NotNull(message = "description {error.general.isRequired}")
	private Map<String, String> description = new HashMap<>();

	public static final BiFunction<BaseTypeDomain, BaseTypeDomainDto, BaseTypeDomainDto> CONVERTER_DTO = (
			BaseTypeDomain t, BaseTypeDomainDto dto) -> {
		dto.setCode(t.getCode());
		dto.setDescription(t.getDescription());
		dto.setStatus(t.isStatus());

		return (BaseTypeDomainDto) BaseDto.CONVERTER_DTO.apply(t, dto);
	};

}
