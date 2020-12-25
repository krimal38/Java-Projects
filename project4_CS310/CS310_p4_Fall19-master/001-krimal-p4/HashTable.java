
//Hash table with separate chaining. Each key and value gets
//placed together as a single entry in the table. The hash code
//of a key is used to place the pair in the table and to look
//for it again. Note that KeyValuePair is a structure for
//ArrayOfListsOfPairs, this part of the code needs to be able to
//deal with keys and values separately.

import java.util.Collection;

/**
 * This is the hashTable class which uses separate chaining. Each key and value
 * gets placed together as a single entry in the table. The hash code of a key
 * is used to place the pair in the table and to look for it again.
 * 
 * @author kshitiz Rimal
 *
 * @param <K>
 *            - Takes key value.
 * @param <V>
 *            - Takes key value pair.
 */
public class HashTable<K, V> {
	// This is the minimum number of slots in the hash table
	// Do not change this.
	/**
	 * This is the minimum number of slots in the hash table.
	 */
	private static final int MIN_SLOTS = 2;

	/**
	 * This tracks the size.
	 */
	private int size = 0;
	// You must use this as your internal storage, you may not change
	// the type, name, privacy, or anything else about this variable.
	/**
	 * This is the internal storage.
	 */
	protected ArrayOfListsOfPairs<K, V> storage;

	// If the number of slots requested is less than the minimum
	// number of slots, use the minimum instead.
	/**
	 * Constructor. If the number of slots requested is less than the minimum,
	 * number of slots, use the minimum instead.
	 * 
	 * @param numSlots
	 *            - Takes in the integer number of slots.
	 */
	public HashTable(int numSlots) {
		if (numSlots < MIN_SLOTS) {
			numSlots = MIN_SLOTS;
		}
		storage = new ArrayOfListsOfPairs<>(numSlots);

	}

	// The number of key-value entries in the table.
	// O(1)
	/**
	 * The number of key- value entries in the table.
	 * 
	 * @return - the integer value of size.
	 */
	public int size() {
		// return storage.getAllPairs().size();
		return size;
	}

	// Returns the number of slots in the table.
	// O(1)
	/**
	 * This returns the number of slots in the table.
	 * 
	 * @return the number of lists.
	 */
	public int getNumSlots() {
		return storage.getNumLists();
	}

	// Returns the load on the table.
	// O(1)
	/**
	 * It checks the load.
	 * 
	 * @return Returns the load on the table.
	 */
	public double getLoad() {
		return size() / storage.getNumLists();
	}

	// If the key is not in the table, add the key-value entry to the table
	// and return true. If unable to add the entry, return false. Keys and
	// values are _not_ allowed to be null in this table, so return false if
	// either of those are provided instead of trying to add them.

	// If the load goes above 3 after adding an entry, this method should
	// rehash to three times the number of slots.

	// Must run in worst case O(n) and average case O(n/m) where n is the
	// number of key-value pairs in the table and m is the number of "slots"
	// in the table.
	/**
	 * If the key is not in the table, add the key-value entry to the table and
	 * return true. If unable to add the entry, return false. Keys and values are
	 * _not_ allowed to be null in this table, so return false if either of those
	 * are provided instead of trying to add them.
	 * 
	 * @param key
	 *            - Takes in key value.
	 * @param value
	 *            - Returns key value pair.
	 * @return true if added else false.
	 */
	public boolean add(K key, V value) {
		if (key == null || value == null) {
			return false;
		}
		// KeyValuePair<K, V> pair = new KeyValuePair<K, V>(key, value);
		int hashcode = Math.abs(key.hashCode() % getNumSlots());

		if (storage.HelperMethodForadd(key, value, hashcode)) {
			size++;
		}

		if (getLoad() > 3) {
			rehash(3 * getNumSlots());
		}
		return true;
	}

	// Rehashes the table to the given new size (new number of
	// slots). If the new size is less than the minimum number
	// of slots, use the minimum instead.

	// Must run in worst case O(n+m) where n is the number of
	// key-value pairs in the table and m is the number of
	// "slots" in the table.
	/**
	 * Rehashes the table to the given new size (new number of slots). If the new
	 * size is less than the minimum number of slots, use the minimum instead.
	 * 
	 * @param newSize
	 *            - Takes in the new size of the list.
	 */
	public void rehash(int newSize) {

		int oldLength = storage.getNumLists();
		int newTableSize = 0;

		if (newSize < getNumSlots())
			newTableSize = getNumSlots();

		else {
			newTableSize = newSize;
		}
		ArrayOfListsOfPairs<K, V> list = new ArrayOfListsOfPairs<K, V>(newTableSize);

		/*
		 * storage.rehasingHelpermethod(list, newTableSize); storage = list;
		 */

		Collection<KeyValuePair<K, V>> pairs = storage.getAllPairs();

		// since the size is constantly changing we need to create the size variable.
		int newsize = pairs.size();

		for (int i = 0; i < newsize; i++) {
			KeyValuePair<K, V> n = pairs.iterator().next();
			pairs.remove(n);
			K key = n.getKey();
			V value = n.getValue();
			list.add(key, value);

		}
		storage = list;
	}

