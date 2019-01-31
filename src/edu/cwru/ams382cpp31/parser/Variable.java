package edu.cwru.ams382cpp31.parser;

/**
 * 
 * @author Kyle Pham
 *
 */
public final class Variable extends AbstractToken {

	/**
	 * 
	 */
	private final String representation;
	
	/**
	 * 
	 * @param representation
	 */
	private Variable(String representation) {
		this.representation = representation;
	}
	
	/**
	 * 
	 * @return
	 */
	public final String getRepresentation() {
		return this.representation;
	}
	
	@Override
	public TerminalSymbol getType() {
		return TerminalSymbol.VARIABLE;
	}
	
	/**
	 * 
	 * @param representation
	 * @return
	 */
	public static final Variable build(String representation) {
		if (representation != null) {
			return new Variable(representation);
		}
		else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public String toString(){
		return this.getRepresentation();
	}
	
	

	
}
