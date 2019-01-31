package edu.cwru.ams382cpp31.parser;

/**
 * 
 * @author Kyle Pham
 *
 */
public interface Token {
	
	/**
	 * 
	 * @return
	 */
	TerminalSymbol getType();
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	boolean matches(TerminalSymbol type);
		
}
