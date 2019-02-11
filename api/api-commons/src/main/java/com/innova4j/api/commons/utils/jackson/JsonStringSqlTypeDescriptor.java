/**
 * 
 */
package com.innova4j.api.commons.utils.jackson;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicBinder;

/**
 * @author alobaton
 *
 */
public class JsonStringSqlTypeDescriptor extends AbstractJsonSqlTypeDescriptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1788769202339524489L;
	public static final JsonStringSqlTypeDescriptor INSTANCE = new JsonStringSqlTypeDescriptor();

	@Override
	public <X> ValueBinder<X> getBinder(JavaTypeDescriptor<X> javaTypeDescriptor) {
		return new BasicBinder<X>(javaTypeDescriptor, this) {
			@Override
			protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options)
					throws SQLException {
				st.setString(index, javaTypeDescriptor.unwrap(value, String.class, options));
			}

			@Override
			protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
					throws SQLException {
				st.setString(name, javaTypeDescriptor.unwrap(value, String.class, options));
			}
		};
	}

}