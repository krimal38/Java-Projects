import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Goblin class is an algorithm of goblin game. The program reads the file
 * system which contains words. The program prompts character user input. It
 * uses BetterArray as data structure.
 * 
 * @author kshitiz Rimal.
 */
class Goblin {
	private Scanner userIn;
	private String dictFileName;
	private int numLetters;
	private int numGuesses;
	// Global variables
	private BetterArray<String> current_dictionary;
	private BetterArray<Character> current_guesses;
	private BetterArray<Character> current_word;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 * Returns a copy of current dictionary.
	 * 
	 * @return the current dictionary.
	 */
	public BetterArray<String> getWords() {

		return current_dictionary.clone();
	}

	/**
	 * Returns the current guesses.
	 * 
	 * @return the current guesses that user has input and returns those letters.
	 */
	public BetterArray<Character> getGuesses() {

		return current_guesses.clone();

	}

	/**
	 * Return the letter user has guessed.
	 * 
	 * @return the word that user has guessed. All other spots will return null.
	 */
	public BetterArray<Character> getCurrentWord() {

		return current_word.clone();
	}

	/**
	 * Returns boolean depending on the check. Helper method to check if each word
	 * is null. If each word is null, return false else true.
	 * 
	 * @return boolean statement to check null places.
	 */
	private boolean wordGuessed() {

		for (int i = 0; i < current_word.capacity(); i++) {

			if (current_word.get(i) == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns the number of wrong guesses.
	 * 
	 * @return the number of wrong guesses.
	 */

	public int getGuessesRemaining() {

		return numGuesses;
	}

	/**
	 * Helper method to check repeated letter in a word. Takes O(n^2).
	 * 
	 * @param takes
	 *            each word to check if the word has repeating letter.
	 * @return true if no repeated letter else return false.
	 */
	private boolean HasDuplicate(String print) {
		for (int i = 0; i < print.length(); i++) {
			for (int j = i + 1; j < print.length(); j++) {
				if (print.charAt(i) == print.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method checks the repeated letter in a word. It also checks if the
	 * number of letters is at least 2. It takes in file system using File class and
	 * use while() loop to iterate through each word. Each word will then be send
	 * into HasDuplicate(print) method which checks repeating letters in a word.
	 * prints true if all the test cases pass otherwise false. The whole code is in
	 * try catch block to catch if the File is not found.
	 * 
	 * @return true if all test cases pass. False if the File cannot be found.
	 * 
	 */
	public boolean init() {

		current_dictionary = new BetterArray<>();
		current_guesses = new BetterArray<>(numGuesses);
		current_word = new BetterArray<>(numLetters);

		try {

			File file = new File(dictFileName);

			Scanner file_type = new Scanner(file);

			while (file_type.hasNextLine()) {
				String print = file_type.next();
				boolean duplicateElements = HasDuplicate(print);

				if (numLetters >= 2) {

					if (print.length() == numLetters && duplicateElements == true) {
						current_dictionary.append(print);
					}
				}

			}

			if (getWords().size() == 0) {
				System.out.println("Goblin can't find enough words! You win!");
				return false;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Goblin lost his dictionary! You win!");
			return false;
		}

		/*
		 * just to check if currentWord() is working fine. For now, it will just add
		 * null, but later once it get real values, null will be replaced with those
		 * values.
		 */
		for (int i = 0; i < numLetters; i++) {
			current_word.add(i, null);
		}

		userIn = new Scanner(System.in); // Initilize new Scanner
		return true;

	}

	/**
	 * This method partition Goblin's dictionary according to "guess". It creates
	 * X+1 slots where X is the number of letter positions and +1 is for the words
	 * where the letter doesn't occur
	 * 
	 * @param guess
	 *            takes the input that user has guessed.
	 * @return the index the position of that letter. If the goblin picks a
	 *         partition without the chosen letter, return -1.
	 * 
	 */
	public int bestPartition(char guess) {

		BetterArray<BetterArray<String>> partitionArray = new BetterArray<>(numLetters + 1);

		for (int i = 0; i < (numLetters + 1); i++) {
			partitionArray.append(new BetterArray<>());
		} // now we have all the data inside this array.

		for (int i = 0; i < getWords().size(); i++) {

			String word = getWords().get(i);
			partitionArray.get(word.indexOf(guess) + 1).append(word);
		}

		// Picks the biggest partition as the new dictionary
		int maxSize = 0, index = 0;
		for (int i = 0; i < partitionArray.size(); i++) {

			if (maxSize < partitionArray.get(i).size()) {
				maxSize = partitionArray.get(i).size();
				index = i;

			}
		}
		// set the biggest partition as new dictionary
		current_dictionary = partitionArray.get(index).clone();

		return index > 0 ? (index - 1) : -1; // it should not be the index

	}

	/**
	 * This method takes user input. Check that the guess is valid (one letter which
	 * has not been guessed before). Checks all valid cases and if true, partition
	 * the goblin's dictionary and choose a new set of words
	 * 
	 * @return boolean statement. true if the game should continue, or false if the
	 *         game is over. Returns with appropriate messages.
	 */
	public boolean step() {

		if (getGuessesRemaining() == 0) {
			return false;
		} else if (wordGuessed() == true) {
			return true;
		}

		String InputGuess = null;

		System.out.print("Goblin says \"Guess a letter\": ");

		boolean flag = true;

		while (flag) {
			InputGuess = userIn.nextLine();

			if (!InputGuess.isEmpty() && InputGuess.matches("^[a-z0-9A-Z]{1}$")) {
				char GuessFromUser = InputGuess.charAt(0);
				// check to make sure character are not repeating
				if (getGuesses().firstIndexOf(GuessFromUser) < 0) {

					current_guesses.append(GuessFromUser);
					int partiton = bestPartition(GuessFromUser);

					if (partiton < 0) {
						numGuesses = numGuesses - 1;

						System.out.print(
								"Goblin says \"No dice! " + getGuessesRemaining() + " wrong guesses left...\"\n");

						if (numGuesses == 0)
							return false;

					} else { // unique chars
						current_word.replace(partiton, GuessFromUser);
						System.out.print("Goblin says \"Yeah, yeah, it's like this: "
								+ getCurrentWord().toString().replaceAll("null", "-").replaceAll("[ ,]", "") + "\"\n");
					}
					flag = false;
				} else { // already guesses the char
					System.out.print("Goblin says \"You already guessed: " + getGuesses().toString()
							+ "\nGuess a new letter\": ");
				}

			} else {
				System.out.print("Goblin says \"One letter! Guess a single letter\": ");

			}
		}
		if (wordGuessed())
			return false;
		return true;

	}

	/**
	 * This method is a void method. This will be called after step() returns false.
	 * It will print win/lose messages to the user
	 */
	public void finish() {

		if (step() == false)
			System.out.println("Goblin says \"I win! I was thinking of the word '" + getWords().get(0) + "'"
					+ "\nYour stuff is all mine... I'll come back for more soon!\"");
		else
			System.out.println("Goblin says \"You win... here's your stuff. But I'll get you next time!\"");
	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	public Goblin(String dictFileName, int numLetters, int numGuesses) {
		this.userIn = new Scanner(System.in);
		this.dictFileName = dictFileName;
		this.numLetters = numLetters;
		this.numGuesses = numGuesses;
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	public static void main(String[] args) {
		// if you don't have the mini dictionary one folder above your
		// user folder, this won't work!

		/*
		 * Sample successful run with output: > java Goblin Goblin can't find enough
		 * words! You win! Goblin lost his dictionary! You win! Yay 1 Yay 2 Yay 3 Yay 4
		 * Yay 5
		 */

		Goblin g1 = new Goblin("../dictionary-mini.txt", 3, 10);
		Goblin g2 = new Goblin("../dictionary-mini.txt", 6, 6);
		Goblin g3 = new Goblin("../dictionary.txt", 1, 6);
		Goblin g4 = new Goblin("banana.txt", 3, 3);

		if (g1.init() && g2.init() && !g3.init() && !g4.init()) {
			System.out.println("Yay 1");
		}

		if (g1.getGuessesRemaining() == 10 && g1.getWords().size() == 1 && g2.getGuessesRemaining() == 6
				&& g2.getWords().size() == 5 && g1.getGuesses().size() == 0 && g2.getCurrentWord().size() == 6) {
			System.out.println("Yay 2");
		}

		BetterArray<Character> g1word = g1.getCurrentWord();
		if (g1word.get(0) == null && g1word.get(1) == null && g1word.get(2) == null) {
			System.out.println("Yay 3");
		}

		// remember what == does on objects... not the same as .equals()
		if (g1.getWords() != g1.getWords() && g1.getGuesses() != g1.getGuesses()
				&& g1.getCurrentWord() != g1.getCurrentWord()) {
			System.out.println("Yay 4");
		}

		if (g2.bestPartition('l') == -1 && g2.getWords().size() == 3 && g2.bestPartition('p') == 0
				&& g2.getWords().size() == 2 && g2.bestPartition('n') == -1 && g2.getWords().size() == 1) {
			System.out.println("Yay 5");
		}

		// can't test step() or finish() this way... requires user input!
		// maybe you need to test another way...
	}

}