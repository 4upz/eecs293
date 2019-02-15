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
	
	/**
	 * Token that is stored in this leaf node
	 */
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
	public final Token getToken() {
		return this.token;
	}
	
	/**
	 * Builds a new LeafNode that will be represented with this class
	 * @param token the Token value that will be stored in the new LeafNode
	 * @return the newly constructed LeafNode that contains the given Token value
	 */
	public static final LeafNode build(Token token) {		
		return new LeafNode(Objects.requireNonNull(token, "Token type cannot be null"));
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
		return Arrays.asList(this.getToken());
	}
	
	/**
	 * Checks if the leaf node is equal to a given object
	 * @param object	an object to be compared to the leaf node
	 * @return			true if the object is a leaf node with the same token and false otherwise
	 */
	@Override
	public boolean equals(Object object) {
		return object instanceof LeafNode && ((LeafNode) object).getToken().equals(this.getToken());
	}

	/**
	 * Gets the children of the LeafNode
	 * @return null since a LeafNode does not contain node children
	 */
	@Override
	public List<Node> getChildren() {
		return null;
	}

	/**
	 * Determines whether the LeafNode holds a value
	 * @return true since a LeafNode always has a stored Token
	 */
	@Override
	public boolean isFruitful() {
		return true;
	}
	
}
