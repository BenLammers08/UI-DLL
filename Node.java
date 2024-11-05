/**
 * Node represents a node in a linked list.
 *
 * @author Java Foundations, mvail
 * @version 4.0
 */
public class Node<E> {
	private Node<E> nextElement;
	private E element;
	private E prevNode;


	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public Node(E elem) {
		nextElement = null;
		element = elem;
		prevNode= null;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<E> getNext() {
		return nextElement;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNext(Node<E> node) {
		nextElement = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public E getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(E elem) {
		element = elem;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (nextElement != null);
	}
}
