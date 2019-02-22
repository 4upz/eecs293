/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * An enum that holds the value types used for symbols within the numerical expression
 */
public enum TerminalSymbol implements Symbol {
	
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
	String getRepresentation() {
		return this.representation;
	}

	/**
	 * Parses the token list, checking whether the first token matches the terminal type, uses it as the node (leaf)
	 * and makes the rest of the list the remainder if matched, or return a failure otherwise.
	 * @param input		the input token list to be parsed
	 * @return			a ParseState with leaf node built from the first token and the remainder being the rest of the list
	 * 					if the first token matches the terminal type, or a failure ParseState otherwise
	 */
	@Override
	public ParseState parse(List<Token> input) {
		Objects.requireNonNull(input, "The input token list must not be null!");
		
		if (!input.isEmpty() && input.get(0).matches(this)) {
			// Return a new ParseState with leaf node built from the first token and the remainder being the rest of the list
			List<Token> copyList = new LinkedList<>(input);
			copyList.remove(0);
			return ParseState.build(LeafNode.build(input.get(0)), copyList);
		}
		else {
			return ParseState.FAILURE;
		}
	}

	/**
	 * Finds a TerminalSymbol that can match the given string representation
	 * @param representation	the given string representation of a TerminalSymbol
	 * @return					a TerminalSymbol that can have the given string representation
	 */
	static TerminalSymbol symbolFromParsing(String representation) {
		Objects.requireNonNull(representation, "The input representation must not be null!");
		for (TerminalSymbol symbol : TerminalSymbol.values()) {
			if (representation.equals(symbol.getRepresentation()))
				return symbol;
		}
		return TerminalSymbol.VARIABLE;
	}
}
