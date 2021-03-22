/**
 * 
 */
package com.co.app.auth.services;

/**
 * @author alobaton
 *
 */
public interface CodeGenerator {
	/**
	 * Generate code.
	 * 
	 * @param type   The code type.
	 * @param length The length.
	 * @return
	 */
	String generateCode(CodeType type, int length);
}
