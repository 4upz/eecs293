/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.Objects;

/**
 * A class to represent variables in a mathematical expression
 * @author Kyle Pham
 * @author Arik Stewart
 */
public final class Variable extends AbstractToken {

	/**
	 * The string representation of the variable
	 */
	private final String representation;
	
	/**
	 * A cache used to avoid creating duplicate variables with the same representation
	 */
	private static Cache<String, Variable> cache = new Cache<String, Variable>();
	
	/**
	 * Constructs a Variable with a given string representation
	 * @param representation	the given string representation of the variable to be constructed
	 */
	private Variable(String representation) {
		this.representation = representation;
	}
	
	/**
	 * Gets the string representation of the variable
	 * @return	the string representation of the variable
	 */
	public final String getRepresentation() {
		return this.representation;
	}
	
	@Override
	public TerminalSymbol getType() {
		return TerminalSymbol.VARIABLE;
	}
	
	/**
	 * Builds a variable with a given string representation, avoiding duplicates
	 * @param representation	the given string representation of the variable to be built or returned
	 * @return	a variable with the given string representation
	 */
	public static final Variable build(String representation) {
		Objects.requireNonNull(representation, "The string representation should not be null!");
		
		return cache.get(representation, Variable::new);
	}
	
	@Override
	public String toString(){
		return this.getRepresentation();
	}
	
}
