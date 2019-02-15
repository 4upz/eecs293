# EECS293 - Programming Pojects 2-5
Software Craftsmanship Fun With Kyle and Arik

## Authors
* **Arik Stewart** || **Kyle Pham**

## Prerequisites (Software Needed)
* Eclipse Java, 2018

# Class Hierarchy and Descriptions - HW2 Implementations
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
		
************************************************************************************************************************************
# Homework 3 Implementations

## ParseState
`final class` with a private constructor. Represents the current state of the tree-parsing process
* success - `private final boolean` denotes whether the parsing process was successful, with a getter
* node - `private final Node` that is the result of parsing an initial prefix of the list, with a getter, 
* remainder - `private final List<Token>` the part of the initial token list that was left over after parsing the `Node`. Getter returns a copy of the private variable
* hasNoRemainder - `private final boolean` returns whether there is no remainder
* FAILURE - `final static ParseState` with `success` equal to false, as well as a `node` and `remainder` equal to null
* build - `public static final` returns a non-failure parse state and specifically it returns a new `ParseState` with the given `node` and a copy of the given remainder list. Throws a `NullPointerException` with message if argument is null

## Symbol
`interface Symbol` that is an input parser that builds a tree node
* parse(List<Token> input) - `private ParseState` that parses the `input` into a node, possibly leaving a `remainder`. `success` will be true if the parsing process was successful and false otherwise

## TerminalSymbol
Now implements `Symbol` and parses a token list by serving as a builder of leaves from tokens.

## SymbolSequence
`final class`  that serves as an individual symbol and a builder of one tree node or the children of an internal node
* production - `private final List<Symbol>` 
* constructor - `private` that sets the production value
* build(List<Symbol> production) - returns a new `SymbolSequence` with the given production, or throws `NullPointerException` with an appropriate error message if the argument is null
* build(Symbol... symbols) - A second build method that takes a variable number of arguments `static final SymbolSequence build(Symbol... symbols)` 
**SymbolSequences Should not be cached**
* EPSILON - `static final SymbolSequence` with an empty production
* toString - delegates the method to its production
* ParseState match (List<Token> input) - returns a successful `ParseState` if all the symbols in the production can be matched with the input, and FAILURE otherwise. Throws a `NullPointerException` with an appropriate error message if the input is null

## NonTerminalSymbol
An `enum` that implements `Symbol` and serves as a builder of internal nodes during parsing
* EXPRESSION : TERM EXPRESSION-TAIL
* EXPRESSION_TAIL : + TERM EXPRESSION_TAIL, - TERM EXPRESSION-TAIL, EPSILON
* TERM : UNARY TERM_TAIL
* TERM_TAIL: * UNARY TERM-TAIL, / UNARY TERM_TAIL, EPSILON
* UNARY : - FACTOR, FACTOR
* FACTOR : (EXPRESSION), VARIABLE
* parseInput(List<Token< input) - `static final optional<Node>` attempts to parse the input with an `EXPRESSION` and returns the root node if the parsing process is successful and has no remainder, and an empty `Optional` otherwise. Throws a `NullPointerException` with appropriate message if the input is null

Make sure to run the parseInput on the example [a, +, b, /, c]

************************************************************************************************************************************
# Homework 4 Implementations

## Node
* getChildren() - Returns the children of the node as a list
* isFruitful() - Returns whether the node bears at least one child

## LeafNode
* getChildren() - Returns null since a LeafNode doesn't have children
* isFruitful() - Returns true since it will always have at least one

## InternalNode
* getChildren() - Returns immutable list of children
* isFruitful() - Returns a boolean value for if the InternalNode has at least one child

### InternalNode.Builder
Nested builder class for InternalNode

## NonTerminaSymbol
Implemented new circularity map `productions` of format Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>>
* parse - adjusted to account for new productions map





