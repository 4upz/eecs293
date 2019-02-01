/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Kyle Pham
 * @author Arik Stewart
 * A class using JUnit5 to test the methods in the class Connector
 */
class TestConnector {

	/**
	 * Tests the methods in the class Variable
	 */
	@Test
	void testConnector() {

		// Test whether a NullPointerException is thrown when building a Connector with a null type
		assertThrows(NullPointerException.class, () -> {
			Connector.build(null);
		});
		
		// Test whether an IllegalArgumentException is thrown when an inappropriate type of TerminalSymbol is given in build()
		TerminalSymbol type_1 = TerminalSymbol.VARIABLE;
		assertThrows(IllegalArgumentException.class, () -> {
			Connector.build(type_1);
		});
		
		// Test the getType(), build(), and toString() method with the input being TerminalSymbol.PLUS
		testValidSymbols(TerminalSymbol.PLUS, "+");
		
		// Test the getType(), build(), and toString() method with the input being TerminalSymbol.MINUS
		testValidSymbols(TerminalSymbol.MINUS, "-");
		
		// Test the getType(), build(), and toString() method with the input being TerminalSymbol.TIMES
		testValidSymbols(TerminalSymbol.TIMES, "*");
		
		// Test the getType(), build(), and toString() method with the input being TerminalSymbol.DIVIDE
		testValidSymbols(TerminalSymbol.DIVIDE, "/");
		
		// Test the getType(), build(), and toString() method with the input being TerminalSymbol.OPEN
		testValidSymbols(TerminalSymbol.OPEN, "(");
		
		// Test the getType(), build(), and toString() method with the input being TerminalSymbol.CLOSE
		testValidSymbols(TerminalSymbol.CLOSE, ")");
		
	}
	
	/**
	 * Tests the methods build(), getType(), and toString() with a valid type and compares with the expected values
	 * @param type				the type of the connector
	 * @param representation	the string representation of the connector
	 */
	private void testValidSymbols(TerminalSymbol type, String representation) {
		Connector connectorSymbol = Connector.build(type);
		assertEquals(connectorSymbol.getType(), type);		// Test getType() method
		Connector connectorSymbol2 = Connector.build(type);
		assertSame(connectorSymbol2, connectorSymbol);		// Test whether build prevents duplication of connectors of the same type
		assertEquals(connectorSymbol.toString(), representation);	// Test the toString() method for connector of with symbol "type"
	}

}
