/*
* The SortedEventList is an extension of the LinkedList<Event>. It incorporates a new method
* which enables the data to be inserted in the list within order. The order depending on the compareTo method
* of the Event type.
* @author Lance Baker (c3128034).
*/
public class SortedEventList extends LinkedList<Event> {
	/*
	* The insertInOrder method receives an Event object, and will place it in the LinkedList<Event>
	* in a sorted position. It will first declare a Node "position" pointer to reference the head sentinel, and it will
	* iterate the entire list comparing the received object with the node data. If the node data is found to be higher
	* or equal to the received Event, then it will update the position reference to that node, continuing the iterations
	* until the last higher node is found. It will then just insert the data directly after that node.
	*/
	public void insertInOrder(Event event) {
		// Sets the position to the head sentinel node.
		Node<Event> position = this.getHead();
		// Iterates the entire list.
		for (Node<Event> node = this.getHead().getNext(); node != this.getTail(); node = node.getNext()) {
			// Compares each node in the list with the event.
			int compare = event.compareTo(node.getData());
			if (compare >= 0) {
				// If a node in the list is found to be higher than 
				// the event, or if the objects are identical. 
				// The position is updated to that node.
				position = node;
			}
		}
		// Inserts the event to the list after the position.
		this.insert(position, event);
	}

}