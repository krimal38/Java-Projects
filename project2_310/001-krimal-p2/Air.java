import java.util.NoSuchElementException;

/**
 * This class is called the Air class which implements the interface Queue. The
 * class is used for the implementation of Queue interface. This class is used
 * for the game to store the plates in the air and enqueue and dequeue according
 * to the game rules.
 *
 * @author kshitiz Rimal
 * 
 */
public class Air implements Queue<Plate> {
	// Maximum allowed items in the air... don't allow more than this!
	/**
	 * This is the max capacity that a queue can hold.
	 */
	public static final int MAX_CAPACITY = 13;

	// this is your internal storage
	/**
	 * Instance of AttachedList class.
	 */
	public AttachedList<Plate> internalList = new AttachedList<>();

	// you may _NOT_ add any additional instance variables, use the list above!
	// you should not need any additional private helper methods, but you
	// may add them if you like

	/**
	 * Default constructor.
	 */
	public Air() {
		// any initialization goes here
	}

	// override all the required methods
	// all methods must be O(1)
	// all methods can be written in 3 lines or less of code
	// if you're writing much more than that, something is probably wrong...

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This is the main method which runs the program.
	 * 
	 * @param args
	 *            - the array of integer.
	 */
	public static void main(String[] args) {
		Air queue = new Air();

		for (int i = 1; i < 13; i++) {
			queue.enqueue(new Plate(i));
		}

		System.out.println(queue.size());
		System.out.println(queue.toString());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.isEmpty());
		System.out.println(queue.dequeue());

	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	/**
	 * The toString method is use to print the Air class and its methods.
	 * 
	 * @return Return the string.
	 */
	public String toString() {
		String returnString = "";
		for (Plate p : internalList) {
			returnString = p + returnString;
		}
		return returnString;
	}

	/**
	 * This method enqueue the element of the queue.
	 * 
	 * @param value
	 *            - takes in value from the user.
	 * 
	 * @return Return true if it is being enqueue and the size is less than
	 *         MAX_CAPACITY, else false.
	 * 
	 */
	@Override
	public boolean enqueue(Plate value) {
		if (internalList.size() < MAX_CAPACITY) {
			internalList.add(value);
			return true;
		}
		return false;
	}

	/**
	 * This method dequeue the element from queue.
	 * 
	 * @return Return the element that has been dequeue.
	 * 
	 */
	@Override
	public Plate dequeue() {
		// TODO Auto-generated method stub
		// if (!internalList.isEmpty()) {
		if (internalList.size() == 0) {
			throw new NoSuchElementException();

		} else
			return internalList.remove(0);

	}

	/**
	 * This method checks the size of a queue.
	 * 
	 * @return Return the size of a queue.
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return internalList.size();
	}

	/**
	 * This method checks if the queue is empty or not.
	 * 
	 * @return Return true if the queue is empty else false.
	 * 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return (internalList.size() == 0);
	}

	/**
	 * This method is used for clearing the air class.
	 * 
	 * 
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		internalList.clear();
	}

}
