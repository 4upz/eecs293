/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.List;
import java.util.Objects;

/**
 * A class that represents the state of the parsing process
 * @author Kyle Pham
 * @author Arik Stewart
 */
final class ParseState {
	
	/**
	 * Denotes whether the parsing process was successful
	 */
	private final boolean success;
	
	/**
	 * The node which is the result of parsing an initial prefix of the list
	 */
	private final Node node;
	
	/**
	 * The part of the token list that was left over after parsing the first node
	 */
	private final List<Token> remainder;
	
	/**
	 * The failure state where the parsing is not done successfully
	 */
	final static ParseState FAILURE = new ParseState(false, null, null);
	
	/**
	 * A private constructor for instantiating a new ParseState
	 */
	private ParseState(boolean success, Node node, List<Token> remainder) {
		this.success = success;
		this.node = node;
		this.remainder = remainder;
	}

	/**
	 * Gets whether the parsing process was successful
	 * @return	the state whether the parsing process was successful
	 */
	final boolean getSuccess() {
		return success;
	}
	
	/**
	 * Gets the node containing the initial prefix of the token list
	 * @return	the node containing the initial prefix of the token list
	 */
	final Node getNode() {
		return node;
	}
	
	/**
	 * Gets the remainder list of tokens that was left over after parsing the first node
	 * @return	the remainder list of tokens that was left over after parsing the first node
	 */
	final List<Token> getRemainder() {
		return remainder;
	}
	
	/**
	 * Checks whether the parse state has a list of remainder tokens
	 * @return	whether the parse state has a list of remainder tokens
	 */
	final boolean hasNoRemainder() {
		return (getRemainder() != null);
	}
	
	/**
	 * Builds a new ParseState with the given initial node and remainder token list
	 * @param node		the given initial node of the ParseState
	 * @param remainder	the remainder token list of the ParseState
	 * @return			the ParseState successfully parsed with the given initial node and remainder token list
	 */
	static final ParseState build(Node node, List<Token> remainder) {
		Objects.requireNonNull(node, "The input initial node should not be null!");
		Objects.requireNonNull(remainder, "The input remainder token list should not be null!");
		
		return new ParseState(true, node, remainder);
	}
	
}
