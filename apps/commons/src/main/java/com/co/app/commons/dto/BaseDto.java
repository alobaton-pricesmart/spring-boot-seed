/**
 * 
 */
package com.co.app.commons.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class BaseDto {
	@DateTimeFormat(iso = ISO.DATE_TIME)
	protected LocalDateTime created;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	protected LocalDateTime lastModified;

}