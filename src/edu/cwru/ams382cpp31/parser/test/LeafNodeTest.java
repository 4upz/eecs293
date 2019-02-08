/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser.test;

import edu.cwru.ams382cpp31.parser.*;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * A class using JUnit4 to test the methods in the class LeafNode
 */
public class LeafNodeTest {

	//Connectors to be used in testing
	Connector add = Connector.build(TerminalSymbol.PLUS);
	Connector minus = Connector.build(TerminalSymbol.MINUS);
	Connector multiply = Connector.build(TerminalSymbol.TIMES);
	Connector divide = Connector.build(TerminalSymbol.DIVIDE);
	Connector open = Connector.build(TerminalSymbol.OPEN);
	Connector close = Connector.build(TerminalSymbol.CLOSE);
	
	//Variables to be used in testing
	Variable a = Variable.build("a");
	Variable b = Variable.build("b");
	Variable c = Variable.build("c");
		
	//LeafNode instance variable to be modified for each test
	LeafNode node;

	/**
	 * Test method for {@link edu.cwru.ams382cpp31.parser.LeafNode#toString()}.
	 * Tests the toString method
	 * The build method, getToken and constructor in LeafNode is implicitly tested using the cases
	 */
	@Test
	public final void testToString() {
		//Test the Connectors
		node = LeafNode.build(add);
		assertEquals("Add Token Test", "+", node.toString());
		node = LeafNode.build(minus);
		assertEquals("Minus Token Test", "-", node.toString());
		node = LeafNode.build(multiply);
		assertEquals("Multiply Token Test", "*", node.toString());
		node = LeafNode.build(divide);
		assertEquals("Divide Token Test", "/", node.toString());
		node = LeafNode.build(open);
		assertEquals("Open Paranthesis Token Test", "(", node.toString());
		node = LeafNode.build(close);
		assertEquals("Closed Paranthesis Token Test", ")", node.toString());
		
		//Test the Variables
		node = LeafNode.build(a);
		assertEquals("'a' Variable Test", "a", node.toString());
		node = LeafNode.build(b);
		assertEquals("'b' Variable Test", "b", node.toString());
		node = LeafNode.build(c);
		assertEquals("'c' Variable Test", "c", node.toString());
		
		//Test a null value for build
		try {
            LeafNode.build(null);
        } catch (NullPointerException e) {
            assertEquals("Null Token Test", "Token type cannot be null", e.getMessage());
        }
		
	}

	/**
	 * Test method for {@link edu.cwru.ams382cpp31.parser.LeafNode#toList()}.
	 * Tests the toList method
	 * The build method, getToken and constructor in LeafNode is implicitly tested using the cases
	 */
	@Test
	public final void testToList() {
		//Test the Connectors
		node = LeafNode.build(add);
		assertEquals("Add Token Test", Arrays.asList(add), node.toList());
		node = LeafNode.build(minus);
		assertEquals("Minus Token Test", Arrays.asList(minus), node.toList());
		node = LeafNode.build(multiply);
		assertEquals("Multiply Token Test", Arrays.asList(multiply), node.toList());
		node = LeafNode.build(divide);
		assertEquals("Divide Token Test", Arrays.asList(divide), node.toList());
		node = LeafNode.build(open);
		assertEquals("Open Paranthesis Token Test", Arrays.asList(open), node.toList());
		node = LeafNode.build(close);
		assertEquals("Closed Paranthesis Token Test", Arrays.asList(close), node.toList());
		
		//Test some Variables
		node = LeafNode.build(a);
		assertEquals("'a' Variable Test", Arrays.asList(a), node.toList());
		node = LeafNode.build(b);
		assertEquals("'b' Variable Test", Arrays.asList(b), node.toList());
		node = LeafNode.build(c);
		assertEquals("'c' Variable Test", Arrays.asList(c), node.toList());
	}

	/**
	 * Tests the method equals in LeafNode
	 */
	@Test
	public void testEquals() {
		// Test the case when equals returns true
		LeafNode ln1 = LeafNode.build(Variable.build("x"));
		LeafNode ln2 = LeafNode.build(Variable.build("x"));
		assertEquals(ln1, ln2);
		
		// Test the case when equals returns false
		LeafNode ln3 = LeafNode.build(Variable.build("y"));
		assertFalse(ln1.equals(ln3));
	}
}
