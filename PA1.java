import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
* The PA1 class is the main class (which is static) in the application. It contains the main method (which invokes the other 
* static methods) with the goal of reading the inputted file that was passed in using input redirection through the CLI. It
* goes through the file contents line-by-line and create Event objects that correspond to the data. It will then insert the 
* objects into a SortedEventList which will insert them in descending order. It will output the Event object data, placing
* medals for the first three places.
* @author Lance Baker (c3128034).
*/
public class PA1 {
	private static final String COMMA = ",";
	private static final String GOLD = "GOLD";
	private static final String SILVER = "SILVER";
	private static final String BRONZE = "BRONZE";
	private static final String EMPTY_STRING = "";
	
	/*
	* The sortEvents method receives a LinkedList<Event> object; it will first instantiate a new
	* SortedEventList object (being a subclass of LinkedList<Event>), and iterates foreach Event in
	* the received object. It will then insertInOrder the Event into the SortedEventList.
	* It will return the SortedEventList.
	*/
	private static SortedEventList sortEvents(LinkedList<Event> events) {
		SortedEventList sorted = new SortedEventList(); // Creates a new SortedEventList.
		for (Event event : events) { // Iterates foreach Event.
			sorted.insertInOrder(event); // Adds the Event into the new SortedEventList.
		}
		return sorted; // Returns the SortedEventList.
	}
	
	/*
	* The readEvents method will receive a BufferedReader which will have direct access to the file.
	* it will go through each line and create the corresponding Event objects, adding the instances
	* to the LinkedList<Event>, and will return it as a SortedEventList.
	*/
	private static SortedEventList fetchEvents(BufferedReader br) throws Exception {
		// Instantiates a new LinkedList<Event> object.
		LinkedList<Event> events = new LinkedList<Event>();
		// The first line should be an Integer value representing how many Events are in the file.
		int records = Integer.parseInt(br.readLine().trim());
		// Once the value is determined, it will then iterate until the records amount is reached.
		for (int i = 0; i < records; i++) {
			// It will split each read line on the comma.
			String[] values = br.readLine().trim().split(COMMA);
			// Instantiate a new Event passing in the athlete's name and country code.
			// It will then add the instance to the LinkedList<Event>.
			events.append(new Event(values[0], values[1]));
		}
		for (int i = 0; i < 6; i++) { // It will iterate 6 times for each attempt.
			for (Event event : events) { // It will iterate through each Event object in the LinkedList.
				// It will then convert the String value to a double, and add it to the event using the addAttempt method.
				event.addAttempt(Double.parseDouble(br.readLine().trim()));
			}
		}
		// Passes the LinkedList<Event> into the sortEvents method which will
		// instantiate a new SortedEventList and iterate through the events inserting each one in order.
		return sortEvents(events); // Returns the SortedEventList.
	}
	
	/*
	* The main method.
	*/
	public static void main(String[] args) {
		try {
			// Instantiates a InputStreamReader that connects to the file passed in by input redirection,
			// which is then thrown into a BufferedReader enabling the lines to be read. The BufferedReader
			// is then passed into the fetchEvents method, which will create Event objects based on the file contents
			// and return it as a SortedEventList.
			SortedEventList events = fetchEvents(new BufferedReader(new InputStreamReader(System.in), 1));
			int place = 1; // A place counter.
			// Iterates foreach Event object.
			// Since the LinkedList implements Iterable it can be used in an enchanced for.
			for (Event event : events) { 
				// It will output the event based on it's toString() with the place appended after.
				System.out.println(event + ((place == 1) ? GOLD : (place == 2) ? SILVER : (place == 3) ? BRONZE : EMPTY_STRING));
				place++; // Increments place.
			}
		} catch (Exception ex) {
			// Somethings wrong. It outputs the thrown exception message.
			System.out.println(ex.getMessage()); 
		}
	}

}