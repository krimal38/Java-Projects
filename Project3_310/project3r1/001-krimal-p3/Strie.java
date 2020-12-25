/**
 * This class implements the Strie data structure. It uses StrieNode objects for
 * storing a node. It has Strie operations such as insert, remove, search,
 * contains a word, etc.
 * 
 * @author kshitiz Rimal
 *
 */
public class Strie {
	// Do NOT remove any method even if you are not implementing it

	/**
	 * The root of a Strie.
	 */
	private StrieNode root; // the root of a strie

	/**
	 * Default constructor.
	 */
	public Strie() {
		// constructor
		// initialize anything that needs initialization
		root = new StrieNode();
	}

	/**
	 * This method checks if the myStrie is empty or not.
	 * 
	 * @param myStrie
	 *            - Takes in the myStrie of Strie class and check if its root is
	 *            equal and number of children is 0.
	 * @return true if the myStrie is empty else false.
	 */
	public static boolean isEmptyStrie(Strie myStrie) {
		// returns true if myStrie is empty
		// O(1)
		if (myStrie.root == null || myStrie.root.getNumChildren() == 0)
			return true;

		return false;
	}

	/**
	 * This method is used for inserting word into the Strie.
	 * 
	 * @param word
	 *            - Takes String as input and stores each letter as its node.
	 */
	public void insert(String word) {
		// inserts word into Strie.
		// word must contain characters between 'a' and 'z'. If not, throw
		// RuntimeException with message "Invalid character entered. Characters must be
		// between 'a' and 'z' "
		// O(n), where n is the number of characters in word

		if (!word.matches("[a-z]+")) { // "[a-z]+"
			throw new RuntimeException("Invalid character entered. Characters must be between 'a' and 'z' ");
		} else {
			char[] chArray = word.toCharArray();

			StrieNode node = root;
			for (int i = 0; i < chArray.length; i++) {

				if (node.getChild(chArray[i]) == null) {
					node.putChild(chArray[i], new StrieNode());

				}
				node = node.getChild(chArray[i]);

			}
			node.setEnd();

		}

	}

	/**
	 * This method checks if Strie contains word that has been passed through
	 * parameter in form of string.
	 * 
	 * @param word
	 *            - Takes String as input.
	 * @return true if the word parameter exits otherwise false.
	 */
	public boolean contains(String word) {
		// returns true if Strie contains word
		// word must contain characters between 'a' and 'z', otherwise, returns false.
		// O(n), where n is the number of characters in word

		if (!word.matches("[a-z]+")) {
			return false;
		} else {
			StrieNode node = root;
			char[] chArray = word.toCharArray();

			for (int i = 0; i < chArray.length; i++) {

				if (node.getChild(chArray[i]) == null)
					return false;

				if (node.isRemoved() && chArray[i] != chArray[chArray.length - 1])
					return false;

				// Otherwise, Iterate to the next node
				node = node.getChild(chArray[i]);

			}
			return (node.isEnd());

		}
	}

	/**
	 * This method removes word from Strie class. Checks if the word exists between
	 * 'a' and 'z' and does the operation. The recursion has been used in helper
	 * method.
	 * 
	 * @param word
	 *            - Takes String input.
	 * @return true if removed otherwise false.
	 */
	public boolean remove(String word) {
		// removes word from Strie
		// word must contain characters between 'a' and 'z', otherwise, returns false.
		// consider using recursion, might be helpful for this method

		if (!word.matches("[a-z]+")) {
			return false;
		} else {
			// char[] chArray = word.toCharArray();
			StrieNode node = root;
			// StrieNode[] all_child = node.getAllChildren();
			// if( all_child[chArray[0]-79] == null)
			if (!contains(word))
				return false;

			return removechild(word, node);
		}
	}

