/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.*;
import java.util.Objects;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * Class object to represent an internal node of tokens from the given expression
 */
public class InternalNode implements Node {

	/**
	 * A list represents the node children
	 */
	private final List<Node> children;
	
	/**
	 * A stored list from the concatenation of all of the InternalNodes's children
	 * Only instantiated if toList() has been called before
	 */
	protected List<Token> listConcat;
	
	/**
	 * A stored string from the tokens in the node children
	 * Only instantiated if toString() has been called before
	 */
	protected String childrenString;
	
	/**
	 * Constructor for the class that initializes children
	 * @param children a given list of Nodes to be set as an InternalNode
	 */
	private InternalNode(List<Node> children) {
		this.children = children;
		this.listConcat = null;
		this.childrenString = null;
	}
	
	/**
	 * Retrieves the list of node children
	 * @return the value stored in children
	 */
	private final List<Node> getChildren() {
		return this.children;
	}
	
	/**
	 * Retrieves the stored concatenation of the node children
	 * @return a list of Tokens from the node children stored in this InternalNode
	 */
	private final List<Token> getListConcat() {
		return this.listConcat;
	}
	
	/**
	 * Retrieves the stored String of the Token values stored in the node children
	 * @return
	 */
	private final String getChildrenString() {
		return this.childrenString;
	}
	
	/**
	 * Sets the value of childrenString to a new String value
	 * @param newString new String to be stored
	 */
	private final void setChildrenString(String newString) {
		this.childrenString = newString;
	}
	
	/**
	 * Sets the value of listConcat once a concatenation has been created in toList()
	 * @param newConcat the new Token List that is to be stored
	 */
	private final void setListConcat(List<Token> newConcat) {
		this.listConcat = newConcat;
	}
	
	/**
	 * Creates and instantiates a new InternalNode
	 * @param children a new list of children node to be created in the InternalNode
	 * @return the newly instantiated internalNode made from the given list
	 */
	public static InternalNode build(List<Node> children) {
		Objects.requireNonNull(children, "Children list cannot be null");
		InternalNode newNode = new InternalNode(children);
		return newNode;
	}
	
	/**
	 * Returns the concatenation of the children's lists or a pre-computed copy if one was already made
	 * @return new List<Token> containing all of the tokens in the internal node's children
	 */
	@Override
	public final List<Token> toList() {
		//If a concatenation hasn't already been made...
		if (this.getListConcat() == null) {	
			List<Token> newList = new ArrayList<Token>();
			//Add every token in each Node children to the new list
			//List.addAll(node.toList())
			for (Node node : this.getChildren()) {
				for(Token token : node.toList()) {
					newList.add(token);
				}
			}
			this.setListConcat(newList);
		}
		return this.getListConcat();
	}
	
	/**
	 * Returns a formatted String result of the stored children
	 * @return String containing squared parentheses from children nodes
	 */
	@Override
	public final String toString() {
		//If a String output hasn't already been made...
		if (this.getChildrenString() == null) {	
			StringBuilder sb = new StringBuilder();
			//Add the string output of each token to the new resulting string with brackets between each node
			List<Node> children = this.getChildren();
			for (Node node : children) {
				sb.append("["); //Move before for-loop
				sb.append(node.toString());
				if(!(node instanceof LeafNode)) {
					if(!((LeafNode)children.get(children.size() - 1)).getToken().matches(((LeafNode)node).getToken().getType())) {
						sb.append(","); //Use sb to remove last char (',')
					}
				}
			}
			//Add a closing bracket for every group of opening brackets
			for (int i = 0; i < this.getChildren().size(); i++) {
				sb.append("]");
			}
			this.setChildrenString(sb.toString());
		}
		System.out.println(this.getChildrenString());
		return this.getChildrenString(); 
	}

}