	// If the key requested is in the table, change the associated value
	// to the provided value and return true. Otherwise return false.

	// Must run in worst case O(n) and average case O(n/m) where n is the
	// number of key-value pairs in the table and m is the number of "slots"
	// in the table.
	/**
	 * If the key requested is in the table, change the associated value to the
	 * provided value and return true. Otherwise return false.
	 * 
	 * @param key
	 *            - Takes in key value.
	 * @param value
	 *            - returns key value pair.
	 * @return true if it is rehashed else false.
	 */
	public boolean replace(K key, V value) {

		if (get(key) != null) {
			int bucketIndex = key.hashCode() % getNumSlots();
			ArrayOfListsOfPairs.Node<K, V> bucket = storage.get(bucketIndex, key);

			if (bucket != null && bucket.pair.getKey().equals(key)) {
				// replace old value with new value
				bucket.pair = new KeyValuePair<K, V>(key, value);

				return true;
			}

		}
		return false;
	}

	// If the key requested is in the table, remove the key-value entry
	// and return true. Otherwise return false.

	// Must run in worst case O(n) and average case O(n/m) where n is the
	// number of key-value pairs in the table and m is the number of "slots"
	// in the table.
	/**
	 * If the key requested is in the table, remove the key-value entry and return
	 * true. Otherwise return false.
	 * 
	 * @param key
	 *            - Takes in key value.
	 * @return true if it is removed else false.
	 */
	public boolean remove(K key) {
		int indexkey = 0;

		indexkey = key.hashCode();
		indexkey = Math.abs(indexkey) % getNumSlots();

		if (contains(key) == false)
			return false;

		else {
			storage.removehelpermethod(indexkey, key);
			size--;
			return true;
		}
	}

	// If the key requested is in the table, return true. Otherwise return false.

	// Must run in worst case O(n) and average case O(n/m) where n is the
	// number of key-value pairs in the table and m is the number of "slots"
	// in the table.
	/**
	 * If the key requested is in the table, return true. Otherwise return false.
	 * 
	 * @param key
	 *            - Takes in key value.
	 * @return If the key requested is in the table, return true. Otherwise return
	 *         false.
	 */
	public boolean contains(K key) {
		if (get(key) != null)
			return true;
		else
			return false;
	}

	// If the key requested is in the table, return the associated value.
	// Otherwise return null.

	// Must run in worst case O(n) and average case O(n/m) where n is the
	// number of key-value pairs in the table and m is the number of "slots"
	// in the table.
	/**
	 * If the key requested is in the table, return the associated value. Otherwise
	 * return null.
	 * 
	 * @param key
	 *            - Takes in key value.
	 * @return return the associated value, else null.
	 */
	public V get(K key) {
		int index = key.hashCode() % getNumSlots();

		if (storage.getAllPairs(index) != null) {
			ArrayOfListsOfPairs.Node<K, V> bucket = storage.get(index, key);

			if (bucket != null && bucket.pair.getKey().equals(key)) {
				return bucket.pair.getValue();
			}

		}
		return null;

	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This is a toString method which prints.
	 * 
	 * @return returns the string representation.
	 */
	public String toString() {
		// you may edit this to make string representations of your
		// lists for testing
		return super.toString();
	}

	/**
	 * This is the main method that runs the hashTable class.
	 * 
	 * @param args
	 *            - Takes in array of string args.
	 */
	public static void main(String[] args) {
		// Some example testing code...

		// make a hash table and add something to it
		HashTable<Integer, String> ht = new HashTable<>(2);
		ht.add(2, "Apple");
		ht.add(2, "skjhf");
		ht.add(4, "fdj");
		ht.add(1, "dbf");

		System.out.println(ht.size());

		// get all pairs at location 0
		Collection<KeyValuePair<Integer, String>> pairs = ht.getInternalTable().getAllPairs(0);

		// should be one pair there...
		if (pairs.size() == 1) {
			// get the first pair from the list
			KeyValuePair<Integer, String> pair = pairs.iterator().next();

			// make sure it's the pair expected
			if (pair.getKey().equals(2) && pair.getValue().equals("Apple")) {
				System.out.println("Yay");
			}
		}
	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	// This will be used to check that you are setting
	// the storage up correctly... it's very important
	// that you (1) are using the ArrayOfListsOfPairs
	// provided and (2) don't edit this at all
	/**
	 * This will be used to check that you are setting the storage up correctly.
	 * 
	 * @return returns array list of pairs of storage.
	 */
	public ArrayOfListsOfPairs<K, V> getInternalTable() {
		return storage;
	}
}