	/**
	 * This is helper method for removing a node.
	 * 
	 * @param word
	 *            - Takes in String input.
	 * @param node
	 *            - Takes a node input.
	 * @return true if the node is removed else false.
	 */
	private boolean removechild(String word, StrieNode node) {
		StrieNode root = node;
		char[] chArray = word.toCharArray();
		for (int i = 0; i < chArray.length; i++) {
			root = node;
			for (int j = 0; j < chArray.length - i; j++) {
				if (root.getChild(chArray[j]).isEnd() && i == 0 && root.getChild(chArray[j]).getNumChildren() == 0) {
					root.putChild(chArray[j], null);
					break;
				}
				if (root.getChild(chArray[j]).getNumChildren() == 0 && !root.getChild(chArray[j]).isEnd()) {
					root.putChild(chArray[j], null);
					break;
				}
				if (root.getChild(chArray[j]).isEnd() && i == chArray.length - 1
						&& root.getChild(chArray[j]).getNumChildren() != 0) {
					root.getChild(chArray[j]).unsetEnd();
					return true;
				}
				root = root.getChild(chArray[j]);
			}

		}
		return true;

	}

	/**
	 * This method does the BFS of the n-ary tree.
	 * 
	 * @param myStrie
	 *            - Takes an myStrie and traverse.
	 * @return the string representation of the BFS.
	 */
	public static String levelOrderTraversal(Strie myStrie) {
		// Do a Breadth First Traversal of myStrie
		// return the string built during traversal

		StrieNode root = myStrie.root;
		StringBuilder charList = new StringBuilder();
		charList.delete(0, charList.length());
		charList = levelOrderTraversalChild(root, charList, 0);
		java.util.ArrayList<java.util.ArrayList<Character>> charList2 = new java.util.ArrayList<java.util.ArrayList<Character>>();
		charList2.add(new java.util.ArrayList<Character>());
		for (int i = 0; i < charList.length(); i++) {
			if (Character.isDigit(charList.charAt(i))
					&& Character.getNumericValue(charList.charAt(i)) < charList2.size()) {
				charList2.get(Character.getNumericValue(charList.charAt(i))).add(charList.charAt(++i));
			}
			if (Character.isDigit(charList.charAt(i))
					&& Character.getNumericValue(charList.charAt(i)) >= charList2.size()) {
				charList2.add(new java.util.ArrayList<Character>());
				charList2.get(Character.getNumericValue(charList.charAt(i))).add(charList.charAt(++i));
			}
		}
		String s = "";
		for (int i = 0; i < charList2.size(); i++)
			for (int j = 0; j < charList2.get(i).size(); j++)
				s += " " + charList2.get(i).get(j).toString();

		return s;
	}

	/**
	 * This is a helper method for levelOrderTraversal method. This method continues
	 * to traverse the current node and return string
	 * 
	 * @param strieNode
	 *            Takes StrieNode and traverse recursively.
	 * @param charList
	 *            Takes stringBuilder type as input and to be inserted in
	 *            StringBuilder.
	 * @param level
	 *            - level to traverse.
	 * @return the stringBuilder type after being recursively called.
	 */
	private static StringBuilder levelOrderTraversalChild(StrieNode strieNode, StringBuilder charList, int level) {

		if (strieNode != null && !strieNode.isEmptyNode() && strieNode.getAllChildren() != null
				&& strieNode.getNumChildren() != 0 && strieNode.isRemoved() != true) {
			StrieNode[] strieChildren = strieNode.getAllChildren();
			for (int i = 0; i < strieChildren.length; i++) {
				if (strieChildren[i] != null) {
					char ch = 0;
					ch += i + 97;
					charList.append(level);
					charList.append(ch);
				}
			}

			// contine with current node
			//
			level++;
			for (int i = 0; i < strieChildren.length; i++) {
				if (strieChildren[i] != null && !strieChildren[i].isEmptyNode())
					levelOrderTraversalChild(strieChildren[i], charList, level);
			}

		}

		return charList;
	}

