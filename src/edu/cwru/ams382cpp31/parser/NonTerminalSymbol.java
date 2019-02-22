/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
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
	
	class TerminalMap {
		
		/**
		 * An enum map that holds the TerminalSymbols and their corresponding SymbolSequences
		 */
		private final Map<TerminalSymbol, SymbolSequence> terminalMap = new HashMap<>();
		
		/**
		 * Retrieves the stored terminalMap
		 * @return the Map from TerminalSymbol to SymbolSequence stored in terminalMap
		 */
		final Map<TerminalSymbol, SymbolSequence> getTerminalMap(){
			return new HashMap<TerminalSymbol, SymbolSequence>(terminalMap);
		}
		
		/**
		 * Maps multiple keys to the terminalMap
		 * @param sequence 	the SymbolSequence that all of the given TerminalSymbols should map to
		 * @param symbols 	the list of symbols that need to be mapped
		 */
		private void mapMultipleKeys(SymbolSequence sequence, List<TerminalSymbol> symbols) {
			symbols.forEach(symbol -> terminalMap.put(symbol, sequence));
		}
		
		/**
		 * Maps multiple keys to the terminalMap
		 * @param sequence 	the SymbolSequence that all of the given TerminalSymbols should map to
		 * @param symbols 	the set of symbols that need to be mapped
		 */
		private void mapMultipleKeys(SymbolSequence sequence, TerminalSymbol... symbols) {
			mapMultipleKeys(sequence, Arrays.asList(symbols));
		}

	}
	
	/*
	 * An enum map that maps the values of the non-terminal symbols (enum) to their possible productions, 
	 * which are lists of symbol sequences
	 */
	private static final Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = 
			new EnumMap<>(NonTerminalSymbol.class);
	
	/**
	 * Maps the productions with the corresponding TerminalMap in the map 
	 */
	static {
		
		// TerminalMap for EXPRESSION
		NonTerminalSymbol.TerminalMap expressionMap = EXPRESSION.new TerminalMap();
		expressionMap.mapMultipleKeys(SymbolSequence.build(TERM, EXPRESSION_TAIL), 
				TerminalSymbol.MINUS, TerminalSymbol.OPEN, TerminalSymbol.VARIABLE);
		productions.put(EXPRESSION, expressionMap.getTerminalMap());
		
		// TerminalMap for EXPRESSION_TAIL
		NonTerminalSymbol.TerminalMap expressionTailMap = EXPRESSION_TAIL.new TerminalMap();
		expressionTailMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL),
				TerminalSymbol.PLUS);
		expressionTailMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL),
				TerminalSymbol.MINUS);
		expressionTailMap.mapMultipleKeys(SymbolSequence.EPSILON,
				TerminalSymbol.CLOSE, null);
		productions.put(EXPRESSION_TAIL, expressionTailMap.getTerminalMap());
		
		// TerminalMap for TERM
		NonTerminalSymbol.TerminalMap termMap = TERM.new TerminalMap();
		termMap.mapMultipleKeys(SymbolSequence.build(UNARY, TERM_TAIL),
				TerminalSymbol.MINUS, TerminalSymbol.OPEN, TerminalSymbol.VARIABLE);
		productions.put(TERM, termMap.getTerminalMap());
		
		// TerminalMap for TERM_TAIL
		NonTerminalSymbol.TerminalMap termTailMap = TERM_TAIL.new TerminalMap();
		termTailMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL),
				TerminalSymbol.TIMES);
		termTailMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL),
				TerminalSymbol.DIVIDE);
		termTailMap.mapMultipleKeys(SymbolSequence.EPSILON, 
				TerminalSymbol.CLOSE, TerminalSymbol.PLUS, TerminalSymbol.MINUS, null);
		productions.put(TERM_TAIL, termTailMap.getTerminalMap());
		
		// TerminalMap for UNARY
		NonTerminalSymbol.TerminalMap unaryMap = UNARY.new TerminalMap();
		unaryMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR), 
				TerminalSymbol.MINUS);
		unaryMap.mapMultipleKeys(SymbolSequence.build(FACTOR), 
				TerminalSymbol.OPEN, TerminalSymbol.VARIABLE);
		productions.put(UNARY, unaryMap.getTerminalMap());
		
		// TerminalMap for FACTOR
		NonTerminalSymbol.TerminalMap factorMap = FACTOR.new TerminalMap();
		factorMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE), 
				TerminalSymbol.OPEN);
		factorMap.mapMultipleKeys(SymbolSequence.build(TerminalSymbol.VARIABLE), 
				TerminalSymbol.VARIABLE);
		productions.put(FACTOR, factorMap.getTerminalMap());
		
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
		
		SymbolSequence availableSequence;
		if(input.isEmpty())
			availableSequence = SymbolSequence.EPSILON;
		else {
			availableSequence = productions.get(this).get(input.get(0).getType());
		}
		
		if (availableSequence == null) {
			return ParseState.FAILURE;
		}
		
		ParseState possibleParseState = availableSequence.match(input);
		return possibleParseState.getSuccess() ? possibleParseState : ParseState.FAILURE;
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
