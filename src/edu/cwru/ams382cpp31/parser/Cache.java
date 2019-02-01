/**
 * Contained in package for EECS 293 project
 */
package edu.cwru.ams382cpp31.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Kyle Pham
 * @author Arik Stewart
 * A final package-private class used to represent cache, which stores previous data objects' properties
 */
final class Cache<T, V> {

	/**
	 * A Map used to store cache from a list and prevent duplicating elements
	 */
	private Map<T, V> cache;
	
	/**
	 * A constructor used to build a HashMap to store the non-duplicate elements
	 */
	Cache() {
		this.cache = new HashMap<T, V>();
	}
	
	/**
	 * Gets an element given the key and constructor type, avoiding duplicates with the same keys
	 * @param key			the key of the element to be put into the cache if necessary
	 * @param constructor	a constructor used to build a new element in the cache if necessary 
	 * @return				the element given by the key of the element and the constructor method
	 * 						that can be used to construct the element
	 */
	V get(T key, Function<? super T, ? extends V> constructor) {
		Objects.requireNonNull(key, "The key should not be null!");
		Objects.requireNonNull(constructor, "The constructor function should not be null!");
		
		if (!cache.containsKey(key)) {
			cache.put(key, constructor.apply(key));
		}
		else {
			// Do nothing because the key is already in the cache
		}

		return cache.get(key);
		
	}
	
}
