/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.List;

/**
 * @author Kyle Pham
 * @author Arik Stewart
 * 
 */
interface Symbol {

	/**
	 * Parse the input token list into a node, creating a ParseState and possibly leaving a remainder
	 * @param input	the list of token to be parsed into the node and remainder of a ParseState
	 * @return		a ParseState with the node with the input and a (possibly nonempty) remainder 
	 */
	ParseState parse(List<Token> input);
}