	/**
	 * This method returns words currently stored in myStrie class. It also sorts
	 * the word and returns in an order. It has taken getStrieWordsChild() method as
	 * a helper method.
	 * 
	 * @param myStrie
	 *            - takes in myStrie and traverse it.
	 * @return the sorted and ordered words currently stored in myStrie.
	 */
	public static String[] getStrieWords(Strie myStrie) {
		// returns all words currently stored in myStrie
		// if myStrie is empty, throw RuntimeException with message "Strie is empty"
		// returns words in alphabetical order
		// Example: myStrie contains: bat, cat, bar, barn. Returns: [bar, barn, bat,
		// cat]
		// recursion might be helpful for this method
		if (isEmptyStrie(myStrie)) {
			System.out.println("Strie is empty");
			return null;
		}
		StrieNode root = myStrie.root;
		StringBuffer charList = new StringBuffer();
		StringBuffer words = new StringBuffer();
		words.delete(0, words.length());
		charList.delete(0, words.length());
		words = getStrieWordsChild(root, charList, words);
		String word = words.toString();
		// splits the string based on whitespace
		String all_words[] = word.split("\\s");
		// sort string
		for (int i = 0; i < all_words.length; i++) {
			for (int j = all_words.length - 1; j > i; j--) {
				if (all_words[i].compareTo(all_words[j]) > 0) {

					String tmp = all_words[i];
					all_words[i] = all_words[j];
					all_words[j] = tmp;

				}

			}

		}

		return all_words;

	}

	/**
	 * This is a helper method of getStrieWords() method. It takes in the child node
	 * of Strie.
	 * 
	 * @param strieNode
	 *            - takes in StrieNode and traverse recursively.
	 * @param charList
	 *            - Buffer character.
	 * @param words
	 *            - Buffer String.
	 * @return the buffer string.
	 */
	private static StringBuffer getStrieWordsChild(StrieNode strieNode, StringBuffer charList, StringBuffer words) {

		if (strieNode != null && !strieNode.isEmptyNode() && strieNode.getAllChildren() != null) {
			StrieNode[] strieChildren = strieNode.getAllChildren();
			for (int i = 0; i < strieChildren.length; i++) {
				if (strieChildren[i] != null) {
					char ch = 0;
					ch += i + 97;
					charList.append(ch);
					if (strieChildren[i].isEnd() && !strieChildren[i].isRemoved()) {
						//
						words.append(charList);// + ";";
						// add whitespace between each word
						words.append(" ");

					}

					// continue with current node
					getStrieWordsChild(strieChildren[i], charList, words);

				}
			}

		}
		if (charList.length() > 0)
			charList.deleteCharAt(charList.length() - 1);
		return words;

	}

	/**
	 * suggest all words from your Strie for a given prefix.
	 * 
	 * @param myStrie
	 *            - Takes in myStrie and traverse.
	 * @param query
	 *            - String query.
	 * @return the array of string.
	 */
	public static String[] getAllSuffixes(Strie myStrie, String query) {
		// returns from myStrie all words with prefix query
		// query must contain characters between 'a' and 'z'
		// otherwise, throw RuntimeException with message "Invalid character entered.
		// Characters must be between 'a' and 'z' "
		// if no word is found in myStrie for the given prefix query, throw
		// RuntimeException with message "No suffixes found with the given prefix"
		// example: Your Strie stores these words: tea, ted, teen, teeth, team, med
		// query: tee
		// returns: teen, teeth
		// returns words in alphabetical order

		return null;
	}

