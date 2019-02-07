/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.List;
import java.util.Optional;

/**
 * @author Kyle Pham
 * @author Arik Stewart
 * An enum that represents a non-terminal symbol
 */
public enum NonTerminalSymbol implements Symbol {

	EXPRESSION(
			SymbolSequence.build(
					NonTerminalSymbol.TERM(),
					NonTerminalSymbol.EXPRESSION_TAIL()
			)
	),
	
	EXPRESSION_TAIL( 
			SymbolSequence.build(
					TerminalSymbol.PLUS,
					NonTerminalSymbol.TERM(),
					NonTerminalSymbol.EXPRESSION_TAIL()
			),
			SymbolSequence.build(
					TerminalSymbol.MINUS,
					NonTerminalSymbol.TERM(),
					NonTerminalSymbol.EXPRESSION_TAIL()
			),
			SymbolSequence.EPSILON
	),
	
	TERM(
			SymbolSequence.build(
					NonTerminalSymbol.UNARY(),
					NonTerminalSymbol.TERM_TAIL()
			)
	),
	
	TERM_TAIL( 
			SymbolSequence.build(
					TerminalSymbol.TIMES,
					NonTerminalSymbol.UNARY(),
					NonTerminalSymbol.TERM_TAIL()
			),
			SymbolSequence.build(
					TerminalSymbol.DIVIDE,
					NonTerminalSymbol.UNARY(),
					NonTerminalSymbol.TERM_TAIL()
			),
			SymbolSequence.EPSILON
	),
	
	UNARY(
			SymbolSequence.build(
					TerminalSymbol.MINUS,
					NonTerminalSymbol.FACTOR()
			),
			SymbolSequence.build(
					NonTerminalSymbol.FACTOR()
			)
	),
	
	FACTOR(
			SymbolSequence.build(
					TerminalSymbol.OPEN,
					NonTerminalSymbol.EXPRESSION(),
					TerminalSymbol.CLOSE
			),
			SymbolSequence.build(
					TerminalSymbol.VARIABLE
			)
	);
	
	/**
	 * The string representation of the Terminal Symbol
	 */
	private SymbolSequence[] productions;
	
	/**
	 * Constructs a TerminalSymbol with its string representation
	 * @param representation	the given string representation
	 */
	private NonTerminalSymbol() {
		this.productions = null;
	}

	/**
	 * 
	 * @param productions
	 */
	private NonTerminalSymbol(SymbolSequence... productions) {
		this.productions = productions;
	}
	
	@Override
	public ParseState parse(List<Token> input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	static final Optional<Node> parseInput(List<Token> input) {
		
	}
	
}
