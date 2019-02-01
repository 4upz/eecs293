/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A class used to represent connectors in a mathematical expression
 * @author Kyle Pham
 * @author Arik Stewart
 *
 */
public final class Connector extends AbstractToken {

	/**
	 * The type of the connector or terminal symbol
	 */
	private TerminalSymbol type;
	
	/**
	 * A cache used to avoid creating duplicate variables with the same representation
	 */
	private static Cache<TerminalSymbol, Connector> cache = new Cache<>();
	
	/**
	 * The list of valid types for a connector
	 */
	private static final List<TerminalSymbol> validTypes = Arrays.asList(new TerminalSymbol[] {
		TerminalSymbol.CLOSE,
		TerminalSymbol.DIVIDE,
		TerminalSymbol.MINUS,
		TerminalSymbol.OPEN,
		TerminalSymbol.PLUS,
		TerminalSymbol.TIMES
	});
	
	
	
	/**
	 * Constructs a connector, given the type of the terminal symbol
	 * @param type	the type of the terminal symbol to be represented by the connector
	 */
	private Connector(TerminalSymbol type) {
		this.type = type;
	}
	
	/**
	 * Retrieves the current TerminalSymbol type of the connector instance
	 * @return	the type of the connector
	 */
	@Override
	public TerminalSymbol getType() {
		return this.type;
	}
	
	/**
	 * Builds a connector, given the type of the terminal symbol, avoiding duplicates
	 * @param type	the type of the terminal symbol to be represented by the connector
	 * @return	a connector with the given type of terminal symbol
	 */
	public static final Connector build(TerminalSymbol type) {
		Objects.requireNonNull(type, "The terminal symbol should not be null!");
		
		if (isValidType(type)) {
			return cache.get(type, Connector::new);
		}
		else {
			throw new IllegalArgumentException("The input terminal symbol type is invalid!");
		}
	}
	
	/**
	 * Checks whether the given input is a connector terminal symbol 
	 * @return	true if the input is a connector terminal symbol ('+', '-', '*', '/', '(', ')')
	 * 			false otherwise
	 */
	private static boolean isValidType(TerminalSymbol type) {
		return validTypes.contains(type);
	}
	
	/**
	 * Returns the string representation of the connector
	 * @return	the string representation of the connector
	 */
	@Override
	public String toString() {
		switch ( this.getType() ) {
		case CLOSE:
			return ")";
		case DIVIDE:
			return "/";
		case MINUS:
			return "-";
		case OPEN:
			return "(";
		case PLUS:
			return "+";
		case TIMES:
			return "*";
		default:
			throw new IllegalArgumentException("Invalid type of the connector!");
		}
	}

}
