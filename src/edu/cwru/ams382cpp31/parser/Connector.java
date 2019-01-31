package edu.cwru.ams382cpp31.parser;

import java.util.Objects;

public final class Connector extends AbstractToken {

	/**
	 * 
	 */
	private TerminalSymbol type;
	
	/**
	 * 
	 * @param type
	 */
	private Connector(TerminalSymbol type) {
		this.type = type;
	}
	
	@Override
	public TerminalSymbol getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static final Connector build(TerminalSymbol type) {
		Objects.requireNonNull(type, "The terminal symbol should not be null!");
		
		if (isValidType(type)) {
			return new Connector(type);
		}
		else {
			throw new IllegalArgumentException("The input terminal symbol type is invalid!");
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private static boolean isValidType(TerminalSymbol type) {
		switch ( type ) {
		case CLOSE:
		case DIVIDE:
		case MINUS:
		case OPEN:
		case PLUS:
		case TIMES:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * 
	 */
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
