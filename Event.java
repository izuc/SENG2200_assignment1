import java.lang.Comparable;
/*
* The Event class implements the Comparable<Event> interface in order to have the compareTo method implemented.
* Each Event stores the athlete's name, their country code, and the attempts that they have done. The attempts
* are stored as a two-dimensional array that has the attempt distance and also the attempt number. It has a method
* to fetch the attempts array sorted into descending order (which is a copy), and also a method to add a attempt
* to the next available slot. The toString method will return a String representation of the object, containing
* a single line consisting of their name, country, and attempts.
* The compareTo implementation compares two Event objects, which both fetch the sorted
* attempts array, and it then iterates through the array comparing each attempt (which is their best attempt to their worst)
* until one is found to be better than the other. If both have matching best attempts, it will then iterate through
* the arrays comparing when the attempt was done until a Event is found to be better than the other.
* @author Lance Baker (c3128034)
*/
public class Event implements Comparable<Event> {
	private static final int MAX_ATTEMPTS = 6;
	private static final int MAX_ELEMENTS = 2;
	private static final String SPACE = " ";
	
	private String name; // The athlete's name.
	private String country; // The country of the athlete.
	private double[][] attempts; // The attempts store both the distance and when the attempt was made.
	
	public Event(String name, String country) {
		this.name = name;
		this.country = country;
		this.attempts = new double[MAX_ATTEMPTS][MAX_ELEMENTS];
		this.clearAttempts();
	}
	
	/*
	* The clearAttempts method iterates throughout each element in the array, setting the distance
	* to be -1, and the position to be the index.
	*/
	public void clearAttempts() {
		for (int i = 0; i < this.attempts.length; i++) {
			this.attempts[i][0] = -1;
			this.attempts[i][1] = i;
		}
	}
	
	/*
	* The getter used to retrieve the athlete's name.
	*/
	public String getName() {
		return this.name;
	}
	
	/*
	* The setter used to set the athlete's name.
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	* The getter used to retrieve the country code.
	*/
	public String getCountry() {
		return this.country;
	}
	
	/*
	* The setter used to set the country code.
	*/
	public void setCountry(String country) {
		this.country = country;
	}
	
	/*
	* The getAttempts() method returns a sorted cloned array of attempts in descending order.
	* It performs a Bubble Sort, which basically traverses the array, and then it will be compare each element 
	* until array length-1. The element is then swapped with the next element if the next element is smaller.
	*/
	public double[][] getSortedAttempts() {
		// Clones the array, so the following won't change the actual attempts.
		double[][] attempts = this.attempts.clone();
		// Performs a Bubble Sort. Iterates throughout each element in the array.
		for (int i = 0; i < attempts.length; i++) {
			// Another iteration which will then iterate for each element after the 
			// current one. Which will then compare the current one with the element,
			// and if the current element is less than the element then it will swap
			// the values.
			for (int index = 1; index < (attempts.length - i); index++) {
				// Compares the distance of the attempt. If the current element being (index-1) is
				// less than the other element, it will effectively swap them.
				if (attempts[index-1][0] < attempts[index][0]){
					// Stores the current element in a temp.
					double[] temp = attempts[index-1];
					// Sets the current element to the other element.
					attempts[index-1] = attempts[index];
					// Then sets the other element to the temp.
					attempts[index] = temp;
				}
			}
		}
		return attempts; // Returns the sorted attempts.
	}
	
	/*
	* A getter used to fetch the (unsorted) attempts.
	*/
	public double[][] getAttempts() {
		return this.attempts;
	}
	
	/*
	* The addAttempt method receives the distance, and it will traverse the array until a not entered
	* value is found (which is -1); it will then set that to be the distance.
	*/
	public boolean addAttempt(double distance) {
		// Iterates for each attempt in the array.
		for (int i = 0; i < this.attempts.length; i++) {
			// If the distance is not set.
			if (this.attempts[i][0] == -1) {
				// It will add the distance to that element.
				this.attempts[i][0] = distance;
				// Returns true indicating add was successful.
				return true; 
			}
		}
		// Returns false indicating the array is full.
		return false;
	}
	
	/*
	* The toString() method will return a String representing an Event containing:
	* The athlete name being blank filled (or truncated) to 15 characters, 
	* followed by a space and then the 3 letter country code, 
	* followed by the 6 values, with each printed in a 7 characters wide field with 2 decimal places.
	*/
	public String toString() {
		// A StringBuilder is used to gather the output.
		StringBuilder builder = new StringBuilder();
		char[] name = this.name.toCharArray(); // Converts the name to the char array.
		for (int i = 0; i < 15; i++) { // Iterates until 15.
			// If it's not out of bounds, it appends the char element. 
			// Otherwise appends a space.
			builder.append((i < name.length) ? name[i] : SPACE);
		}
		builder.append(SPACE);
		builder.append(this.country); // Appends the country code.
		builder.append(SPACE);
		// Iterates through each element in the attempts array.
		for (int i = 0; i < this.attempts.length; i++) {
			// Formats the attempt to the correct format, and appends it to the output.
			builder.append(String.format("%7.2f", this.attempts[i][0]));
			builder.append(SPACE);
		}
		return builder.toString(); // Returns the built String.
	}
	
	/*
	* The compareTo method compares the current event object with the received event object. It first fetches
	* the sorted attempts (by distance in decending) from both objects and placed it in their own array. 
	* The array is then iterated, comparing each the current element's distance of both arrays together; it will
	* return a value indicating which object is better. If both are considered equal, then the position for each
	* attempt is compared. If the attempts are both identical, then it will return 0 indicating that the objects are equal.
	* @return -1 if this object is better, 1 if the param is better, 0 if equal.
	*/
	public int compareTo(Event event) {
		// Fetches the sorted attempts for both objects.
		double[][] attempts = this.getSortedAttempts();
		double[][] otherAttempts = event.getSortedAttempts();
		// Compares their attempts with the best distance. It will iterate
		// for each index until the MAX_ATTEMPTS is reached.
		for (int i = 0; i < MAX_ATTEMPTS;) { // Iterates for each attempt.
			// If the object's attempt is greater than the param's attempt.
			if (attempts[i][0] > otherAttempts[i][0]) {
				return -1; // It will return -1 indicating the object is better.
			// If the object's attempt is greater than the param's attempt.
			} else if (otherAttempts[i][0] > attempts[i][0]) {
				return 1; // It will return 1 indicating the param is better.
			} else {
				i++; // Only increments to the next if considered equal.
			}
		}
		// If the attempts match, then it will be compared based on when they've done the attempt.
		// It will iterate for each index until the MAX_ATTEMPTS is reached.
		for (int i = 0; i < MAX_ATTEMPTS;) {
			// If the position of the object's attempt is less than the position of the param's attempt.
			if (attempts[i][1] < otherAttempts[i][1]) {
				return -1; // Then the object is better, since it is the position which was done earlier.
			// If the position of the param's attempt is less than the position of the objects's attempt.
			} else if (otherAttempts[i][1] < attempts[i][1]) {
				return 1; // Then the param is better, since it is the position which was done earlier.
			} else {
				i++; // Otherwise, it will compare the next attempt's positions.
			}
		}
		return 0; // If they still match, it will then return 0.
	}
}