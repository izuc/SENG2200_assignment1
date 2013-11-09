/*
* The Node<E> is a generic class that represents each link in the LinkedList<E>. 
* It contains references to the previous, and next Node<E>. The object data is stored
* as a generic type. The class has the standard getters & setters which encapsulates the attributes.
* @author Lance Baker (c3128034).
*/

public class Node<E> {
	private Node<E> previous; // Reference to the previous node.
	private Node<E> next; // Reference to the next node.
	private E data; // The data object reference.
	
	/*
	* The default constructor just chains to the next constructor passing a null reference for the data.
	*/
	public Node() {
		this(null);
	}
	
	/*
	* The constructor accepts a E data object. It then chains to the next constructor passing
    * the received data object, with the previous & next Node<E> passed as null references.
	*/
	public Node(E data) {
		this(data, null, null);
	}
	
	/*
	* The constructor receives all three values. The E data object, the previous Node<E>, and the 
	* next Node<E>, are received and then assigned to the attributes.
	*/
	public Node(E data, Node<E> previous, Node<E> next) {
		this.data = data;
		this.previous = previous;
		this.next = next;
	}
	
	/*
	* The getPrevious method is a Getter for the previous node.
	*/
	public Node<E> getPrevious() {
		return this.previous;
	}
	
	/*
	* The setPrevious method is a Setter for the previous node.
	*/
	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}
	
	/*
	* The getNext method is a Getter for the next node.
	*/
	public Node<E> getNext() {
		return this.next;
	}
	
	/*
	* The setNext method is a Setter for the next node.
	*/
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/*
	* The getData method is a Getter for the stored object.
	*/
	public E getData() {
		return this.data;
	}
	
	/*
	* The setData method is a Setter for the stored object.
	*/
	public void setData(E data) {
		this.data = data;
	}
}