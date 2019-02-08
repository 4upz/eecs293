/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * @author Kyle Pham
 * @author Arik Stewart
 * A class using JUnit4 to test the methods in the class ParseState
 */
public class ParseStateTest {

	//Connectors to be used in testing
	private Connector add = Connector.build(TerminalSymbol.PLUS);
			
	//Variables to be used in testing
	private Variable a = Variable.build("a");
	private Variable b = Variable.build("b");
		
	/**
	 * Tests that a null exception is thrown from in class ParseState when a null value is given in build()
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Tests the method build in the class ParseState in the case of failing
	 */
	@Test
	public void testBuildFail() {
		
		// Test the case where the input node is null
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("The input initial node should not be null!");
		ParseState.build(null, Arrays.asList(a, add, b));
		
		// Test the case where the input remainder list is null
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("The input remainder token list should not be null!");
		ParseState.build(LeafNode.build(a), null);
		
	}
	
	/**
	 * Tests the methods in the class ParseState after building successfully
	 */
	@Test
	public void testBuildSuccess() {
		
		// Test getSuccess, getNode, getRemainder, and hasNoRemainder if the remainder input is not empty
		Node node = LeafNode.build(a);
		List<Token> remainder = Arrays.asList(a, add, b);
		ParseState ps1 = ParseState.build(node, remainder);
		
		assertEquals(ps1.getSuccess(), true);
		assertEquals(ps1.getNode(), node);
		assertEquals(ps1.getRemainder(),  remainder);
		assertEquals(ps1.hasNoRemainder(), false);

		// Test hasNoRemainder if the remainder input is empty
		ParseState ps2 = ParseState.build(node, new ArrayList<Token>());
		assertEquals(ps2.hasNoRemainder(), true);
		
	}
	
	/**
	 * Tests the method equals in ParseState
	 */
	@Test
	public void testEquals() {
		// Test the case when equals returns true
		ParseState ps1 = ParseState.build(LeafNode.build(a), Arrays.asList(a, add, b));
		ParseState ps2 = ParseState.build(LeafNode.build(a), Arrays.asList(a, add, b));
		assertEquals(ps1, ps2);
		
		// Test the case comparing with null
		assertNotEquals(ps1, null);
		
		// Test the case comparing with a different object type
		assertNotEquals(ps1, "a");
		
		// Test the case when equals returns false as the ParseStates were built from different inputs
		assertNotEquals(ps1, ParseState.build(LeafNode.build(a), Arrays.asList(a, add, a)));
	}
}
