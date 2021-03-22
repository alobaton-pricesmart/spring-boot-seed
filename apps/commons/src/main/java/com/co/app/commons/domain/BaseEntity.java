/**
 * 
 */
package com.co.app.commons.domain;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.co.app.commons.utils.jackson.JsonStringType;

/**
 * @author alobaton
 *
 */
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class) })
@MappedSuperclass
public class BaseEntity {

}
