/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 */
class LeafNodeTest {

	//Connectors to be used in testing
	Connector add = Connector.build(TerminalSymbol.PLUS);
	Connector minus = Connector.build(TerminalSymbol.MINUS);
	Connector multiply = Connector.build(TerminalSymbol.TIMES);
	Connector divide = Connector.build(TerminalSymbol.DIVIDE);
			
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
	final void testToString() {
		//Test the Connectors
		node = LeafNode.build(add);
		assertEquals("Add Token Test", "+", node.toString());
		node = LeafNode.build(minus);
		assertEquals("Minus Token Test", "-", node.toString());
		node = LeafNode.build(multiply);
		assertEquals("Multiply Token Test", "*", node.toString());
		node = LeafNode.build(divide);
		assertEquals("Divide Token Test", "/", node.toString());

		//Test the Variables
		node = LeafNode.build(a);
		assertEquals("'a' Variable Test", "a", node.toString());
		node = LeafNode.build(b);
		assertEquals("'b' Variable Test", "b", node.toString());
		node = LeafNode.build(c);
		assertEquals("'c' Variable Test", "c", node.toString());
		
		//Test null
		assertEquals("Testing a null value", new NullPointerException(), LeafNode.build(null));
		
	}

	/**
	 * Test method for {@link edu.cwru.ams382cpp31.parser.LeafNode#toList()}.
	 * Tests the toList method
	 * The build method, getToken and constructor in LeafNode is implicitly tested using the cases
	 */
	@Test
	final void testToList() {
		//Test the Connectors
		node = LeafNode.build(add);
		assertEquals("Add Token Test", Arrays.asList(add), node.toList());
		node = LeafNode.build(minus);
		assertEquals("Minus Token Test", Arrays.asList(minus), node.toList());
		node = LeafNode.build(multiply);
		assertEquals("Multiply Token Test", Arrays.asList(multiply), node.toList());
		node = LeafNode.build(divide);
		assertEquals("Divide Token Test", Arrays.asList(divide), node.toList());
		
		//Test some Variables
		node = LeafNode.build(a);
		assertEquals("'a' Variable Test", Arrays.asList(a), node.toList());
		node = LeafNode.build(b);
		assertEquals("'b' Variable Test", Arrays.asList(b), node.toList());
		node = LeafNode.build(c);
		assertEquals("'c' Variable Test", Arrays.asList(c), node.toList());
	}

}
