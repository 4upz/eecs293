package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		assertTrue(symbol.parse(tokenList).isFailure());	
	}
	
	/**
	 * Tests the parse method in NonTerminalSymbol where it returns a successful ParseState
	 */
	@Test
	public void testParseSuccess() {
		// Test for EXPRESSION
		ParseState stateExpression = ParseState.build(LeafNode.build(Variable.build("x")), Collections.emptyList());
		testParseSuccessSpecific(NonTerminalSymbol.EXPRESSION, stateExpression, Variable.build("x"));
		
		// Test for EXPRESSION_TAIL
		ParseState stateExpressionTail = ParseState.build(LeafNode.build(Variable.build("z")), Collections.emptyList());
		testParseSuccessSpecific(NonTerminalSymbol.EXPRESSION_TAIL, stateExpressionTail, Variable.build("z"));
	}
	
	/**
	 * Tests the parse method with a successful output for a given non-terminal symbol
	 * @param symbol	the symbol the parse method is used on
	 * @param tokens	the array of tokens to be used as the parse input
	 */
	private void testParseSuccessSpecific(NonTerminalSymbol symbol, ParseState stateCompared, Token... tokens) {
		List<Token> tokenList = Arrays.asList(tokens);
		System.out.println(symbol.parse(tokenList).getNode().equals(LeafNode.build(Variable.build("x"))));
		assertEquals(symbol.parse(tokenList), stateCompared);	
	}
}
