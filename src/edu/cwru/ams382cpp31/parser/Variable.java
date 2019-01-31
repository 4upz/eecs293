package edu.cwru.ams382cpp31.parser;

import java.util.Objects;

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
	 */
	private static Cache<String, Variable> cache = new Cache<String, Variable>();
	
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
		Objects.requireNonNull(representation, "The string representation should not be null!");
		
		return cache.get(representation, Variable::new);
	}
	
	@Override
	public String toString(){
		return this.getRepresentation();
	}
	
	

	
}
