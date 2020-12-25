//This structure is an array where each entry is the head of its own
//linked list. The linked lists are made up of "bare nodes" (i.e.
//they are not "wrapped" in a nice linked list class). Each node
//in each linked list contains a key-value pair (rather than an single
//value).

//This class will form the baseline for creating a hash table for a
//map that uses separate chaining (each entry in a map is a key-value
//pair). This class will also form a baseline for creating an adjacency
//list (where each entry is a key-value pair where keys are the
//"adjacent" node and values are the connection between them). This way
//we have a universal way for to access your internal structures when
//grading these two classes.

//You have a lot of freedom in how you design this class, as long as
//the provided code work as described. However, because this is only
//a baseline for the other classes you are writing, it would be bad
//design on your part to add in anything specific to hash tables
//(such as rehashing) or specific to graphs (such as source/destination
//information for edges). Our advice to you is: (1) keep it simple
//and (2) think before you code.

//Read the "do not edit" section carefully so that you know what is
//already available. This should help you form some ideas of what
//types of things are missing.

//You may: Add additional methods or variables of any type (even
//public!), but again, you _must_ use the provided Node class, the
//provided "storage" instance variable, and all provided methods
//_must still work_.

//You may not import anything from java.util (and you may not use anything
//from java.util in your part of the code). We use java.util.ArrayList in
//the provided code, but it is not available to you.

/**
 * 
 * @author kshitiz Rimal and Professors.
 *
 * @param <K>
 *            - Takes in key value.
 * @param <V>
 *            - Key value pair.
 */
public class ArrayOfListsOfPairs<K, V> {
	// This is your internal structure, you must use this
	// you may not change the type, name, privacy, or anything
	// else about this variable. It is initialized in the
	// provided constructor (see the do-not-edit section)
	// and the Node class is also defined there.
	/**
	 * This is the internal structure.
	 */
	private Node<K, V>[] storage;

	// Your code goes here!

	/**
	 * This method is the helper method for get method in hashTable.
	 * 
	 * @param index
	 *            - Takes in integer value.
	 * @param key
	 *            - takes a key.
	 * @return the node otherwise null.
	 */
	public Node<K, V> get(int index, K key) {
		for (KeyValuePair<K, V> i : getAllPairs(index)) {
			if (i.getKey().equals(key)) {
				Node<K, V> newNode = new Node<K, V>(i);
				return newNode;
			}
		}
		return null;
	}

	/**
	 * This is the helper method for add.
	 * 
	 * @param key
	 *            - Takes in key.
	 * @param value
	 *            - Takes the value of the key.
	 * @param size
	 *            - Takes in the size of hashCode.
	 * @return true if added else false.
	 */
	public boolean HelperMethodForadd(K key, V value, int size) {

		Node<K, V> var = new Node<>(new KeyValuePair<>(key, value));

		if (storage[size] != null) {
			Node<K, V> current = storage[size];
			Node<K, V> temp = storage[size];

			while (temp != null) {
				if (temp.pair.getKey() == key)
					return false;
				else {
					temp = temp.next;
				}
				while (current.next != null) {
					if (current.pair.getKey() == var.pair.getKey())
						return false;

					else {
						current = current.next;
					}
				}
				current.next = temp;
			}
		} else {
			storage[size] = var;
		}
		return true;

	}

	/**
	 * This is the helper method for add.
	 * 
	 * @param key
	 *            - Takes in key.
	 * @param value
	 *            - Takes the value of the key.
	 * @return true if added else false.
	 */
	public boolean add(K key, V value) {

		Node<K, V> var = new Node<>(new KeyValuePair<>(key, value));
		int size = Math.abs(key.hashCode() % storage.length);
		if (storage[size] != null) {
			Node<K, V> current = storage[size];
			Node<K, V> temp = storage[size];

			while (temp != null) {
				if (temp.pair.getKey() == key)
					return false;
				else {
					temp = temp.next;
				}
				while (current.next != null) {
					if (current.pair.getKey() == var.pair.getKey())
						return false;

					else {
						current = current.next;
					}
				}
				current.next = temp;
			}
		} else {
			storage[size] = var;
		}
		return true;
	}

