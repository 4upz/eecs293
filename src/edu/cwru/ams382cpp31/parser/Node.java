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

	//Method stub for the toList method which returns a list representation of a subtree
	abstract List<Token> toList();
	
}