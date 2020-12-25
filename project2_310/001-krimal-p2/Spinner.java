/**
 * This class is a nested class. Spinner class has another class name Hand.
 * Spinner class works with single element. It spins, pass the plates with the
 * help of Hand class.
 * 
 * @author kshitiz Rimal
 *
 */
public class Spinner {
	// you may NOT add any additional instance variables or methods
	// to the Spinner, all needed instance variables are created in
	// the DO NOT EDIT section

	/**
	 * This class is inside the Spinner class. This class checks if the plates are
	 * being tossed or passed properly. All the methods of this class performs the
	 * check for the Spinner class.
	 * 
	 * @author kshitiz Rimal
	 *
	 */
	private static class Hand {
		// add any additional instance variables here
		// no additional methods (not even private ones)
		/**
		 * Instance of the Plate class.
		 */
		private Plate myPlate;

		/**
		 * This method checks if the plate (element) is being processed properly and the
		 * Syeda is not catching the null plate. It throws error if the plate is full
		 * and it calls catch method. It throws error if trying to catch a null element.
		 * 
		 * @param plate
		 *            - takes in the Object from the user.
		 */
		public void catchPlate(Plate plate) {
			// if trying to catch a plate when full, throw a RuntimeException
			// with the message "Catching hand not empty"

			// if trying to catch a null plate, throw a RuntimeException
			// with the message "Can't catch a plate that doesn't exist!"

			// otherwise keep the plate in this hand
			if (hasPlate()) {
				throw new RuntimeException("Catching hand not empty");
			} else if (plate == null)
				throw new RuntimeException("Can't catch a plate that doesn't exist!");

			else {
				myPlate = plate;
			}

		}

		/**
		 * This method return the plate that has been removed. Throws error if Syeda
		 * hand is empty.
		 * 
		 * @return the Plate removed.
		 */
		public Plate tossPlate() {
			// if trying to throw a plate when this hand does not have
			// a plate, throw a RuntimeException with the message
			// "Tossing hand was empty"

			// otherwise remove the plate from this hand
			// and return the plate you removed
			if (myPlate == null)
				throw new RuntimeException("Tossing hand was empty");
			else {
				Plate temp = myPlate;
				myPlate = null;
				return temp;
			}
		}

		/**
		 * This method checks if there is plate or not.
		 * 
		 * @return true if you have a plate, false otherwise.
		 */
		public boolean hasPlate() {
			// return true if you have a plate, false otherwise
			if (myPlate != null)
				return true;

			return false;

		}

		/**
		 * This is a toString method which checks of hand is empty or not.
		 * 
		 * @return the string with three spaces if hand is empty otherwise return the
		 *         plate's string value.
		 */
		public String toString() {
			// if this hand is empty, return the string " " (three spaces)
			// otherwise return the plate's string value

			if (hasPlate())
				return myPlate.toString();

			return "   ";

		}
	}

	/**
	 * This puts a plate from hand 2 and pass it to hand 1.
	 */
	public void passPlate() {
		// put a plate from hand 2 and pass it to hand 1
		// this can be done with a single line of code
		hands[0].catchPlate(hands[1].tossPlate());
	}

	/**
	 * This put a plate from hand 1 and put it in the bin.
	 */
	public void putDownPlate() {
		// put a plate from hand 1 and put it in the bin
		// this can be done with a single line of code
		bin.push(hands[0].tossPlate());
	}

	/**
	 * It take a plate out of the bin and put it in hand 1. If there are no plates
	 * in the bin, throws exception.
	 */
	public void pickUpPlate() {
		// take a plate out of the bin and put it in hand 1

		// if there are no plates in the bin, throw a RuntimeException
		// with the message "Can't pickup a plate that doesn't exist!"

		if (bin.isEmpty())
			throw new RuntimeException("Can't pickup a plate that doesn't exist!");

		else {
			hands[0].catchPlate(bin.pop());
		}
	}

	/**
	 * Takes a plate from hand 1 and put it in the air. Checks if the air can hold
	 * more than 13 items or not and return the exception.
	 */
	public void spinPlate() {
		// take a plate from hand 1 and put it in the air

		// if the air can't hold anymore plates, throw a RuntimeException
		// with the message "Too many plates in the air!"
		if (air.size() < 13) {
			air.enqueue(hands[0].tossPlate());

		} else {
			throw new RuntimeException("Too many plates in the air!");
		}
	}

	/**
	 * Takes a plate out of the air and put it in hand 2. Checks if there are no
	 * plates in the air throws exception.
	 */
	public void catchPlate() {
		// take a plate out of the air and put it in hand 2

		// if there are no plates in the air, throw a RuntimeException
		// with the message "Can't catch a plate that doesn't exist!"

		if (air.isEmpty())
			throw new RuntimeException("Can't catch a plate that doesn't exist!");

		hands[1].catchPlate(air.dequeue());

	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * This is the main method that runs this class.
	 * 
	 * @param args
	 *            - Takes in the array of string.
	 */
	public static void main(String[] args) {
		Spinner kshitiz = new Spinner(5);
		kshitiz.pickUpPlate();
		System.out.println(kshitiz.toString());
		kshitiz.spinPlate();
		System.out.println(kshitiz.toString());
		kshitiz.catchPlate();
		System.out.println(kshitiz.toString());
		kshitiz.passPlate();
		System.out.println(kshitiz.toString());
		kshitiz.spinPlate();
		System.out.println(kshitiz.toString());

	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	/**
	 * Instance variable for air class.
	 */
	private final Air air = new Air();
	/**
	 * Instance variable for bin class.
	 */
	private final Bin bin = new Bin();
	/**
	 * Instance variable for the Hand class.
	 */
	private final Hand[] hands = new Hand[2];

	// spinners have two hands and starts with a bin full of plates
	/**
	 * The constructor.
	 * 
	 * @param totalPlates
	 *            - Takes in the number of plates.
	 */
	public Spinner(int totalPlates) {
		hands[0] = new Hand();
		hands[1] = new Hand();

		for (int i = totalPlates; i > 0; i--) {
			this.bin.push(new Plate(i));
		}
	}

	// this does the beautiful ascii art :)
	/**
	 * This method does the ascii art for Syeda.
	 * 
	 * @return the string that makes Syeda.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		String[] personParts = { "   " + air.toString() + "\n", "\n",
				"    " + hands[0].toString() + "( )" + hands[1].toString() + "\n", "     \\__|__/\n", "        |\n",
				"        |\n", "       / \\\n", "      /   \\\n" };

		String[] stackParts = this.bin.toString().split("[|]");

		int total = (this.bin.size() < personParts.length) ? personParts.length : this.bin.size();
		for (int i = total; i >= 0; i--) {
			sb.append((this.bin.size() - 1 < i) ? "   " : stackParts[stackParts.length - i - 1]);
			if (i < personParts.length) {
				sb.append(personParts[personParts.length - i - 1]);
			} else {
				sb.append("\n");
			}
		}

		return sb.toString();
	}
}
