package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * A test class using JUnit 4 to tests the methods in the enum TerminalSymbol
 * @author Kyle Pham
 * @author Arik Stewart
 */
public class TerminalSymbolTest {

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
	 * Test the getRepresentation() method in the TerminalSymbol enum
	 */
	@Test
	public void testGetRepresentation() {
		assertEquals(TerminalSymbol.PLUS.getRepresentation(), "+");
		assertEquals(TerminalSymbol.MINUS.getRepresentation(), "-");
		assertEquals(TerminalSymbol.TIMES.getRepresentation(), "*");
		assertEquals(TerminalSymbol.DIVIDE.getRepresentation(), "/");
		assertEquals(TerminalSymbol.OPEN.getRepresentation(), "(");
		assertEquals(TerminalSymbol.CLOSE.getRepresentation(), ")");
	}
	
	/**
	 * Test the method parse in TerminalSymbol with a null input
	 */
	@Test
	public void testParseNull() {
		testParseNullSpecific(TerminalSymbol.PLUS);
		testParseNullSpecific(TerminalSymbol.MINUS);
		testParseNullSpecific(TerminalSymbol.TIMES);
		testParseNullSpecific(TerminalSymbol.DIVIDE);
		testParseNullSpecific(TerminalSymbol.OPEN);
		testParseNullSpecific(TerminalSymbol.CLOSE);
	}

	/**
	 * Test the method parse in TerminalSymbol with a null input for a given TerminalSymbol
	 * @param symbol	the given TerminalSymbol to test parse on
	 */
	private void testParseNullSpecific(TerminalSymbol symbol) {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("The input token list must not be null!");
		symbol.parse(null);
	}
	
	/**
	 * Tests the parse method in TerminalSymbol where it returns a failure
	 */
	@Test
	public void testParseFail() {
		testParseFailSpecific(TerminalSymbol.PLUS, Variable.build("x"));
		testParseFailSpecific(TerminalSymbol.MINUS, Connector.build(TerminalSymbol.PLUS));
		testParseFailSpecific(TerminalSymbol.TIMES, Variable.build("y"));
		testParseFailSpecific(TerminalSymbol.DIVIDE, Connector.build(TerminalSymbol.MINUS));
		testParseFailSpecific(TerminalSymbol.OPEN, Variable.build("z"));
		testParseFailSpecific(TerminalSymbol.CLOSE, Connector.build(TerminalSymbol.OPEN));
	}
	
	/**
	 * Tests the parse method with failure output for a given terminal symbol
	 * @param symbol	the terminal symbol to test parse on
	 * @param tokens	the array of tokens to be parsed using the symbol
	 */
	private void testParseFailSpecific(TerminalSymbol symbol, Token... tokens) {
		List<Token> tokenList = Arrays.asList(tokens);
		ParseState state = symbol.parse(tokenList);
		assertFalse(state.getSuccess());
		assertNull(state.getNode());
		assertNull(state.getRemainder());	
	}
}
