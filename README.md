# EECS293 - Programming Pojects 2-5
Software Craftsmanship Fun With Kyle and Arik

## Authors
* **Arik Stewart** || **Kyle Pham**

## Prerequisites (Software Needed)
* Eclipse Java, 2018

# Class Hierarchy and Descriptions
The project in this package will be used to translate a sequence representation of a numerical expression into a compact tree representation

## Cache
Used for constructors to avoid creating multiple immutable objects with the same non-null parameter
package-private `final class Cache<T,V>`
* **cache** - `private Map<T, V> cache` that is initially empty and holds a mapped cache value
* **get()** - `V get(T key, Function<? super T, ? extends V> constructor>` that returns the cached object corresponding to `t` if present in the cache,
		and otherwise creates a new object with the provided constructor. Throws a `NullPointerException` with an appropriate error message
		if `t` or `constructor` are null

## Tokens
Represents any symbol represented in the list
### Token
An interface that represents the elements in the list of expressions such as 'a' or '+'
* **TerminalSymbol** - An enum with values `VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE`
* **getType()** - Returns a `TerminalSymbol` value of the current token
* **matches(TerminalSymbol type)** Returns a boolean value of whether the argument is equal to current TerminalSymbol type
* **AbstractToken** - public abstract class that implements a `public final matches(Terminal Type)` function

### Variables
A class that extends AbstractToken and represents a token such as 'a', 'b', 'c' on the list
Variable has type `VARIABLE` from `TerminalSymbol`
* **representation** - `private final String` variable which represents the variable name and is non-null. Also has a getter
* **constructor** - private, and sets the string representation
* **build(String representation)** - returns a variable with the given `representation` or throws a `NullPointerException` with an appropriate error message if the representation is null
* **cache** - a `private static Cache<String, Variable>` variable used to avoid multiple redundant variables with the same string representation. Used in the 'build' function
* **toString()** - Override function that returns the variable's `representation`

### Connectors
Other symbols that can appear in the list such as operators including +, -, *, /, (, ).
Must be a `public final class Connector` that extends `AbstractToken`
* **constructor** - private, sets the type of the Connector
* **getType()** - public `TerminalSymbol` that returns the type
* **build** - `public static final Connector` that returns a connector of the given type, throws a `NullPointerException` if the type is null,
		throws an `IllegalArgumentException` if the type is anything but one of `PLUS, MINUS, TIMES, DIVIDE, OPEN` or `CLOSE` with 
		an appropriate error message for all exceptions
		Must use a `Cache` to avoid creating multiple connectors of the same type

## Nodes
Objects that appear in the tree representation. Can be either leaves (for tokens) or internal nodes
### Node
Public interface representation of a node within the tree. 
* **toList** - type `List<Token>` which is supposed to return the list representation of the subtree rooted at the given node

### LeafNode
`public final class` that implements `Node`
* **token** - 	`private final` variable that also has a getter method
* **constructor** - private, sets the `token` value
* **build** - returns a new leaf with the given token or throws a `NullPointerException` if the argument is null
		Note: Different leaves can share the same `Token`. Tokens are cached but LeafNodes are not
* **toString()** - Override method that returns its `Token` to string representation
* **toList()** - `public final` returns a list containing as a single element its `Token` 

### InternalNode
`public final class` that implements `Node` 
* **children** - `private final List<Node>` that represents node children, also contains a getter that returns a copy of the private children
* **constructor** - private, sets the children to an unmodifiable copy of its own argument
* **build** - returns a new a new `InternalNode` with the given children, throws a `NullPointerException` if the argument is null
	Note: Internal Nodes are not cached
* **toList()** - `public final` returns the concatenation of the children's lists, or a copy of the precomputed lists if already created (since the list will not change)
* **toString()** - override that returns the children in a result formatted by square parenthesis separated by comma, or returns a copy of computed in the past (since the string will not change)
		Ex: [a,[+,[b,[/,c]]]]


