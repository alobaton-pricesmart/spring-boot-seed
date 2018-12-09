/**
 * 
 */
package com.innova4j.api.auth.services.encoder;

/**
 * @author innova4j-team
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
