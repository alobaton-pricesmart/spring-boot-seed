package com.co.app.commons.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class ExceptionMessageDto extends MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String error;
	private Set<?> errors;
	private String traza;
}
