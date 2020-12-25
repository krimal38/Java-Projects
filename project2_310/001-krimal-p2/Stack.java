// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------

/**
 * The interface class is for Bin stack class. The implementation is done in the
 * Bin class.
 * 
 * @author kshitiz Rimal
 *
 * @param <T>
 *            Takes in the generic type values in the stack.
 *
 */
interface Stack<T> {
	/**
	 * Method used for push.
	 * 
	 * @param value
	 *            - Takes in generic type value from user.
	 * @return true if it push in the stack else false.
	 */
	public boolean push(T value);

	/**
	 * Method used for push.
	 * 
	 * @return Returns the generic type that has been popped from the stack.
	 */
	public T pop(); // throw NoSuchElementException if nothing to pop

	/**
	 * Method used to check the size of stack.
	 * 
	 * @return Returns the size
	 */
	public int size();

	/**
	 * Method to check if the stack is empty.
	 * 
	 * @return Returns true if stack is empty else false.
	 */
	public boolean isEmpty();

	/**
	 * Method to clear the stack.
	 */
	public void clear();
}