	/**
	 * This method gets from Strie the longest prefix for a given query.
	 * 
	 * @param myStrie
	 *            - Takes in myStrie and traverse in the method.
	 * @param query
	 *            - Takes in String and finds the longest prefix stored in myStrie.
	 * @return returns the longest prefix stored in myStrie.
	 */
	public static String getLongestPrefix(Strie myStrie, String query) {
		// Given query, returns the longest prefix stored in myStrie
		// If no prefix can be found, throw RuntimeException with message "No prefix
		// found!"
		// query must contain characters between 'a' and 'z'
		// otherwise, throw RuntimeException with message "Invalid character entered.
		// Characters must be between 'a' and 'z' "
		// O(n), where n is the number of characters in query
		// Example: Your Strie stores these words: ban, band, banned, banana, can
		// query: bandana
		// returns: band

		return null;
	}

	/**
	 * This method gets from your Strie the closest match for a given word.
	 * 
	 * @param myStrie
	 *            - gets myStrie and traverse in the method. This is helpful for
	 *            traversing.
	 * @param query
	 *            - Takes in string input and finds the closed match in myStrie for
	 *            the query.
	 * @return Returns the closest match(es) found in myStrie for the given query.
	 */
	public static String[] getClosestMatch(Strie myStrie, String query) {
		// Returns the closest match(es) found in myStrie for the given query
		// query must contain characters between 'a' and 'z'
		// otherwise, throw RuntimeException with message "Invalid character entered.
		// Characters must be between 'a' and 'z' "
		// closest match rules: return the word(s) with the smallest distance between
		// word_in_myStrie and query
		// 1. length of query == length of word_in_myStrie
		// distance = charcter mismatches between query and word_in_myStrie at each
		// position (distance(barn, bird) = 2)
		// 2. length of query != length of word_in_myStrie
		// find substrings by moving a sliding window of length = smaller(query,
		// word_in_myStrie)
		// distance_substring = absolute_length_difference(word_in_myStrie, query) +
		// mismatch of characters at each position of substring and query
		// distance = smallest distance_substring
		// example: query: bann, word_in_myStrie = banned.
		// absolute_length_difference(word_in_myStrie, query) = 2
		// substrings: bann, anne, nned
		// distance_substring(bann, bann) = 2, distance_substring(bann, anne) = 5,
		// distance_substring(bann, nned) = 6
		// distance(bann, banned) = 2
		// return the words with minimum distance
		// if the minumim cost of the closest match >= length of query, throw
		// RuntimeException with message "Can't suggest a word! No close word found!"
		// Example: your Strie stores these words: barn, ban, banana, banned, bird
		// query: bann
		// returns: ban, barn
		// returns words in alphabetical order

		return null;
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This method is the main method which prints and checks all the methods of the
	 * Strie class. There are some test cases which are checked and prints "yay"
	 * accordingly.
	 * 
	 * @param args
	 *            - Takes in the array of strings.
	 */
	public static void main(String[] args) {

		Strie myStrie = new Strie();

		if (isEmptyStrie(myStrie) && myStrie.root.isEmptyNode())
			System.out.println("Yay 1");

		myStrie.insert("a");
		StrieNode[] children = myStrie.root.getAllChildren();

		if (!isEmptyStrie(myStrie) && children[0].isEmptyNode() && children[0].isEnd()
				&& myStrie.root.containsChar('a'))

			System.out.println("Yay 2");

		myStrie.insert("bat");
		myStrie.insert("bar");
		myStrie.insert("barn");
		myStrie.insert("cat");

		if (myStrie.contains("cat"))
			System.out.println("Yay 3");
		String res = Strie.levelOrderTraversal(myStrie).trim();
		String actualOut = "a b c a a r t t n";

		if (res.equals(actualOut))
			System.out.println("Yay 4");

		String[] yourWords = Strie.getStrieWords(myStrie);
		String[] allWords = { "a", "bar", "barn", "bat", "cat" };
		int allMatches = 1;
		for (int i = 0; i < yourWords.length; i++) {
			if (!yourWords[i].equals(allWords[i]))
				allMatches = 0;
		}
		if (allMatches == 1)
			System.out.println("Yay 5");

		if (myStrie.remove("cat") && !myStrie.contains("cat"))
			System.out.println("Yay 6");

	}

}