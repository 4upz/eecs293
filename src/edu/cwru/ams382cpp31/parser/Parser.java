/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

/**
 * A class that runs the parser program
 * @author Kyle Pham
 * @author Arik Stewart
 */
class Parser {

	/**
	 * The main method used to run the Parser program using command line
	 * @param args		the list of arguments given by the user, which should only contain one element for this program
	 */
	public static void main(String[] args) {
		try {
			handleBadInputs(args);
			Optional<Node> nodeParsed = NonTerminalSymbol.parseInput(tokenRepresentations(args[0]));
			if (nodeParsed.isPresent()) {
				System.out.println(nodeParsed.get().toString());
			}
			else {
				System.out.println("The given string is not a valid expression!");
			}
		}
		catch (Exception e) {
			System.out.println("An error has occured in the program! Please try again using a different input!");
		}
	}

	/**
	 * Handle bad inputs with length 0 or more than 1
	 * @param args	the input expression from the user
	 */
	private static void handleBadInputs(String[] args) {
		if (args.length != 1) {
			System.out.println("You need to input a string as an expression for the program to parse!");
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Return the list of Tokens given the string expression input
	 * @param input		the string expression input
	 * @return			the list of Tokens containing all the TerminalSymbols from the input
	 */
	private static List<Token> tokenRepresentations(String input) {
		// Split the input string into representations separated by (but also include) connectors
		String[] representations = input.split("(?=[+*-/()])|(?<=[+*-/()])");
		
		// Get an array of TerminalSymbols that corresponds to the string representations
		TerminalSymbol[] listTerminalSymbols = 
								   Stream.of(representations)															// get the stream of representations
										 .map(representation -> TerminalSymbol.symbolFromParsing(representation))		// get the stream of corresponding TerminalSymbols
										 .toArray(TerminalSymbol[]::new);
		
		// Get the list of tokens based on the array of TerminalSymbols
		List<Token> tokenList = new LinkedList<>();
		for (int i = 0; i < listTerminalSymbols.length; i++) {
			if (Connector.connectorTypes.contains(listTerminalSymbols[i])) {
				tokenList.add(Connector.build(listTerminalSymbols[i]));
			}
			else {
				tokenList.add(Variable.build(representations[i]));
			}
		}		
		return tokenList;
	}
	
}
