/**
 * 
 */
package com.innova4j.api.commons.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author innova4j-team
 *
 */
public abstract class BaseDomain {

	@CreatedDate
	@DateTimeFormat(iso = ISO.DATE_TIME)
	protected LocalDateTime created;
	@LastModifiedDate
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseDomain [created=" + created + ", lastModified=" + lastModified + "]";
	}

}
