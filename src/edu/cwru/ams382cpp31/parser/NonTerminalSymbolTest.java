/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * A class that uses JUnit4 to tests the methods in the NonTerminalSymbol enum
 * @author Kyle Pham
 * @author Arik Stewart
 *
 */
public class NonTerminalSymbolTest {

	// Tokens to be used in the test
	private Variable a = Variable.build("a");
	private Variable b = Variable.build("b");
	private Connector plus = Connector.build(TerminalSymbol.PLUS);
	
	/**
	 * Used for testing cases where an exception is expected for invalid SequenceSymbol builds
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Test the parse method in NonTerminalSymbol with a null input token list
	 */
	@Test
	public void testParseNull() {		
		// Test the case when the input token list is null, using every non-terminal symbol to test
		testParseNullSpecific(NonTerminalSymbol.EXPRESSION);
		testParseNullSpecific(NonTerminalSymbol.EXPRESSION_TAIL);
		testParseNullSpecific(NonTerminalSymbol.TERM);
		testParseNullSpecific(NonTerminalSymbol.TERM_TAIL);
		testParseNullSpecific(NonTerminalSymbol.UNARY);
		testParseNullSpecific(NonTerminalSymbol.FACTOR);		
	}
	
	/**
	 * Tests the parse method with null input with a specific non-terminal symbol
	 * @param symbol	the symbol the parse method is used on
	 */
	private void testParseNullSpecific(NonTerminalSymbol symbol) {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("The input token list must not be null!");
		symbol.parse(null);
	}

	/**
	 * Tests the parse method in NonTerminalSymbol where it returns a failure
	 */
	@Test
	public void testParseFail() {
		testParseFailSpecific(NonTerminalSymbol.EXPRESSION, Connector.build(TerminalSymbol.OPEN));
		testParseFailSpecific(NonTerminalSymbol.TERM, Connector.build(TerminalSymbol.PLUS));
		testParseFailSpecific(NonTerminalSymbol.UNARY, Connector.build(TerminalSymbol.MINUS));
		testParseFailSpecific(NonTerminalSymbol.FACTOR, Connector.build(TerminalSymbol.PLUS));	
	}
	
	/**
	 * Tests the parse method with failure output for a given non-terminal symbol
	 * @param symbol	the symbol the parse method is used on
	 * @param tokens	the array of tokens to be used as the parse input
	 */
	private void testParseFailSpecific(NonTerminalSymbol symbol, Token... tokens) {
		List<Token> tokenList = Arrays.asList(tokens);
		assertFalse(symbol.parse(tokenList).getSuccess());	
	}
	
	/**
	 * Tests the parse method in NonTerminalSymbol where it returns a successful ParseState
	 */
	@Test
	public void testParseSuccess() {
		testParseSuccessSpecific(NonTerminalSymbol.EXPRESSION, Variable.build("x"));
		testParseSuccessSpecific(NonTerminalSymbol.EXPRESSION_TAIL, Connector.build(TerminalSymbol.MINUS),Variable.build("z"));
		testParseSuccessSpecific(NonTerminalSymbol.TERM, Connector.build(TerminalSymbol.OPEN), Variable.build("x"), Connector.build(TerminalSymbol.CLOSE));
		testParseSuccessSpecific(NonTerminalSymbol.TERM_TAIL, Variable.build("z"));
		testParseSuccessSpecific(NonTerminalSymbol.UNARY, Connector.build(TerminalSymbol.MINUS), Variable.build("x"));
		testParseSuccessSpecific(NonTerminalSymbol.FACTOR, Connector.build(TerminalSymbol.OPEN), Variable.build("y"), Connector.build(TerminalSymbol.CLOSE));
	}
	
	/**
	 * Tests the parse method with a successful output for a given non-terminal symbol
	 * @param symbol	the symbol the parse method is used on
	 * @param tokens	the array of tokens to be used as the parse input
	 */
	private void testParseSuccessSpecific(NonTerminalSymbol symbol, Token... tokens) {
		List<Token> tokenList = Arrays.asList(tokens);
		assertTrue(symbol.parse(tokenList).getSuccess());	
	}
	
	/**
	 * Test the parseInput method in NonTerminalSymbol with a null input token list
	 */
	@Test
	public void testParseInputNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("The input token list must not be null!");
		NonTerminalSymbol.parseInput(null);
	}
	
	/**
	 * Tests the parseInput method in NonTerminalSymbol where it fails and returns an empty Optional
	 */
	@Test
	public void testParseInputFail() {
		assertFalse(NonTerminalSymbol.parseInput(Arrays.asList(Connector.build(TerminalSymbol.OPEN))).isPresent());
	}
	
	/**
	 * Tests the parseInput method in NonTerminalSymbol where it succeeds and returns a nonempty Optional
	 */
	@Test
	public void testParseInputSuccess() {
		Optional<Node> node = NonTerminalSymbol.parseInput(Arrays.asList(Variable.build("x")));
		assertEquals(node.get().toList(), InternalNode.build(Arrays.asList(LeafNode.build(Variable.build("x")))).toList());
	}
}
