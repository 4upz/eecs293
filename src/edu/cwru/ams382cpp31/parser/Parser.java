/**
 * Contained in package for EECS 293 
 */
package edu.cwru.ams382cpp31.parser;

/**
 * A class that runs the parser program
 * 
 * @author Kyle Pham
 * @author Arik Stewart
 */
class Parser {

	public static void main(String[] args) {
		handleBadInputs(args);
		
		

	}

	static void handleBadInputs(String[] args) {
		if (args.length != 1) {
			System.out.println("You need to input a string as an expression for the program to parse!"
					+ "\nPlease try again!");
		}
	}
}
