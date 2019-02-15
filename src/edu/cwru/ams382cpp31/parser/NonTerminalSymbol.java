/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	 class TerminalMap{
		
		/**
		 * An enum map that holds the TerminalSymbols and their corresponding SymbolSequences
		 */
		private final Map<TerminalSymbol, SymbolSequence> terminalMap = new EnumMap<>(TerminalSymbol.class);
		
		/**
		 * Retrieves the stored terminalMap
		 * @return the Map from TerminalSymbol to SymbolSequence stored in terminalMap
		 */
		final Map<TerminalSymbol, SymbolSequence> getTerminalMap(){
			return new EnumMap<TerminalSymbol, SymbolSequence>(terminalMap);
		}
		
		/**
		 * Maps multiple keys to the terminalMap
		 * @param sequence the SymbolSequence that all of the given TerminalSymbols should map to
		 * @param symbols the list of symbols that need to be mapped
		 */
		private void mapMultipleKeys(SymbolSequence sequence, List<TerminalSymbol> symbols) {
			symbols.forEach(symbol -> terminalMap.put(symbol, sequence));
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
		//TerminalMap for EXPRESSION
		assignMap(
				EXPRESSION, 
				Arrays.asList(SymbolSequence.build(TERM, EXPRESSION_TAIL)),
				Arrays.asList(Arrays.asList(TerminalSymbol.values()))
		);
		
		//TerminalMap for EXPRESSION_TAIL
		assignMap(
				EXPRESSION_TAIL,
				Arrays.asList(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL),
							SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL)),
				Arrays.asList(Arrays.asList(TerminalSymbol.PLUS),
							Arrays.asList(TerminalSymbol.MINUS))
		);
		
		//TerminalMap for TERM
		assignMap(
				TERM,
				Arrays.asList(SymbolSequence.build(UNARY, TERM_TAIL)),
				Arrays.asList(Arrays.asList(TerminalSymbol.values()))
		);
		
		//TerminalMap for TERM_TAIL
		assignMap(
				TERM_TAIL,
				Arrays.asList(SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL),
							SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL)),
				Arrays.asList(Arrays.asList(TerminalSymbol.TIMES),
							Arrays.asList(TerminalSymbol.DIVIDE))
		);
		
		//TerminalMap for TAIL
		assignMap(
				UNARY,
				Arrays.asList(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR),
							SymbolSequence.build(FACTOR)),
				Arrays.asList(Arrays.asList(TerminalSymbol.MINUS),
							// Any TerminalSymbol other than MINUS
							Arrays.stream(TerminalSymbol.values()).filter(
									value -> !value.equals(TerminalSymbol.MINUS)).collect(
											Collectors.toList()))
		);
		
		//TerminalMap for FACTOR
		assignMap(
				FACTOR,
				Arrays.asList(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE),
							SymbolSequence.build(TerminalSymbol.VARIABLE)),
				Arrays.asList(Arrays.asList(TerminalSymbol.OPEN),
							// Any TerminalSymbol other than OPEN
							Arrays.stream(TerminalSymbol.values()).filter(
								value -> !value.equals(TerminalSymbol.OPEN)).collect(
										Collectors.toList()))
		);	
	}
	
	/**
	 * Static method to assigns the appropriate Map to a given NonTerminalSymbol type
	 * @param type					the type of NonTerminalSymbol to map
	 * @param listSequences			the list of possible productions
	 * @param listTerminalSymbols	the list of lists of TerminalSymbols for each production
	 */
	private static void assignMap(NonTerminalSymbol type, List<SymbolSequence> listSequences, List<List<TerminalSymbol>> listTerminalSymbols) {
		NonTerminalSymbol.TerminalMap typeMap = type.new TerminalMap();
		Iterator<SymbolSequence> itSequence = listSequences.iterator();
		Iterator<List<TerminalSymbol>> itTerminalSymbols = listTerminalSymbols.iterator();
		while (itSequence.hasNext() && itTerminalSymbols.hasNext()) {
			typeMap.mapMultipleKeys(itSequence.next(), itTerminalSymbols.next());
		}
		productions.put(type, typeMap.getTerminalMap());
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
			availableSequence =  productions.get(this).getOrDefault(input.get(0).getType(), SymbolSequence.EPSILON);
		}
		ParseState possibleParseState = availableSequence.match(input);
		if (possibleParseState.getSuccess()) {
			return possibleParseState;
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
