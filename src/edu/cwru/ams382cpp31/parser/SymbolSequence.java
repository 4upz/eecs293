/**
 * Contained in package for EECS 293
 */
package edu.cwru.ams382cpp31.parser;

import java.util.*;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * A class that serves as an individual symbol and a builder of one tree node or the children of an internal node
 */
final class SymbolSequence {

	/*
	 * Represents a list of symbols to be produced
	 */
	private final List<Symbol> production;
	
	/**
	 * Represents a SymbolSequence state with an empty production
	 */
	static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<Symbol>());
	
	/*
	 * Constructor that initializes class and sets value for production
	 */
	private SymbolSequence(List<Symbol> production) {
		this.production = production;
	}
	
	/*
	 * Getter for production
	 * @return The list of Symbols stored in production
	 */
	public final List<Symbol> getProduction(){
		return this.production;
	}
	
	/**
	 * SymbolSequence Builder that calls the constructor and returns an instance of SymbolSequence with the given production
	 * @param production given list of Symbols for new SymbolSequence
	 * @return 			 instance of SymbolSequence with production initialized as the given argument
	 */
	public static final SymbolSequence build(List<Symbol> production) {
		Objects.requireNonNull(production, "Production list cannot be null!");
		
		return new SymbolSequence(production);
	}
	
	/**
	 * Alternative  SymbolSequence builder that takes a variable number of Symbol arguments then returns a Symbol sequence 
	 * with them as a production
	 * @param symbols an array of given Symbol instances
	 * @return 	      an instance of SymbolSequence
	 */
	public static final SymbolSequence build(Symbol... symbols) {
		Objects.requireNonNull(symbols, "You must provide at least one Symbol!");
		
		return new SymbolSequence(Arrays.asList(symbols));
	}
	
	/**
	 * Calls toString to the SymbolSequence's production list
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return this.getProduction().toString();
	}
	
	/**
	 * Provides a resulting ParseState based on if all of the Symbols in the production can be matched with the input.
	 * @param input a List of Tokens to be matched
	 * @return 		a successful ParseState if the production matches and FAILURE otherwise
	 */
	public final ParseState match(List<Token> input) {
		Objects.requireNonNull(input, "Token List input cannot be null!");
		List<Token> remainder = new LinkedList<>(input);
		List<Node> children = new ArrayList<Node>();
		
		for(Symbol symbol : this.getProduction()) {
			ParseState parsedNode = symbol.parse(remainder);
			
			//Return failure if the parsing process fails and continue if successful
			if(parsedNode.equals(ParseState.FAILURE)) {
				return ParseState.FAILURE;
			}
			
			children.add(parsedNode.getNode());
			remainder = parsedNode.getRemainder();
		}
		
		return ParseState.build(InternalNode.build(children), remainder);
	}
	
}
