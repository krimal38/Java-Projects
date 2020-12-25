
/**
 * BetterArray is used as dynamically allocated array that adds and deletes
 * elements. It has methods which are used by Goblin class.
 * 
 * @author kshitiz Rimal
 * @param <T>
 *            Holds generic type data that is used by BetterArray class.
 */

public class BetterArray<T> {
	/**
	 * Global variable used in the class. default initial capacity
	 */
	private static final int DEFAULT_CAPACITY = 2; // default initial capacity / minimum capacity
	/**
	 * Underlying array.
	 */
	private T[] data; // underlying array, you MUST use this for full credit
	/**
	 * Initial capacity of an array.
	 */
	private int initialCapacity;
	/**
	 * Checks the size of an array.
	 */
	private int size = 0;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 * This is a default constructor.
	 */
	@SuppressWarnings("unchecked")
	public BetterArray() {

		data = (T[]) new Object[DEFAULT_CAPACITY];

	}

	/**
	 * Sets equal initialCapacity to instance variable/Global variable.
	 * 
	 * @param constructor
	 *            that takes in the capacity.
	 */
	@SuppressWarnings("unchecked")
	public BetterArray(int initialCapacity) {

		this.initialCapacity = initialCapacity;
		data = (T[]) new Object[initialCapacity];
		if (this.initialCapacity < 1)
			throw new IllegalArgumentException("IllegalArgumentException");
	}

	/**
	 * @return the size of an array.
	 */
	public int size() {

		return size; // returns the size of array
	}

	/**
	 * @return the capacity of an array.
	 */
	public int capacity() {

		return data.length; // returns the length of data array
	}

	/**
	 * adds an element to the end.
	 * 
	 * @param Takes
	 *            in generic value and adds into its method.
	 * @return boolean statement true.
	 */
	@SuppressWarnings("unchecked")
	public boolean append(T value) {

		if (size() + 1 <= capacity() || ensureCapacity(2 * capacity())) {
			data[size] = value;
			size++;
		}
		return true;
	}

	/**
	 * This method is used to append items as well as insert items.
	 * 
	 * @param Takes
	 *            in index at where it wanted to be inserted.
	 * @param Takes
	 *            in generic value and adds into the specified index.
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) {

		T temp;
		if (index > capacity())
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");

		if (size() + 1 <= capacity()) {
			T prevValue = value;
			for (int i = index; i < size() + 1; i++) {
				temp = prevValue;
				prevValue = data[i];
				data[i] = temp;
			}
			size++;

		} else if (ensureCapacity(2 * capacity())) {

			T prevValue = value;
			for (int i = index; i < size() + 1; i++) {
				temp = prevValue;
				prevValue = data[i];
				data[i] = temp;
			}
			size++;
		}
	}

	/**
	 * This method acts like a getter method.
	 * 
	 * @param Takes
	 *            index.
	 * @return the item at index.
	 */
	public T get(int index) {

		if (index > capacity() || index < 0)
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");// IndexOutOfBoundsException for invalid
																				// index

		return data[index]; // returns the item at index

	}

	/**
	 * This method is used to replace element if needed. It does not add or remove
	 * elements. Replaces the value at the specified index.
	 * 
	 * @param Takes
	 *            in index at where it wanted to be inserted.
	 * @param Replaces
	 *            the value at the specified index.
	 * @return the generic value of the old item value which has been replaced by
	 *         the new value.
	 */
	public T replace(int index, T value) {

		T oldItem = data[index]; // getting old value of data

		if (index > capacity() || index < 0)
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); // IndexOutOfBoundsException for invalid
																				// index

		data[index] = value; // The data at index will be the new value that will be replaced

