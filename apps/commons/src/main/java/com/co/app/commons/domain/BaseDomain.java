/**
 * 
 */
package com.co.app.commons.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alobaton
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public abstract class BaseDomain extends BaseEntity {

	@CreatedDate
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "created")
	protected LocalDateTime created;

	@LastModifiedDate
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "last_modified")
	protected LocalDateTime lastModified;

}
