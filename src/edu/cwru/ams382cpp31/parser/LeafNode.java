/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;
import java.util.*;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * Class that represents a "leaf" or node on the expression tree
 */
public final class LeafNode implements Node {
	
	//Token that is in this leaf node
	private final Token token;
	
	/**
	 * Constructs the LeafNode and initializes its token value
	 * @param token
	 */
	private LeafNode(Token token) {
		//Set the given value to the value stored in token
		this.token = token;
	}
	
	/**
	 * Retrieves the token in this node
	 * @return value stored in token
	 */
	private final Token getToken() {
		return this.token;
	}
	
	/**
	 * Builds a new LeafNode that will be represented with this class
	 * @param token the Token value that will be stored in the new LeafNode
	 * @return the newly constructed LeafNode that contains the given Token value
	 */
	public static final LeafNode build(Token token) {
		if(token == null) {
			throw new NullPointerException("Token type cannot be null");
		}
		LeafNode newLeaf = new LeafNode(token);
		return newLeaf;
	}
	
	/**
	 * Overrides toString method and returns the Token stored in the LeafNode instance to a string representation
	 * @return returns a string representation of the token value
	 */
	@Override
	public final String toString() {
		return this.getToken().toString();
	}
	
	/**
	 * Adds the token in this LeafNode instance to an empty list and returns that list
	 * @return a new list containing the single value stored in token
	 */
	public final List<Token> toList(){
		List<Token> list = new ArrayList<Token>();
		list.add(this.getToken());
		return list;
	}
	
	
	
}
