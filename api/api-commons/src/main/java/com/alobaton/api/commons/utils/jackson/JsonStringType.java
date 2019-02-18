/**
 * 
 */
package com.alobaton.api.commons.utils.jackson;

import java.util.Properties;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

/**
 * @author alobaton
 *
 */
public class JsonStringType extends AbstractSingleColumnStandardBasicType<Object> implements DynamicParameterizedType {
	private static final long serialVersionUID = 965453813114523627L;

	public JsonStringType() {
		super(JsonStringSqlTypeDescriptor.INSTANCE, new JsonTypeDescriptor());
	}

	public String getName() {
		return "json";
	}

	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}

	@Override
	public void setParameterValues(Properties parameters) {
		((JsonTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
	}
}