/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

/**
 * An abstract class used to represent tokens of different types in a mathematical expression
 * @author Kyle Pham
 * @author Arik Stewart
 */
public abstract class AbstractToken implements Token {

	/**
	 * Inherited method that retrieves the current TerminalSymbol type of the token instance
	 * @return enum value stored in Token type
	 */
	@Override
	public abstract TerminalSymbol getType(); 
	
	/**
	 * Inherited method that determines if the current Token type matches a given type
	 * @return a boolean value based on match result
	 */
	@Override
	public boolean matches(TerminalSymbol type) {
		return this.getType() == type;
	}

	@Override
	public boolean equals(Object object) {
		return object != null && object instanceof AbstractToken && ((AbstractToken) object).matches(this.getType());
	}
}