	/**
	 * This method is the helper method for remove class in HashTable.
	 * 
	 * @param hashcode
	 *            - Takes in integer hashcode.
	 * @param key
	 *            - Takes key value pair.
	 * @return true if removed else false.
	 */
	public boolean removehelpermethod(int hashcode, K key) {
		Node<K, V> currentVal;
		Node<K, V> prev = null;

		currentVal = storage[hashcode];

		while (currentVal != null) {

			if (currentVal.pair.getKey().equals(key)) {

				if (currentVal != storage[hashcode]) {

					prev.next = currentVal.next;

				} else {
					storage[hashcode] = currentVal.next;
				}
				return true;
			}
			prev = currentVal;
			currentVal = currentVal.next;
		}
		return false;
	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This is the to string method.
	 * 
	 * @return return string representation.
	 */
	public String toString() {
		// you may edit this to make string representations of your
		// lists for testing
		return super.toString();
	}

	/**
	 * This is the main method that runs the ArrayOfListOfParis.
	 * 
	 * @param args
	 *            - Takes in the array string of args.
	 */
	public static void main(String[] args) {

	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	// This is what one node in one linked list looks like
	/**
	 * 
	 * @author Professors.
	 *
	 * @param <K>
	 *            - Takes in key value.
	 * @param <V>
	 *            - Takes in key value pair.
	 */
	public static class Node<K, V> {
		// it contains one key-value pair
		/**
		 * It contains one key-value pair.
		 */
		public KeyValuePair<K, V> pair;

		// and one pointer to the next node
		/**
		 * One pointer to the next node.
		 */
		public Node<K, V> next;

		// convenience constructor
		/**
		 * Constructor.
		 * 
		 * @param pair
		 *            - Takes key value pair.
		 */
		public Node(KeyValuePair<K, V> pair) {
			this.pair = pair;
		}

		// convenience constructor
		/**
		 * This is the constructor.
		 * 
		 * @param pair
		 *            - Takes the pair.
		 * @param next
		 *            - The next value.
		 */
		public Node(KeyValuePair<K, V> pair, Node<K, V> next) {
			this.pair = pair;
			this.next = next;
		}
	}

	// Creates an array with the specified number of lists-of-pairs
	/**
	 * Array with the specified number of lists-of-pairs.
	 * 
	 * @param numLists
	 *            - Takes in integer numLists.
	 */
	@SuppressWarnings("unchecked")
	public ArrayOfListsOfPairs(int numLists) {
		storage = (Node<K, V>[]) new Node[numLists];
	}

	// Returns the number of lists in this collection
	/**
	 * List in the collection.
	 * 
	 * @return - Returns the number of lists in this collection
	 */
	public int getNumLists() {
		return storage.length;
	}

	// Returns all key-value pairs in the specified sublist of this collection
	/**
	 * This method gets all the pairs of values.
	 * 
	 * @param listId
	 *            - Takes in integer listId.
	 * @return Returns all key-value pairs in the specified sublist of this
	 *         collection.
	 */
	public java.util.ArrayList<KeyValuePair<K, V>> getAllPairs(int listId) {
		java.util.ArrayList<KeyValuePair<K, V>> lst = new java.util.ArrayList<>();

		Node<K, V> current = storage[listId];
		while (current != null) {
			lst.add(current.pair);
			current = current.next;
		}

		return lst;
	}

	// Returns all key-value pairs in this collection
	/**
	 * This method gets all the pairs.
	 * 
	 * @return Returns all key-value pairs in this collection.
	 */
	public java.util.ArrayList<KeyValuePair<K, V>> getAllPairs() {
		java.util.ArrayList<KeyValuePair<K, V>> lst = new java.util.ArrayList<>();

		for (int i = 0; i < storage.length; i++) {
			lst.addAll(getAllPairs(i));
		}

		return lst;
	}
}
