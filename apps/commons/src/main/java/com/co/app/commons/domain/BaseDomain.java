/**
 * 
 */
package com.co.app.commons.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.co.app.commons.utils.DateUtil;

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

	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = DateUtil.FECHA_PATTERN_DD_MM_YYYY_HH_MM_SS)
	@CreatedDate
	@Column(name = "created", updatable = false)
	protected LocalDateTime created;

	@Column(name = "created_by", nullable = false, updatable = false)
	@CreatedBy
	protected String createdBy;

	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = DateUtil.FECHA_PATTERN_DD_MM_YYYY_HH_MM_SS)
	@LastModifiedDate
	@Column(name = "last_modified")
	protected LocalDateTime lastModified;

	@Column(name = "modified_by")
	@LastModifiedBy
	protected String modifiedBy;

}