		return oldItem; // returning old item in data array
	}

	/**
	 * This method is used for deleting elements from the generic array.
	 * 
	 * @param Takes
	 *            in index for deleting the item at the position.
	 * @return deleted item.
	 */
	@SuppressWarnings("unchecked")
	public T delete(int index) {

		T temp;
		int newCapacity = 0;

		if (index < 0 || index > capacity())
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); // checks for invalid index

		T element = data[index]; // sets generic element to the data of indicated index.

		T nextVal = data[index + 1]; // sets generic nextVal with the specified data array+1

		for (int i = index; i < size(); i++) { // loops through the size
			temp = nextVal;
			nextVal = i + 2 < size() ? data[i + 2] : null; // ternary operator used for checking if data[i+2] exists or
															// not. If not throws null
			data[i] = temp;
		}
		size--; // decrement the size

		newCapacity = size() < capacity() / 4 ? capacity() / 2 : capacity(); // halve capacity if the number of elements
																				// falls below 1/4 of the capacity
		if (newCapacity > DEFAULT_CAPACITY) { // checking to see if capacity is not below DEFAULT_CAPACITY
			T[] newCapacityArray = (T[]) new Object[newCapacity]; // new generic array created to make a copy of
																	// existing data array

			for (int i = 0; i < newCapacity; i++) { // copying arrays
				newCapacityArray[i] = data[i];
			}

			data = newCapacityArray; // data is equal to new generic array
		}

		return element; // returns the element at position index
	}

	/**
	 * 
	 * @param takes
	 *            in generic value.
	 * @return the first occurrence in the data array, if not found then returns -1.
	 */
	public int firstIndexOf(T value) {

		int count = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == value)
				return count = i;

		}
		return -1;
	}

	/**
	 * This method checks the capacity and adds it if needed. This method is used
	 * for the expansion of the capacity of array Some test cases: newCapacity is
	 * below DEFAULT_CAPACITY; or newCapacity is not large enough to accommodate
	 * current number of items
	 * 
	 * @param takes
	 *            in new capacity that is needed for expansion.
	 * @return true if newCapacity gets applied; false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public boolean ensureCapacity(int newCapacity) {

		if (newCapacity < DEFAULT_CAPACITY)
			return false; // checking if newCapacity is below DEFAULT_CAPACITY

		else if (newCapacity < size())
			return false; // newCapacity is not large enough to accommodate current number of items

		else {

			T[] newData = (T[]) new Object[newCapacity]; // larger array

			// copying data to a newdata
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			data = newData;
			return true;

		}

	}

	/**
	 * Creates a clone of the data array. Returns the cloned array.
	 */

	public BetterArray<T> clone() {

		BetterArray<T> CloneArray = new BetterArray<>(capacity());
		CloneArray.size = size(); // setting the size as the original array

		T[] newData = (T[]) new Object[capacity()]; // larger array

		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
		CloneArray.data = data;

		return CloneArray;
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	public static void main(String args[]) {

		// create a smart array of integers
		BetterArray<Integer> nums = new BetterArray<>();
		if ((nums.size() == 0) && (nums.capacity() == 2)) {
			System.out.println("Yay 1");
		}

		// append some numbers
		for (int i = 0; i < 3; i++)
			nums.add(i, i * 2);

		if (nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4) {
			System.out.println("Yay 2");
		}

		// create a smart array of strings
		BetterArray<String> msg = new BetterArray<>();

		// insert some strings
		msg.add(0, "world");
		msg.add(0, "hello");
		msg.add(1, "new");
		msg.append("!");

		// replace and checking
		if (msg.get(0).equals("hello") && msg.replace(1, "beautiful").equals("new") && msg.size() == 4
				&& msg.capacity() == 4) {
			System.out.println("Yay 3");
		}

		// change capacity
		if (!msg.ensureCapacity(0) && !msg.ensureCapacity(3) && msg.ensureCapacity(20) && msg.capacity() == 20) {
			System.out.println("Yay 4");
		}

		// delete and shrinking
		if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world") && msg.size() == 3
				&& msg.capacity() == 10) {
			System.out.println("Yay 5");
		}

		// firstIndexOf and clone
		// remember what == does on objects... not the same as .equals()
		BetterArray<String> msgClone = msg.clone();
		if (msgClone != msg && msgClone.get(1) == msg.get(1) && msgClone.size() == msg.size()
				&& msgClone.capacity() == msg.capacity() && msgClone.firstIndexOf("world") == 1
				&& msgClone.firstIndexOf("beautiful") == -1) {
			System.out.println("Yay 6");
		}

	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	public String toString() {
		if (size() == 0)
			return "";

		StringBuffer sb = new StringBuffer();
		sb.append(get(0));
		for (int i = 1; i < size(); i++) {
			sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString();
	}
}