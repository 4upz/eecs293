/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser.test;

import edu.cwru.ams382cpp31.parser.*;

import static org.junit.Assert.*;

import org.junit.*;

import org.junit.Test;

import org.junit.rules.ExpectedException;


/**
 * @author Kyle Pham
 * @author Arik Stewart
 * A class using JUnit4 to test the methods in the class Connector
 */
public class ConnectorTest {

	/**
	 * Tests that a null exception is thrown from in class Connector when a null value is given in build()
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testNull() throws NullPointerException {
		thrown.expect(Exception.class);
		thrown.expectMessage("The terminal symbol should not be null!");
		Connector.build(null);
	}
	
	/**
	 * Tests that an IllegalArgumentException is thrown when an inappropriate type of TerminalSymbol is given in build()
	 */
	
	@Test
	public void  testIllegalArgument() throws IllegalArgumentException{
		thrown.expect(Exception.class);
		thrown.expectMessage("The input terminal symbol type is invalid!");
		TerminalSymbol type_1 = TerminalSymbol.VARIABLE;
		Connector.build(type_1);
	}
	
	/**
	 * Tests the methods in the class Connector
	 */
	@Test
	public void testConnector() {

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
