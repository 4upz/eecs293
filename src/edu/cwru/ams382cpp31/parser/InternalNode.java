/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.List;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * Class object to represent an internal node of tokens from the given expression
 */
public class InternalNode implements Node {

	/**
	 * A list represents the node children
	 */
	private final List<Node> children;
	
	/**
	 * Constructor for the class that initializes children
	 * @param children a given list of Nodes to be set as an InternalNode
	 */
	private InternalNode(List<Node> children) {
		this.children = children;
	}
	
	/**
	 * Retrieves the list of node children
	 * @return the value stored in children
	 */
	public List<Node> getChildren(){
		return this.children;
	}
	
	/**
	 * Creates and instantiates a new InternalNode
	 * @param children a new list of children node to be created in the InternalNode
	 * @return the newly instantiated internalNode made from the given list
	 */
	public static InternalNode build(List<Node> children) {
		if(children == null) {
			throw new NullPointerException("Children list cannot be null");
		}
		InternalNode newNode = new InternalNode(children);
		return newNode;
	}
	
	/**
	 * Returns the concatenation of the children's lists or a pre-computed copy if one was already made
	 */
	@Override
	public List<Token> toList() {
		// TODO Create concatenation algorithm along with new variable to store the concatenation
		return null;
	}
	
	/**
	 * Returns a formatted String result of the stored children
	 * @return String containing squared parentheses from children nodes
	 */
	public String toString() {
		// TODO Create String formatting algorithm along with a new variable to store the result once built
		return null;
	}

}
