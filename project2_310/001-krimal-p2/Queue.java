// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------

/**
 * This is an interface class. So, every implementation of its method is done in
 * the Air class.
 *
 * @author kshitiz Rimal
 *
 * @param <T>
 *            Holds the generic type values.
 * 
 */
interface Queue<T> {
	/**
	 * Append the element to queue.
	 * 
	 * @param value
	 *            - Takes in generic type value to append in the Queue.
	 * @return Return true if enqueue else false.
	 */
	public boolean enqueue(T value);

	/**
	 * Removes the element from the queue.
	 * 
	 * @return Return generic element.
	 */
	public T dequeue(); // throw NoSuchElementException if nothing to dequeue

	/**
	 * Checks the size of the queue.
	 * 
	 * @return the size of queue.
	 */
	public int size();

	/**
	 * Checks if the list is empty or not.
	 * 
	 * @return boolean statement true if queue is empty else false.
	 */
	public boolean isEmpty();

	/**
	 * Clear the value of queue.
	 */
	public void clear();
}