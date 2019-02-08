package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SymbolSequenceTest {

	//Tokens to be used in the test
	Variable a = Variable.build("a");
	Variable b = Variable.build("b");
	Connector plus = Connector.build(TerminalSymbol.PLUS);
	
	/**
	 * Used for testing cases where an exception is expected for invalid SequenceSymbol builds
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Tests the initialization of a SymbolSequence. This includes the two build methods, constructor, and getter method for production
	 */
	@Test
	public void testBuild() {
		List<Symbol> testList;
		
		//Test null value for exception throw of first builder
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("Production list cannot be null!");
		testList = null;
		SymbolSequence.build(testList);
				
		//Test null value for exception throw of alternative builder
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("Production list cannot be null!");
		SymbolSequence.build(null, null);
		
		//Tests a successfully built SymbolSequence with only one value in production
		testList = Arrays.asList(TerminalSymbol.PLUS);
		SymbolSequence testSequence = SymbolSequence.build(testList);
		assertEquals("Testing One Symbol", Arrays.asList(TerminalSymbol.PLUS), testSequence.getProduction());
		
		//Tests multiple SymbolSequences along with NonTerminalSequences
		testSequence = SymbolSequence.build(NonTerminalSymbol.EXPRESSION, TerminalSymbol.PLUS, NonTerminalSymbol.EXPRESSION_TAIL);
		testList = Arrays.asList(NonTerminalSymbol.EXPRESSION, TerminalSymbol.PLUS, NonTerminalSymbol.EXPRESSION_TAIL);
		assertEquals("Testing multiple Symbols", testList, testSequence.getProduction());
	}
	
	/**
	 * Tests the toString override in SymbolSequence that calls toString to its production
	 */
	@Test
	public void testToString() {
		//Test a sequence with a single production value
		SymbolSequence testSequence = SymbolSequence.build(TerminalSymbol.PLUS);
		assertEquals("Testing with single production value", "[PLUS]", testSequence.toString());
		
		//Test a sequence with multiple production values
		testSequence = SymbolSequence.build(TerminalSymbol.VARIABLE, TerminalSymbol.PLUS, TerminalSymbol.VARIABLE);
		assertEquals("Testing with multiple values in production", "[VARIABLE, PLUS, VARIABLE]", testSequence.toString());
		
		//Test a sequence with multiple NonTerminal values in production
		testSequence = SymbolSequence.build(NonTerminalSymbol.FACTOR, NonTerminalSymbol.EXPRESSION, NonTerminalSymbol.EXPRESSION_TAIL);
		assertEquals("Testing with multiple NonTerminalSymbols in production", "[FACTOR, EXPRESSION, EXPRESSION_TAIL]", testSequence.toString());
		
	}
	
	/**
	 * Tests the match method in a given SymbolSequence
	 */
	@Test
	public void testMatch( ) {
		//Test a sequence with a single production value and no remainder
		SymbolSequence testSequence = SymbolSequence.build(TerminalSymbol.VARIABLE);
		List<Token> testList = Arrays.asList(a);
		ParseState testParse = ParseState.build(InternalNode.build(Arrays.asList(LeafNode.build(a))), new ArrayList<Token>());
		/* Test Success */
		 assertEquals("Testing match success for one production value", testParse.getSuccess(), testSequence.match(testList).getSuccess());
		/* Test Node */
		assertEquals("Testing node returned from match for one production value", testParse.getNode(), testSequence.match(testList).getNode());
		/* Test Remainder value */
		assertEquals("Testing remainder returned from match for multiple production values", testParse.getRemainder(), testSequence.match(testList).getRemainder());
		 
		//Test a sequence with multiple Symbol values in production with a remainder
		testSequence = SymbolSequence.build(TerminalSymbol.VARIABLE, TerminalSymbol.PLUS, TerminalSymbol.VARIABLE);
		testList = Arrays.asList(a, plus, b, Variable.build("c"));
		List<Node> nodeList = Arrays.asList(LeafNode.build(a), LeafNode.build(plus), LeafNode.build(b));
		testParse = ParseState.build(InternalNode.build(nodeList), Arrays.asList(Variable.build("c")));
		/* Test Success */
		 assertEquals("Testing match success for one production value", testParse.getSuccess(), testSequence.match(testList).getSuccess());
		/* Test Node */
		assertEquals("Testing node returned from match for one production value", testParse.getNode(), testSequence.match(testList).getNode());
		/* Test Remainder value */
		assertEquals("Testing remainder returned from match for multiple production values", testParse.getRemainder(), testSequence.match(testList).getRemainder());

		//Test a failure case for a matched sequence
		testSequence = SymbolSequence.build(TerminalSymbol.VARIABLE, TerminalSymbol.PLUS, TerminalSymbol.VARIABLE);
		testList = Arrays.asList(plus, a, b, Variable.build("c")); //Switched Sequence Order
		/* Test Failure*/
		 assertEquals("Testing if FAILURE ParseState is returned for failed case", ParseState.FAILURE, testSequence.match(testList));
	}

}
