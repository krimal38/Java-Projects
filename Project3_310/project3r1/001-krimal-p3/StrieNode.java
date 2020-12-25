/**
 * This is a node class that sets each node to its children. It represents a
 * single Strie node. It provides methods required to manipulate a node.
 * 
 * @author kshitiz Rimal
 *
 */
public class StrieNode {

	// Complete all the methods in this class even if you are not using them for
	// your Strie class implementation
	// You MAY NOT use your own variable for a purpose that has been addressed by
	// one of the private variables provided in this template
	// For example, You may not use your own 'marker' to indicate the end of a word
	// as the provided 'endMarker' variable addresses this purpose

	// NOTE: One easy way to convert characters from an alphabet to integer indices
	// is to subtract a character from the the first letter (char) of the alphabet
	// Example from ASCII table: 'a': 97 and 'z': 122. You have a char variable ch.
	// You get an integer if you do: ch - 'a'
	// Example continued: If ch contains 'a', then you get a 0. If ch contains 'b',
	// you get 1.....and so on.

	/**
	 * Number of children of each node. This is constant and does not change.
	 */
	private static final int NUM_CHILDREN = 26; // Number of children for each node. Do not change this value.

	/**
	 * Array of StrieNode that holds children nodes.
	 */
	private StrieNode[] children = new StrieNode[NUM_CHILDREN]; // Use this array to hold children nodes

	/**
	 * Marks the end of a word.
	 */
	private boolean endMarker; // Marks the end of a word

	/**
	 * Marks a character/child node as removed.
	 */
	private boolean removed; // Marks a character/child-node as 'removed' if this node/char denoted the end

	/**
	 * Default constructor.
	 */
	public StrieNode() {
		// constructor
		// initialize anything that needs initialization

	}

	/**
	 * Set the removed status = true.
	 */
	public void setRemoved() {
		// set the "removed" status
		// O(1)
		removed = true;

	}

	/**
	 * Unset the removed status = false.
	 */
	public void unsetRemoved() {
		// unset the "removed" status
		// O(1)
		removed = false;

	}

	/**
	 * returns the 'removed' status of a node.
	 * 
	 * @return boolean statement if removed status is true else false.
	 */
	public boolean isRemoved() {
		// returns the 'removed' status of a node
		// O(1)

		return removed;
	}

	/**
	 * This method converts the char into int using ASCII and returns boolean.
	 * 
	 * @param ch
	 *            - Takes in char.
	 * @return true if the value is found in the children node otherwise false.
	 */
	public boolean containsChar(char ch) {
		// returns true if node contains ch
		// O(1)
		int value = ch - 97;

		if (children[value] != null)
			return true;
		return false;
	}

	/**
	 * This method converts the char into int using ASCII and returns the node that
	 * contains character.
	 * 
	 * @param ch
	 *            - Takes in char.
	 * @return returns the node where char is located.
	 */
	public StrieNode getChild(char ch) {
		// returns the node that contains ch
		// O(1)
		int value = ch - 97; // ASCII char - 97 which gives us index

		if (children[value] == null)
			return null;
		else
			return children[value];
	}

	/**
	 * This method sets a node to StrieNode.
	 * 
	 * @param ch
	 *            - Takes in character input.
	 * @param node
	 *            - Takes in node as input.
	 */
	public void putChild(char ch, StrieNode node) {
		// sets a a node (that contains ch) to node
		// O(1)
		int value = ch - 97;

		children[value] = node;

	}

	/**
	 * This method makes a copies the children array and returns the new copied
	 * array.
	 * 
	 * @return the copied StrieNode array.
	 */
	public StrieNode[] getAllChildren() {
		// returns the children of a node
		// O(1)
		StrieNode[] Childern_node = new StrieNode[NUM_CHILDREN];

		for (int i = 0; i < children.length; i++) {
			if (children[i] != null)
				Childern_node[i] = children[i];
		}
		return Childern_node;
	}

	/**
	 * This method is used for getting number of children and returns the number of
	 * children.
	 * 
	 * @return the number of children.
	 */
	public int getNumChildren() {
		// returns the number of children
		// O(1)

		int index = 0;

		for (int i = 0; i < children.length; i++) {
			if (children[i] != null)
				index++;
		}
		return index;

	}

	/**
	 * Sets the end marker to true.
	 */
	public void setEnd() {
		// Sets the end marker to indicate the end of a string/word
		// O(1)
		endMarker = true;
	}

	/**
	 * Sets the end marker to false.
	 */
	public void unsetEnd() {
		// Unsets a previously set end marker
		// O(1)
		endMarker = false;

	}

	/**
	 * checks the current node is marked as the end of word.
	 * 
	 * @return Boolean true if the end marker is end to true else false.
	 */
	public boolean isEnd() {
		// Checks whether the current node is marked as the end of a string/word
		// O(1)
		return endMarker;
	}

	/**
	 * Checks if the node is empty or not.
	 * 
	 * @return boolean true if the node is empty else false.
	 */
	public boolean isEmptyNode() {
		// checks if a node is empty
		// O(1)

		if (getNumChildren() == 0)
			return true;

		return false;
	}
	// you may add more methods if needed
	// you also don't have to have anything else than what's written
}
