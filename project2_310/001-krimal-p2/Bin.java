import java.util.NoSuchElementException;

/**
 * This is the Bin class which implements the stack interface. This class is
 * used for the game and act as a Bin or a storage place for plates. The user
 * takes one stack at a time from this class.
 * 
 *
 * @author kshitiz Rimal
 * 
 *
 */
public class Bin implements Stack<Plate> {
	// this is your internal storage
	/**
	 * Instance of AttachedList class.
	 */
	public AttachedList<Plate> internalList = new AttachedList<>();

	// you may _NOT_ add any additional instance variables, use the list above!
	// you should not need any additional private helper methods, but you
	// may add them if you like
	/**
	 * This is a default constructor.
	 */
	public Bin() {
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
	 * This is the main method which is used for running the class.
	 * 
	 * @param args
	 *            - Takes the array of string.
	 */
	public static void main(String[] args) {

		Bin stack = new Bin();

		for (int i = 0; i < 17; i++) {
			stack.push(new Plate(i));
		}

		System.out.println(stack.toString());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.isEmpty());
		System.out.println(stack.toString());
		System.out.println(stack.size());
	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	/**
	 * This method is use to print the values of the Bin class.
	 * 
	 * @return The output as a string.
	 */
	public String toString() {
		String returnString = "";
		boolean first = true;
		for (Plate p : internalList) {
			if (first) {
				returnString = returnString + p;
				first = false;
			} else {
				returnString = returnString + "|" + p;
			}
		}
		return returnString;
	}

	/**
	 * This method push the value in the stack.
	 * 
	 * @param value
	 *            - Takes the value from the user.
	 * 
	 *
	 * @return Return true if the value is added successfully.
	 */
	@Override
	public boolean push(Plate value) {
		// TODO Auto-generated method stub
		internalList.add(0, value);
		return true;
	}

	/**
	 * This method pops the element from the stack.
	 * 
	 * @return Return the element that's been popped out from stack.
	 * 
	 */
	@Override
	public Plate pop() {
		// TODO Auto-generated method stub
		if (internalList.isEmpty())
			throw new NoSuchElementException();
		else
			return internalList.remove(0);
	}

	/**
	 * This method checks and keep track of the size of the stack.
	 * 
	 * @return Return the integer size of the stack.
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return internalList.size();
	}

	/**
	 * This method checks if the stack is empty or not.
	 * 
	 * @return Return true if the stack is empty else false.
	 * 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return internalList.size() == 0;
	}

	/**
	 * This method is used for clearing the bin class. This class clears the bin
	 * class if called.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

		internalList.clear();
	}
}