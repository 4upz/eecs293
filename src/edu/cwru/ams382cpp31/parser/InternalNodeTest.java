/**
 * 
 */
package edu.cwru.ams382cpp31.parser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * Class object to represent an internal node of tokens from the given expression
 */
class InternalNodeTest {

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
	final void testToList() {
		//Test with one single-node child
		leafNode = LeafNode.build(add);
		internalNodeOne = InternalNode.build(Arrays.asList(leafNode));
		assertEquals("Test with only one Node Children", leafNode.toList(), internalNodeOne.toList());
		
		//Test with more than one single-node children
		List<Token> testList = new ArrayList<Token>();
		testList.add(a);
		testList.add(minus);
		testList.add(b);
		internalNodeOne = InternalNode.build(Arrays.asList(LeafNode.build(a), LeafNode.build(minus), LeafNode.build(b)));
		assertEquals("Test with more than one Node Children", testList, internalNodeOne.toList());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node Test of list copy", testList, internalNodeOne.toList());
		
		//Test with one single-node child and one InternalNode child
		leafNode = LeafNode.build(add);
		internalNodeTwo = InternalNode.build(Arrays.asList(internalNodeOne, leafNode));
		testList.add(add);
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
	}

	/**
	 * Test method for {@link edu.cwru.ams382cpp31.parser.InternalNode#toString()}.
	 * Tests the toString method of InternalNode
	 * Implicitly tests the build, getChildren, getChildrenString, setChildrenString methods using the cases
	 */
	@Test
	final void testToString() {
		//Test with one single-node child
		leafNode = LeafNode.build(add);
		internalNodeOne = InternalNode.build(Arrays.asList(leafNode));
		assertEquals("Test with only one Node Children", "+", internalNodeOne.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node Test of String copy", "+", internalNodeOne.toString());

		internalNodeOne = InternalNode.build(Arrays.asList(LeafNode.build(a), LeafNode.build(minus), LeafNode.build(b)));
		assertEquals("Test with more than one Node Children", "a-b", internalNodeOne.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("More than one One-Node Test of String copy", "a-b", internalNodeOne.toString());
				
		//Test with one single-node child and one InternalNode child
		leafNode = LeafNode.build(add);
		internalNodeTwo = InternalNode.build(Arrays.asList(internalNodeOne, leafNode));
		assertEquals("Test with one node and InternalNode as children", "a-b+", internalNodeTwo.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("One-Node and InternalNode Test of String copy", "a-b+", internalNodeTwo.toString());
				
		//Test with more than one InternalNode children
		internalNodeTwo = InternalNode.build(Arrays.asList(leafNode, LeafNode.build(c)));
		internalNodeThree = InternalNode.build(Arrays.asList(internalNodeOne, internalNodeTwo)); 
		assertEquals("Test with two InternalNodes as children", "a-b+c", internalNodeThree.toString());
		//Once more to make sure the method saves the list after initial call
		assertEquals("Two-InternalNode Test of String copy", "a-b+c", internalNodeThree.toString());
	}

}