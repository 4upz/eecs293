/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.*;

/**
 * @author Arik Stewart
 * @author Kyle Pham
 * Class object to represent an internal node of tokens from the given expression
 */
public class InternalNode implements Node {

	/** 
	 * @author Arik Stewart
	 * @author Kyle Pham
	 * Nested class used to build an InternalNode
	 */
	public static class Builder {
		
		/**
		 * List of children within the InternalNode, initialized as empty list
		 */
		private List<Node> children = Collections.emptyList();
		
		/**
		 * Adds a new node to the list of children being built
		 * @param node A new node to add to the end of list
		 * @return true after node is added
		 */
		public boolean addChild(Node node) {
			return this.children.add(node);
		}
		
		/**
		 * Simplifies the InternalNode by clearing unneeded children
		 * @return new Builder instance containing simplified InternalNode
		 */
		public Builder simplify() {
			for (Node child : children) {
				//If the child is not fruitful, remove from the children list
				if (!child.isFruitful()) {
					children.remove(child);
				}
				splitSingleInternalNode();
			}
			return this;
		}
		
		/**
		 * Builds a new InternalNode instance with the simplified children list
		 * @return the newly built instance of InternalNode
		 */
		public InternalNode build() {
			return InternalNode.build(simplify().children);
		}
		
		/**
		 * Checks to see if the children list contains a single InternalNode and splits its children if it does
		 */
		private void splitSingleInternalNode() {
			if(children.size() == 1 && children.get(0) instanceof InternalNode) {
				children = children.get(0).getChildren();
			}
		}
	}
	
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
	public final List<Node> getChildren() {
		return new LinkedList<>(this.children);
	}
	
	/**
	 * Retrieves the stored concatenation of the node children
	 * @return a list of Tokens from the node children stored in this InternalNode
	 */
	private final List<Token> getListConcat() {
		return this.listConcat == null ? null : new LinkedList<>(this.listConcat);
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
		return new InternalNode(children);
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
			this.getChildren().forEach(child -> newList.addAll(child.toList()));
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
			StringBuilder sb = new StringBuilder();		// StringBuilder for String to be formatted
			List<Node> children = this.getChildren();
			sb.append("[");
			for (Node node : children) {
				sb.append(node.toString() + ",");
			}
			sb.append("]");
			sb.deleteCharAt(sb.lastIndexOf(",")); //Removes the last comma from the list
			this.setChildrenString(sb.toString());
		}
		return this.getChildrenString(); 
	}
	
	/**
	 * Checks if the internal node is equal to a given object
	 * @param object	an object to be compared to the internal node
	 * @return			true if the object is an internal node with the same list of children,
	 * 					and false otherwise
	 */
	@Override
	public boolean equals(Object object) {
		return object != null 
				&& object instanceof InternalNode 
				&& this.getChildren().equals(((InternalNode) object).getChildren());
	}

	/**
	 * Inherited from Node and determines whether the InternalNode has children
	 * @return boolean value based on if stored children list is empty
	 */
	@Override
	public boolean isFruitful() {
		return !this.getChildren().isEmpty();
	}
}
