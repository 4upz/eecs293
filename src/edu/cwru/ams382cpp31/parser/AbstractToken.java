package edu.cwru.ams382cpp31.parser;

/**
 * 
 * @author Kyle Pham
 *
 */
public abstract class AbstractToken implements Token {

	@Override
	public abstract TerminalSymbol getType(); 
	
	@Override
	public boolean matches(TerminalSymbol type) {
		if (type == getType()) {
			return true;
		}
		else {
			return false;
		}
	}

}
