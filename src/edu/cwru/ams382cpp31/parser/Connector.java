package edu.cwru.ams382cpp31.parser;

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
		if (type != null && isValidType(type)) {
			return new Connector(type);
		}
		else if (!isValidType(type)){
			throw new IllegalArgumentException();
		}
		else {
			throw new NullPointerException();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private static boolean isValidType(TerminalSymbol type) {
		// TODO: Check enum type
		return false;
	}
	
	/**
	 * 
	 */
	public String toString() {
		// TODO: Override based on the type of Connector
		return null;
	}

}
