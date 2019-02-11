/**
 * 
 */
package com.innova4j.api.commons.domain;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.innova4j.api.commons.utils.jackson.JsonStringType;

/**
 * @author alobaton
 *
 */
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class) })
@MappedSuperclass
public class BaseEntity {

}
