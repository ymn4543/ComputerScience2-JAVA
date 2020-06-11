/**
 * Description: DB implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */

import java.util.Collection;

public interface DB<K,V> {
    /**
     * Adds a value entry to the database in constant time.
     * The database will determine the key based on the value type.
     */
    V addValue(V value);

    /**
     * Get all the values in the database in linearithmic,
     * O(nlogn), time, where the elements are naturally ordered.
     * @return
     */
    Collection<V>getAllValues();

    /**
     * Get the value for an associated key.
     * @param key key
     * @return value
     */
    V getValue(K key);

    /**
     * Indicates whether a key is in the database or not.
     * @param key key being checked
     * @return boolean
     */
    boolean hasKey(K key);

}
