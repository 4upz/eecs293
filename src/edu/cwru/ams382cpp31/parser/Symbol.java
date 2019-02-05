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
	 * Parse the input token list into a node, creating a ParseState and 
	 * @param input
	 * @return
	 */
	ParseState parse(List<Token> input);
}
