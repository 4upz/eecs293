/**
 * 
 */
package edu.cwru.ams382cpp31.parser.test;

import edu.cwru.ams382cpp31.parser.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import java.util.*;

import org.junit.Test;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * A class using JUnit4 to test the methods in the class InternalNode
 */
public class InternalNodeTest {

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
	
	//LeafNode instance variable to be modified and used for each test
	LeafNode leafNode;
	
	//InternalNode instance variables to be modified and used for each test
	InternalNode internalNodeOne;
	InternalNode internalNodeTwo;
	InternalNode internalNodeThree;

	/**
	 * Test method for {@link edu.cwru.ams382cpp31.parser.InternalNode#toList()}.
	 * Tests the toList method of InternalNode
	 * Implicitly tests the build, getChildren, getListConcat, setListConcat methods using the cases
	 */
	@Test
	public final void testToList() {
		//Test with one single-node child
		leafNode = LeafNode.build(add);
		internalNodeOne = InternalNode.build(Arrays.asList(leafNode));
		assertEquals("Test with only one Node Children", leafNode.toList(), internalNodeOne.toList());
		
		//Test with more than one single-node children
		List<Token> testList = new ArrayList<Token>();
		testList.add(a);
		testList.add(add);
		testList.add(b);
		internalNodeOne = InternalNode.build(Arrays.asList(LeafNode.build(a), LeafNode.build(add), LeafNode.build(b)));
		assertEquals("Test with more than one Node Children", testList, internalNodeOne.toList());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node Test of list copy", testList, internalNodeOne.toList());
		
		//Test with one single-node child and one InternalNode child
		leafNode = LeafNode.build(divide);
		internalNodeTwo = InternalNode.build(Arrays.asList(internalNodeOne, leafNode));
		testList.add(divide);
		assertEquals("Test with one node and InternalNode as children", testList, internalNodeTwo.toList());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node and InternalNode Test of list copy", testList, internalNodeTwo.toList());
		
		//Test with more than one InternalNode children
		internalNodeTwo = InternalNode.build(Arrays.asList(leafNode, LeafNode.build(c)));
		internalNodeThree = InternalNode.build(Arrays.asList(internalNodeOne, internalNodeTwo)); 
		testList.add(c);
		assertEquals("Test with two InternalNodes as children", testList, internalNodeThree.toList());
		//Once more to make sure the method saves the list after initial call
		assertEquals("Two-InternalNode Test of list copy", testList, internalNodeThree.toList());
		
		//Test a null value for build
		try {
			InternalNode.build(null);
		} catch (NullPointerException e) {
		     assertEquals("Null Children Test", "Children list cannot be null", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.cwru.ams382cpp31.parser.InternalNode#toString()}.
	 * Tests the toString method of InternalNode
	 * Implicitly tests the build, getChildren, getChildrenString, setChildrenString methods using the cases
	 */
	@Test
	public final void testToString() {
		//Test with one single-node child
		leafNode = LeafNode.build(a);
		internalNodeOne = InternalNode.build(Arrays.asList(leafNode));
		assertEquals("Test with only one Node Children", "[a]", internalNodeOne.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node Test of String copy", "[a]", internalNodeOne.toString());

		internalNodeOne = InternalNode.build(Arrays.asList(LeafNode.build(a), LeafNode.build(add), LeafNode.build(b)));
		assertEquals("Test with more than one Node Children", "[a,+,b]", internalNodeOne.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("More than one One-Node Test of String copy", "[a,+,b]", internalNodeOne.toString());
				
		//Test with one single-node child and one InternalNode child
		leafNode = LeafNode.build(divide);
		internalNodeTwo = InternalNode.build(Arrays.asList(internalNodeOne, leafNode));
		assertEquals("Test with one node and InternalNode as children", "[[a,+,b],/]", internalNodeTwo.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node and InternalNode Test of String copy", "[[a,+,b],/]", internalNodeTwo.toString());
				
		//Test with more than one InternalNode children
		internalNodeTwo = InternalNode.build(Arrays.asList(leafNode, LeafNode.build(c)));
		internalNodeThree = InternalNode.build(Arrays.asList(internalNodeOne, internalNodeTwo)); 
		assertEquals("Test with two InternalNodes as children", "[[a,+,b],[/,c]]", internalNodeThree.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("Two-Internal Node Test of String copy", "[[a,+,b],[/,c]]", internalNodeThree.toString());
		
		//Special Test with list tree set up as example given in instructions
		internalNodeOne = InternalNode.build(Arrays.asList(LeafNode.build(divide), LeafNode.build(c)));
		internalNodeTwo = InternalNode.build(Arrays.asList(LeafNode.build(b), internalNodeOne));
		internalNodeThree = InternalNode.build(Arrays.asList(LeafNode.build(add), internalNodeTwo));
		InternalNode internalNodeFour = InternalNode.build((Arrays.asList(LeafNode.build(a), internalNodeThree)));
		assertEquals("Test Example List Tree Setup", "[a,[+,[b,[/,c]]]]", internalNodeFour.toString());
		//Once more to test the stored copy of the String
		assertEquals("Test Example List Tree Setup of String copy", "[a,[+,[b,[/,c]]]]", internalNodeFour.toString());

	}
	
	/**
	 * Tests the method equals in InternalNode
	 */
	@Test
	public final void testEquals() {
		// Test the case when the two internal nodes are equal
		leafNode = LeafNode.build(a);
		internalNodeOne = InternalNode.build(Arrays.asList(leafNode));
		internalNodeTwo = InternalNode.build(Arrays.asList(leafNode));
		assertEquals(internalNodeOne, internalNodeTwo);
		
		// Test compare to null
		assertNotEquals(internalNodeOne, null);
		
		// Test compare to a non-InternalNode object
		assertNotEquals(internalNodeOne, "aaa");
		
		// Test compare to another internal node with different children
		assertNotEquals(internalNodeOne, InternalNode.build(Arrays.asList(LeafNode.build(b))));
	}
	
	/**
	 * Tests the getChildren and isFruitful method of InternalNode
	 */
	@Test
	public final void testChildren() {
		//Tests with empty child
		InternalNode.Builder builder = new InternalNode.Builder();
		InternalNode internalNodeOne = builder.build();
		assertEquals(Arrays.asList(), internalNodeOne.toList());
		assertFalse(internalNodeOne.isFruitful());
		
		//Test with one child that is also internalNode to test the simplify method of InternalNode.Builder
		builder.addChild(InternalNode.build(Arrays.asList(LeafNode.build(a), LeafNode.build(add), LeafNode.build(b))));
		internalNodeOne = builder.build();
		assertEquals(Arrays.asList(a, add, b), internalNodeOne.toList());
		assertTrue(internalNodeOne.isFruitful());
		assertEquals(new LinkedList<Node>(Arrays.asList(LeafNode.build(a), LeafNode.build(add), LeafNode.build(b))), internalNodeOne.getChildren());
		
		//Test with multiple children that includes one InternalNode and one LeafNode
		builder = new InternalNode.Builder();
		builder.addChild(internalNodeOne);
		builder.addChild(LeafNode.build(divide));
		builder.addChild(LeafNode.build(c));
		internalNodeOne = builder.build();
		assertEquals(Arrays.asList(a, add, b, divide, c), internalNodeOne.toList());
		assertTrue(internalNodeOne.isFruitful());
		assertEquals("[[a,+,b], /, c]",
				internalNodeOne.getChildren().toString());
		
	}

}
