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
	
	/**
	 * Checks if the node is equal to a given object
	 * @param object	an object to be compared to the node
	 * @return			true if the node is equal to the object and false otherwise
	 */
	boolean equals(Object object);
}
