/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.LinkedList;
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
		return remainder == null ? null : new LinkedList<>(remainder);
	}
	
	/**
	 * Checks whether the parse state has a list of remainder tokens
	 * @return	whether the parse state has a list of remainder tokens
	 */
	final boolean hasNoRemainder() {
		return getRemainder().isEmpty();
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
	
	/**
	 * Checks if the ParseState and a given object are equal
	 * @param object	a given object to be compared with the ParseState
	 * @return			true if the object is a ParseState with the same components as the current ParseState
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof ParseState && this.sameComponent((ParseState) object)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Compares the components (success, node, and remainder) between the current ParseState and a given ParseState
	 * @param state		the given ParseState to be compared with the current ParseState
	 * @return			true if both ParseState have the same components (success, node, and remainder) and
	 * 					false otherwise
	 */
	private boolean sameComponent(ParseState state) {
		if ((this.getSuccess() == state.getSuccess()
				&& this.getNode().equals(state.getNode())
				&& this.getRemainder().equals(state.getRemainder()))) {
			return true;
		}
		return false;
	}

}
