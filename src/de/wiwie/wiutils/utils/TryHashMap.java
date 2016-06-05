/**
 * 
 */
package de.wiwie.wiutils.utils;

import java.util.HashMap;

/**
 * @author Christian Wiwie
 * @param <K>
 * @param <V>
 * 
 */
public class TryHashMap<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5268585235021191215L;
	protected V initValue;

	/**
	 * @param initValue
	 */
	public TryHashMap(V initValue) {
		super();
		this.initValue = initValue;
	}

	@Override
	public V get(Object key) {
		V value = super.get(key);
		// if the key is not contained, insert and return the default value
		if (value == null) {
			this.put((K) key, initValue);
			value = this.initValue;
		}
		return value;
	}
}
