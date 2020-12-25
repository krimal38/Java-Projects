import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class is the main implementation class for the LinkedList. This class
 * has all the necessary methods required for LinkedList. This class has been
 * called in Air and Bin class for add and removing the elements from Queue and
 * Stack. This class is used for adding, removing, checking, etc. of the
 * LinkedList.
 *
 * @author kshitiz Rimal
 *
 * @param <T>
 *            Takes in generic type value.
 */
public class AttachedList<T> implements List<T> {
	// for more information on these methods
	// read the documentation of the list interface
	// here: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
	// keep in mind that we are doing a _linked_ list
	// but the documentation is for general lists (like array lists)

	// NOTE: the documentation above is not optional, it tells you things
	// like what exceptions to throw
	/**
	 * This class is the class inside the LinkedList. The class servers as a Node
	 * that is used for traversing the LinkedList and giving the value of the Node.
	 *
	 * @author kshitiz Rimal
	 *
	 * @param <T>
	 *            Takes in generic type value from the LinkedList class.
	 */
	private static class Node<T> {
		// you may NOT change these instance variables and/or
		// add any additional instance variables here
		// (so you may not doubly link your list)
		/**
		 * Value helps to get the value of the node.
		 */
		T value;
		/**
		 * Next points to the next element of the LinkedList. It traverses through the
		 * LinkedList.
		 */
		Node<T> next;

		// you may add more methods here... and they may be public!
		// note: a constructor _is_ a method (just a special type of method)
		// note: you don't have to add anything if you don't want
		// this will work as-is
		/**
		 * This is a constructor that takes in the new value and sets to the generic
		 * type value.
		 * 
		 * @param value
		 *            - Takes in the value and sets to the generic type value.
		 * 
		 */
		private Node(T value) {
			this.value = value;
		}
	}

	/**
	 * points to the head of the LinkedList.
	 */
	private Node<T> head = null;
	/**
	 * Points to the tail of the LinkedList.
	 */
	private Node<T> tail = null;
	/**
	 * Keep track of the size of the LinkedList.
	 */
	private int size = 0;
	// You _MUST_ use head defined above, we will be "breaking into"
	// your class for testing and we'll be using this "head" variable
	// as part of the tests. If you rename or change it, you will
	// not pass the unit tests.

	// Note: if you're interested on what "breaking in" means, it means
	// we'll be using reflection to access your private instance variables.
	// Interested? See: https://docs.oracle.com/javase/tutorial/reflect/index.html

	// you may add more private methods and instance variables here if you want
	// you may add additional private helper functions as well
	// no new protected or public variables or methods

	/**
	 * Default constructor.
	 */
	public AttachedList() {
		// initialize anything you want here...
	}

	/**
	 * Returns the size of the LinkedList.
	 * 
	 * @return Returns the Integer size of the LinkedList.
	 */
	@Override
	public int size() {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(1)
		return size;
	}

	/**
	 * Checks if the list is empty.
	 * 
	 * @return Returns the boolean. True if the list is empty else false.
	 */
	@Override
	public boolean isEmpty() {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(1)
		return size == 0;
	}

