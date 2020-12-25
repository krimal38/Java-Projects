//This class represents a key-value pair. It is completed for you,
//but you need to add JavaDocs.

//--------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
//--------------------------------------------------------
/**
 * @author Professors
 *
 * @param <K>
 *            - Takes in key value.
 * @param <V>
 *            - Takes key value pair.
 */
public class KeyValuePair<K, V> {
	/**
	 * Takes in key value.
	 */
	private K key;
	/**
	 * Takes key value pair.
	 */
	private V value;

	/**
	 * Constructor. Takes in key and value.
	 * 
	 * @param key
	 *            - Takes in key value.
	 * @param value
	 *            - Takes key value pair.
	 */
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key and returns it.
	 * 
	 * @return the key value.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * gets the key value pair.
	 * 
	 * @return the value pair.
	 */
	public V getValue() {
		return value;
	}

	/**
	 * This is the equals method which checks if object is equal or not.
	 * 
	 * @param -
	 *            Takes in object parameter.
	 * @return - Return true if it is equals else false.
	 */
	public boolean equals(Object o) {
		if (o instanceof KeyValuePair) {
			return key.equals(((KeyValuePair) o).key);
		}
		return false;
	}

	/**
	 * This method returns the hashcode.
	 * 
	 * @return - returns the integer hashCode.
	 */
	public int hashCode() {
		return key.hashCode();
	}

	/**
	 * The toString method.
	 * 
	 * @return - Returns the string.
	 */
	public String toString() {
		return "(" + key + "," + value + ")";
	}
}