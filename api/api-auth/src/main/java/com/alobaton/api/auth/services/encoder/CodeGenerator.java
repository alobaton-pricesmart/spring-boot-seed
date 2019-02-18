/**
 * 
 */
package com.alobaton.api.auth.services.encoder;

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
