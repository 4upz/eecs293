/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * An enum that holds the value types used for symbols within the numerical expression
 */
public enum TerminalSymbol {
	VARIABLE(null), // Letter variables
	PLUS("+"), 	  // Plus sign for addition
	MINUS("-"), 	  // Minus sign for subtraction
	TIMES("*"),    // Sign for multiplication
	DIVIDE("/"),   // Sign for division
	OPEN("("), 	  // Open parenthesis
	CLOSE(")");	  // Closed parenthesis
	
	/**
	 * The string representation of the Terminal Symbol
	 */
	private String representation;
	
	/**
	 * Constructs a TerminalSymbol with its string representation
	 * @param representation	the given string representation
	 */
	private TerminalSymbol(String representation){
		this.representation = representation;
	}
	
	/**
	 * Gets the TerminalSymbol's string representation
	 * @return	the string representation of the TerminalSymbol
	 */
	public String getRepresentation() {
		return this.representation;
	}
}
