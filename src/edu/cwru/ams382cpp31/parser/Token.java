/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * An interface to represent a token within a given expression
 */
public interface Token {

	/**
	 * Retrieves the current TerminalSymbol type of the token instance
	 * @return enum value stored in Token type
	 */
	TerminalSymbol getType();
	
	/**
	 * Determines whether the token's type matches a given TerminalSymbol enum type
	 * @param type a specified value from the TerminalSymbol enum
	 * @return a boolean value of whether the token matches the given type
	 */
	boolean matches(TerminalSymbol type);
	
//	/**
//	 * Abstract class used for checking if tokens are matching
//	 * @author Arik Stewart
//	 * @author Kyle Pham
//	 */
//	public abstract class AbstractToken implements Token {
//		
//		/**
//		 * Inherited method that determines if the current Token type matches a given type
//		 * @return a boolean value based on match result
//		 */
//		public final boolean matches(TerminalSymbol type) {
//			return this.getType() == type;
//		}
//		
//	}
	
}
