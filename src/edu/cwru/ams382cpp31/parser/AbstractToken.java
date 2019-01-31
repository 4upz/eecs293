/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

/**
 * An abstract class used to represent tokens of different types in a mathematical expression
 * @author Kyle Pham
 *
 */
public abstract class AbstractToken implements Token {

	@Override
	public abstract TerminalSymbol getType(); 
	
	@Override
	public boolean matches(TerminalSymbol type) {
		if (getType() == type) {
			return true;
		}
		else {
			return false;
		}
	}

}
