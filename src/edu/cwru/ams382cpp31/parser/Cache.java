/**
 * 
 */
package edu.cwru.ams382cpp31.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Kyle Pham
 *
 */
final class Cache<T, V> {

	/**
	 * 
	 */
	private Map<T, V> cache;
	
	/**
	 * 
	 */
	Cache(){
		this.cache = new HashMap<T, V>();
	}
	
	/**
	 * 
	 * @param key
	 * @param constructor
	 * @return
	 */
	V get(T key, Function<? super T, ? extends V> constructor) {
		Objects.requireNonNull(key, "The key should not be null!");
		Objects.requireNonNull(constructor, "The constructor function should not be null!");
		
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		else {
			return cache.put(key, constructor.apply(key));
		}
		
	}
	
}
