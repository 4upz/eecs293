/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.*;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * Interface to be implemented by node objects within the expression tree
 */
public interface Node {

	/**
	 * Method stub for the toList method which returns a list representation of a subtree
	 * @return	the List of nodes that represents the subtree rooted at the current node
	 */
	List<Token> toList();
	
	/*
	 * Gets the copy of the node's children or null if it is a LeafNode
	 * @return a list of node children or a null value if none exists
	 */
	List<Node> getChildren();
	
	/**
	 * returns whether this node has at least one child or is a leaf
	 * @return true or false value for if the InternalNode has more than one child or if it is a LeafNode
	 */
	boolean isFruitful();
	
	/**
	 * Checks if the node is equal to a given object
	 * @param object	an object to be compared to the node
	 * @return			true if the node is equal to the object and false otherwise
	 */
	boolean equals(Object object);
	
	/**
	 * Determines whether a node is a leaf corresponding to an operator
	 * @return true if it the leaf does correspond to an operator and false otherwise
	 */
	boolean isOperator();
	
	/**
	 * Determines if the first child of the node is started by an operator
	 * @return true if the node’s first child is an operator, and false otherwise
	 */
	boolean isStartedByOperator();
	
	/**
	 * Retrieves the first child of the node if it has one
	 * @return the first child of this node or empty if unfruitful
	 */
	Optional<Node> firstChild();
	
	/**
	 * Determines whether the node's only child is a leaf
	 * @return true if it only contains a leaf and false otherwise
	 */
	boolean isSingleLeafParent();
}