	/**
	 * This method finds the index of the given Object.
	 * 
	 * @param o
	 *            - Takes in Object from user.
	 * 
	 * 
	 * @return Returns the integer index otherwise -1.
	 */
	@Override
	public int indexOf(Object o) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)
		// yes, nulls are allowed to be searched for
		if (head.value == o)
			return 0;
		else {
			Node<T> current_val = head;
			int index = 0;

			while (current_val != null) {
				if (current_val.value == o) {
					return index;
				}
				current_val = current_val.next;
				index++;

			}
		}
		return -1;
	}

	/**
	 * This method checks if the list contains the element or not. If yes, return
	 * true otherwise false.
	 * 
	 * @param o
	 *            - Takes in the list object from the user.
	 * 
	 * 
	 * @return Returns boolean. True if the object is present in the list else
	 *         false.
	 */
	@Override
	public boolean contains(Object o) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)
		// yes, nulls are allowed to be searched for
		if (size == 0)
			return false;
		else {
			Node<T> current_var = head;

			while (current_var != null) {

				if (current_var.value == o)
					return true;
				current_var = current_var.next;
			}

		}
		return false;
	}

	/**
	 * This method append the node at the end of the list. The worst case is O(1).
	 * 
	 * @param e
	 *            - Takes in generic type value.
	 * 
	 * 
	 * @return Return boolean statement. True if the elements are successfully added
	 *         in the LinkedList.
	 */
	@Override
	public boolean add(T e) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// this should append to the end of the list
		// O(1) <-- not a typo... think about it!
		// yes, nulls are allowed to be added
		Node<T> newNode = new Node<>(e);

		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
		return true;
	}

	/**
	 * This method append the node at the given position of the LinkedList.
	 * 
	 * @param index
	 *            - First is the index which is taken from the user on where to add
	 *            the node. .
	 * 
	 * @param element
	 *            - The second parameter takes in generic type element and adds in
	 *            the LinkedList.
	 * 
	 * 
	 * 
	 */
	public void add(int index, T element) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)

		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();

		if (index == 0) {
			Node<T> newNode = new Node<>(element);
			newNode.next = head;
			head = newNode;
			size++;

		} else if (index >= size)
			add(element);

		else {
			Node<T> current = head;

			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<T> temp = current.next;
			Node<T> newNode = new Node<T>(element);
			current.next = newNode;
			(current.next).next = temp;
			size++;

		}

	}

	/**
	 * This method is used for removing the node at the given index.
	 * 
	 * @param index
	 *            - Takes in the index that needs to be removed.
	 * 
	 * @return Returns the element that has been removed from the List.
	 *
	 */
	@Override
	public T remove(int index) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		else if (index == 0) {
			Node<T> temporary_var = head;
			head = head.next;
			size--;
			if (size == 0)
				tail = head;
			return temporary_var.value;

		} else {
			Node<T> previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}
			Node<T> current_var = previous.next;
			previous.next = current_var.next;
			size--;
			return current_var.value;
		}

	}

	/**
	 * This method is similar to the remove method with parameter index, but this
	 * method search the node through the value and not index.
	 * 
	 * @param o
	 *            - Takes in object of the LinkedList.
	 * 
	 * @return Returns boolean. True if the element is found and removed. False if
	 *         the element is not found in the List.
	 * 
	 * 
	 */
	@Override
	public boolean remove(Object o) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)
		// yes, nulls are allowed to removed
		Node<T> current = head;
		Node<T> prev = head;
		int pos = 0;
		boolean IsRemove = false;

		if (head.value == o) {
			head = head.next;
			size--;
			if (size == 0) {
				tail = head;
			}
			return IsRemove = true;
		}

		else {

			while (current != null && !current.value.equals(o)) {
				current = current.next;
				pos++;

			}
			if (current != null) {
				for (int i = 0; i < (pos - 1); i++) {
					prev = prev.next;
				}

				Node<T> temporary_var = prev.next;
				prev.next = current.next;
				temporary_var.next = null;

				size--;
				IsRemove = true;
			}
		}
		return IsRemove;
	}

	/**
	 * This method clear the List. The worse case is O(1).
	 * 
	 */
	@Override
	public void clear() {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(1) <-- not a typo... think about it!
		size = 0;
		head = tail = null;
	}

	/**
	 * This method gets the position of the node of the given index.
	 * 
	 * @param index
	 *            - Takes in the index that needs to be return.
	 * 
	 * @return Returns the value of the index.
	 * 
	 */
	@Override
	public T get(int index) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		else {
			Node<T> currentIndex = head;
			for (int i = 0; i < index; i++) {
				currentIndex = currentIndex.next;
			}
			return currentIndex.value;
		}
	}

	/**
	 * This method sets the generic type element in the desired index of the
	 * LinkedList.
	 * 
	 * @param index
	 *            - Takes in the index of where the list wants to be set.
	 * 
	 * @param element
	 *            - Generic type element which will be the node of LinkedList.
	 * 
	 * @return Returns the value which of the node.
	 * 
	 * 
	 */
	@Override
	public T set(int index, T element) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");
		// O(n)
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		else {
			Node<T> currentIndex = head;
			for (int i = 0; i < index; i++) {
				currentIndex = currentIndex.next;
			}
			currentIndex.value = element;
			return currentIndex.value;
		}
	}

	/**
	 * Still have to remove the slice This method slice the element from the given
	 * set of the index inclusive. Then it removes that slice from the original
	 * index. Throws IndexOutOfBoundsException if fromIndex _or_ toIndex is invalid.
	 * 
	 * @param fromIndex
	 *            - fromIndex. The index to start with.
	 * 
	 * @param toIndex
	 *            - toIndex. The index to end.
	 * 
	 * @return The the slice as a new AttachedList.
	 */
	public AttachedList<T> slice(int fromIndex, int toIndex) {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");

		// removes a "slice" from fromIndex to toIndex (inclusive)
		// return the slice as a new AttachedList
		// throws IndexOutOfBoundsException if fromIndex _or_ toIndex is invalid

		// O(n)
		AttachedList<T> SlicedList = new AttachedList<>();
		Node<T> startIndex = head;
		Node<T> endIndex = head;
		int count = 0;

		if (fromIndex >= size() || toIndex >= size()) {
			throw new IndexOutOfBoundsException();
		}

		if (fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < fromIndex; i++) {
			startIndex = startIndex.next;
			count++;
		}

		for (int i = 0; i < toIndex; i++) {
			endIndex = endIndex.next;
		}

		Node<T> temp = startIndex;
		Node<T> pointer_at_endIndex = endIndex;
		Node<T> pointer_at_fromIndex_minus1 = head;

		if (fromIndex == toIndex) {
			SlicedList.add(startIndex.value);
			head = head.next;
			size--;

		} else if (fromIndex == size() && toIndex == size()) {
			SlicedList.add(endIndex.value);
			remove(endIndex);

		} else {

			for (int i = 0; i < (count - 1); i++) {
				pointer_at_fromIndex_minus1 = pointer_at_fromIndex_minus1.next;
			}

			for (int i = 0; i < (toIndex - fromIndex) + 1; i++) {
				SlicedList.add(temp.value);
				temp = temp.next;
				size--;
			}
			Node<T> tempo = pointer_at_fromIndex_minus1.next;
			pointer_at_fromIndex_minus1.next = pointer_at_endIndex.next;
			tempo.next = null;

		}

		return SlicedList;

	}

	/**
	 * This method returns the returns a copy of the list with the elements
	 * reversed. This is O(n).
	 * 
	 * @return The new copy of the list in a reverse order.
	 */
	public AttachedList<T> reverseCopy() {
		// throw new UnsupportedOperationException("Not supported yet. Replace this line
		// with your implementation.");

		// returns a copy of the list with the elements reversed
		// does not alter the original list
		// O(n)

		AttachedList<T> list = new AttachedList<>();
		Node<T> current_val = head;

		while (current_val != null) {
			list.add(0, current_val.value);
			current_val = current_val.next;

		}
		return list;

	}

	/**
	 * This method changes the 2D List into 1D list. Given a 2D list of lists
	 * (packedList), "flatten" the list into 1D.
	 * 
	 * @param packedList
	 *            - Takes in 2D list from the user.
	 * @return this is type parameter. Changes that 2D list given by user to 1D and
	 *         returns.
	 */
	public static <E> AttachedList<E> flatten(AttachedList<AttachedList<E>> packedList) {
		// return null;
		// given a 2D list of lists (packedList), "flatten" the list into 1D
		// Example 1: [[1,2,3],[4,5],[6]] becomes [1,2,3,4,5,6]
		// Example 2: [[null],[1,3],[5],[6]] becomes [null,1,3,5,6]
		// IMPORTANT: the above examples are _lists NOT arrays_

		AttachedList<E> list = new AttachedList<>();

		for (int i = 0; i < packedList.size; i++) {
			for (int j = 0; j < packedList.get(i).size; j++) {
				list.add(packedList.get(i).get(j));
			}
		}
		return list;

	}

	/**
	 * Given a 1D (flatList), "pack" sequential items together to form a 2D list of
	 * values.
	 * 
	 * @param flatList
	 *            - Takes in the 1D list from the user.
	 * 
	 * @return Changes that flatList to 2D list and returns.
	 */
	public static <E> AttachedList<AttachedList<E>> pack(AttachedList<E> flatList) {
		// return null;
		// given a 1D (flatList), "pack" sequential items together
		// to form a 2D list of values

		// Example 1: [1,1,2,3,3] becomes [[1,1],[2],[3,3]]
		// Example 1: [1,1,2,1,1,2,2,2,2] becomes [[1,1],[2],[1,1],[2,2,2,2]]
		// Example 3: [1,2,3,4,5] becomes [[1],[2],[3],[4],[5]]
		// IMPORTANT: the above examples are _lists NOT arrays_

		// promise: we will never test this with nulls in the flatList
		// though there's no harm in coding it to work with nulls

		AttachedList<AttachedList<E>> list = new AttachedList<>();
		E temp = null;

		AttachedList<E> insideList = new AttachedList<>();

		for (int i = 0; i < flatList.size(); i++) {
			if (temp != flatList.get(i)) {
				temp = flatList.get(i);
				if (!insideList.isEmpty()) {
					list.add(insideList);
					insideList = new AttachedList<>();
				}

			}
			insideList.add(flatList.get(i));
		}
		list.add(insideList);
		return list;

	}

	@Override
	public Iterator<T> iterator() {
		// this method is outlined for you... just fill out next() and hasNext()
		// NO ADDITIONAL ANYTHING (METHODS, VARIABLES, ETC.) INSIDE THE ANONYMOUS CLASS
		// You may NOT override remove() or any other iterator methods
		return new Iterator<T>() {
			// starts at the head
			/**
			 * A pointer pointing to the head element.
			 */
			private Node<T> current = head;

			/**
			 * The method checks if there is next element or not. O(1) complexity.
			 * 
			 * @return Return true if it has next element else false.
			 */
			@Override
			public boolean hasNext() {
				// throw new UnsupportedOperationException(
				// "Not supported yet. Replace this line with your implementation.");
				// O(1)
				return (current != null);
			}

			/**
			 * This method checks if there is next element and returns the next element.
			 * O(1) complexity.
			 * 
			 * @return Return the element.
			 * 
			 * 
			 */
			@Override
			public T next() {
				// throw new UnsupportedOperationException(
				// "Not supported yet. Replace this line with your implementation.");
				// O(1)
				T element = current.value;
				current = current.next;
				return element;
			}
		};
	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This method creates a string to print an Object.
	 * 
	 * @return the string Object.
	 */
	public String toString() {
		// return null;
		// you may edit this to make string representations of your
		// list for testing

		StringBuilder result = new StringBuilder();

		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.value);
			current = current.next;
			if (current != null) {
				result.append(",");
			}
		}
		return result.toString();

	}

	/**
	 * This is the main method which executes the program.
	 * 
	 * @param args
	 *            - Takes in the array of string.
	 */
	public static void main(String[] args) {

		AttachedList<String> list = new AttachedList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("5");

		System.out.print(list.toString() + " \n");

		System.out.println("Reverse ARRAY");
		System.out.println(list.reverseCopy().toString());

		AttachedList<AttachedList<String>> packed = pack(list);
		Iterator<AttachedList<String>> iter = packed.iterator();
		System.out.println("After pack: ");
		while (iter.hasNext()) {

			System.out.println(iter.next().toString());
		}

		System.out.println("After flatten:");

		AttachedList<String> flatten = flatten(packed);
		Iterator<String> ite = flatten.iterator();
		while (ite.hasNext()) {
			System.out.print(ite.next().toString() + " ");
		}
		System.out.println("\n" + list.size());
		System.out.println("\nSliced LinkedList");
		System.out.println(list.slice(2, 2));
		System.out.println(list.toString());

		/*
		 * System.out.println(list.get(6)); list.clear();
		 * System.out.println(list.isEmpty());
		 */

		/**
		 * int x; boolean b; String s;
		 * 
		 * AttachedList<String> list1 = new AttachedList<>(); x = list1.size(); b =
		 * list1.isEmpty(); b = list1.add("Banana"); try { list1.add(0, "Banana"); }
		 * catch (IndexOutOfBoundsException e) { } b = list1.contains("Banana"); try { s
		 * = list1.get(0); } catch (IndexOutOfBoundsException e) { } try { s =
		 * list1.set(0, "Apple"); } catch (IndexOutOfBoundsException e) { } x =
		 * list1.indexOf("Banana"); try { s = list1.remove(0); } catch
		 * (IndexOutOfBoundsException e) { } b = list1.remove("Banana"); list1.clear();
		 * try { AttachedList<String> list2 = list1.slice(0, 0); } catch
		 * (IndexOutOfBoundsException e) { } AttachedList<String> list3 =
		 * list1.reverseCopy(); AttachedList<AttachedList<Integer>> list4 = new
		 * AttachedList<>(); AttachedList<Integer> list5 = AttachedList.flatten(list4);
		 * AttachedList<AttachedList<Integer>> list6 = AttachedList.pack(list5); for
		 * (AttachedList<Integer> list7 : list6) { } Object[] arr1 = list5.toArray();
		 * String[] arr2 = list5.toArray(new String[0]);
		 * 
		 * Plate p = new Plate(0); s = p.toString(); x = p.getNumber();
		 * 
		 * Bin bin = new Bin(); s = bin.toString(); b = bin.push(p); Plate p2 =
		 * bin.pop(); x = bin.size(); b = bin.isEmpty(); bin.clear();
		 * 
		 * Air air = new Air(); s = air.toString(); b = air.enqueue(p); Plate p3 =
		 * air.dequeue(); x = air.size(); b = air.isEmpty(); air.clear();
		 * 
		 * Spinner sy = new Spinner(1); s = sy.toString(); sy.passPlate();
		 * sy.putDownPlate(); try { sy.pickUpPlate(); } catch (RuntimeException e) { }
		 * try { sy.spinPlate(); } catch (RuntimeException e) { } try { sy.catchPlate();
		 * } catch (RuntimeException e) { }
		 */

	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------

	/**
	 * This method takes the object and returns the item.
	 * 
	 * @return Return the items of toArray.
	 *
	 */
	@Override
	public Object[] toArray() {
		Object[] items = new Object[this.size()];
		int i = 0;
		for (T val : this) {
			items[i++] = val;
		}
		return items;
	}

	/**
	 * This method is only used to return the array on top.
	 * 
	 * @param a
	 *            - Takes in generic type variable.
	 * @return Return the generic type toArray.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		return (T[]) this.toArray();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not supported.");
	}
}