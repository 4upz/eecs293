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
	 * The list of valid types for a connector, which are CLOSE, DIVIDE, MINUS, OPEN, PLUS, TIMES.
	 */
	static final List<TerminalSymbol> connectorTypes = Arrays.asList(
		TerminalSymbol.CLOSE,
		TerminalSymbol.DIVIDE,
		TerminalSymbol.MINUS,
		TerminalSymbol.OPEN,
		TerminalSymbol.PLUS,
		TerminalSymbol.TIMES
	);
	
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
		return connectorTypes.contains(type);
	}
	
	/**
	 * Returns the string representation of the connector type
	 * @return	the string representation of the connector type
	 */
	@Override
	public String toString() {
		return this.getType().getRepresentation();
	}

}
