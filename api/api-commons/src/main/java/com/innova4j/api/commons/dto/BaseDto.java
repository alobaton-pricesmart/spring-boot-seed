/**
 * 
 */
package com.innova4j.api.commons.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author innova4j-teamF
 *
 */
public abstract class BaseDto {
	@DateTimeFormat(iso = ISO.DATE_TIME)
	protected LocalDateTime created;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	protected LocalDateTime lastModified;

	/**
	 * @return the created
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * @return the lastModified
	 */
	public LocalDateTime getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

}