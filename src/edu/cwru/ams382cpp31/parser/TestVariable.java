/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Kyle Pham
 * @author Arik Stewart
 * A class using JUnit5 to test the methods in the class Variable
 */
class TestVariable {

	/**
	 * Tests the methods in the class Variable
	 */
	@Test
	void testVariable() {
		
		// Test whether a NullPointerException is thrown when building a Variable with a null representation
		assertThrows(NullPointerException.class, () -> {
			Variable.build(null);
		});
		
		// Test whether a Variable is built correctly with a given representation
		// Also test the getRepresentation() method
		String representation2 = "number";
		Variable number2 = Variable.build(representation2);
		assertEquals(number2.getRepresentation(), representation2);
		
		// Test whether a Variable with the same representation shares the same address with number2
		// thus showing that using cache avoids duplication
		String representation3 = "number";
		Variable number3 = Variable.build(representation3);
		assertSame(number3, number2);
		
		// Test the getType() method
		assertEquals(number2.getType(), TerminalSymbol.VARIABLE);
		
		// Test the toString() method
		assertEquals(number2.toString(), "number");

	}
	
}
