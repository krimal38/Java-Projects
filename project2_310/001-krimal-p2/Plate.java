// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------
/**
 * This class takes in the number input from Bin, Air and Spinner class and
 * changes to ASCII character.
 * 
 * @author kshitiz Rimal
 *
 */
public class Plate {
	/**
	 * Instance variable with type integer.
	 */
	private int number;

	/**
	 * This is a constructor.
	 * 
	 * @param number
	 *            - Takes integer number and set to private integer number.
	 */
	public Plate(int number) {
		this.number = number;
	}

	/**
	 * This changes the number given by user to ASCII character.
	 * 
	 * @return return the ascii character.
	 */
	public String toString() {
		return "(" + ((char) (this.number + 96)) + ")";
	}

	/**
	 * This method returns the number asked from user.
	 * 
	 * @return Return integer type number asked by user.
	 */
	public int getNumber() {
		return this.number;
	}
}
