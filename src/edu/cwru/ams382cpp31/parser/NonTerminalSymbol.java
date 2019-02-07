/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * An enum that represents a non-terminal symbol
 * @author Kyle Pham
 * @author Arik Stewart 
 */
public enum NonTerminalSymbol implements Symbol {
	
	EXPRESSION,			// an expression
	EXPRESSION_TAIL,	// an expression tail
	TERM,				// a term
	TERM_TAIL,			// a term tail 
	UNARY,				// a unary operator
	FACTOR;				// a factor
	
	/*
	 * An enum map that maps the values of the non-terminal symbols (enum) to their possible productions, 
	 * which are lists of symbol sequences
	 */
	private static final Map<NonTerminalSymbol, List<SymbolSequence>> map = 
			new EnumMap<>(NonTerminalSymbol.class);
	
	/**
	 * Maps the productions with the corresponding non-terminal symbols in the map 
	 */
	static {
		
		map.put(NonTerminalSymbol.EXPRESSION, 
				Arrays.asList(
						SymbolSequence.build(NonTerminalSymbol.TERM, NonTerminalSymbol.EXPRESSION_TAIL)
				)
		);
		
		map.put(NonTerminalSymbol.EXPRESSION_TAIL,
				Arrays.asList(
						SymbolSequence.build(TerminalSymbol.PLUS, NonTerminalSymbol.TERM, NonTerminalSymbol.EXPRESSION_TAIL),
						SymbolSequence.build(TerminalSymbol.MINUS, NonTerminalSymbol.TERM, NonTerminalSymbol.EXPRESSION_TAIL),
						SymbolSequence.EPSILON
				)
		);
		
		map.put(NonTerminalSymbol.TERM, 
				Arrays.asList(
						SymbolSequence.build(NonTerminalSymbol.UNARY, NonTerminalSymbol.TERM_TAIL)
				)
		);
		
		map.put(NonTerminalSymbol.TERM_TAIL, 
				Arrays.asList(
						SymbolSequence.build(TerminalSymbol.TIMES, NonTerminalSymbol.UNARY, NonTerminalSymbol.TERM_TAIL),
						SymbolSequence.build(TerminalSymbol.DIVIDE, NonTerminalSymbol.UNARY, NonTerminalSymbol.TERM_TAIL),
						SymbolSequence.EPSILON
				)
		);
		
		map.put(NonTerminalSymbol.UNARY, 
				Arrays.asList(
						SymbolSequence.build(TerminalSymbol.MINUS, NonTerminalSymbol.FACTOR),
						SymbolSequence.build(NonTerminalSymbol.FACTOR)
				)
		);
		
		map.put(NonTerminalSymbol.FACTOR,
				Arrays.asList(
						SymbolSequence.build(TerminalSymbol.OPEN, NonTerminalSymbol.EXPRESSION, TerminalSymbol.CLOSE),
						SymbolSequence.build(TerminalSymbol.VARIABLE)
				)
		);
		
	}
	
	/**
	 * The non-terminal symbol parses the input token list by going through its productions and check if any of them
	 * matches the token list
	 * @param input		the input token list to be parsed by the non-terminal symbol
	 * @return 			a ParseState corresponding to the first production that matches the token list, or a failure
	 * 					ParseState if none of the productions matches the token list.
	 */
	@Override
	public ParseState parse(List<Token> input) {
		Objects.requireNonNull(input, "The input token list must not be null!");
		
		for (SymbolSequence sequence : map.get(this)) {
			ParseState possibleParseState = sequence.match(input);
			if (possibleParseState.getSuccess()) {
				return possibleParseState;
			}
		}
		return ParseState.FAILURE;
	}
	
	/**
	 * Attempts to parse the input token list with an EXPRESSION, and return the root node if the parsing process is
	 * successful and the parse state has no remainder
	 * @param input		the input token list to be parsed by the EXPRESSION non-terminal symbol
	 * @return			an Optional of the root node from a ParseState created from a match that is successful and
	 * 					has no remainder, or an empty Optional otherwise
	 */
	static final Optional<Node> parseInput(List<Token> input) {
		Objects.requireNonNull(input, "The input token list must not be null!");
		
		ParseState possibleState = NonTerminalSymbol.EXPRESSION.parse(input);
		if (possibleState.getSuccess() && possibleState.hasNoRemainder()) {
			return Optional.of(possibleState.getNode());
		}
		else {
			return Optional.empty();
		}

	}
	
}
