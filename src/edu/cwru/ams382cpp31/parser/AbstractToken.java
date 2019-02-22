/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.Arrays;
import java.util.List;

/**
 * An abstract class used to represent tokens of different types in a mathematical expression
 * @author Kyle Pham
 * @author Arik Stewart
 */
public abstract class AbstractToken implements Token {

	/**
	 * The list of operator types
	 */
	private static final List<TerminalSymbol> operatorTypes = Arrays.asList(
			TerminalSymbol.DIVIDE,
			TerminalSymbol.MINUS,
			TerminalSymbol.PLUS,
			TerminalSymbol.TIMES
		);
	
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

	/**
	 * Checks if the abstract token is equal to an object of any type
	 * @param object	an object to be compared to the abstract token
	 * @return			true if the object is an abstract token and matches the type of the current abstract token
	 * 					, and false otherwise
	 */
	@Override
	public boolean equals(Object object) {
		return object instanceof AbstractToken && ((AbstractToken) object).matches(this.getType());
	}
	
	/**
	 * Determines whether a token contains an operator as its type
	 * @return true if it the token does correspond to an operator and false otherwise
	 */
	public boolean isOperator() {
		return operatorTypes.contains(this.getType());
	}
	
}
