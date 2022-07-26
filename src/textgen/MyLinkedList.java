package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E> (null);
		tail = new LLNode<E> (null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException();
		}
		else {
			LLNode<E> n = new LLNode<E>(element);
			n.prev = tail.prev;
			n.prev.next = n;
			n.next = tail;
			tail.prev = n;
			size ++;
			
		}
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		LLNode<E> cur = head;
		
		if(size >= index + 1 && index >= 0) {
			
			for (int i = 0; i <= index; i++) {
				cur = cur.next;
			}
			
		}
		else {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		return cur.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException();
		}
		else if (index < 0 || (index + 1 > size && size != 0 || (index > 0 && size == 0))){
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		else {
			LLNode<E> n = new LLNode<E>(element);
			LLNode<E> cur = head;
			
			for (int i = 0; i <= index; i++) {
				cur = cur.next;
			}
			n.prev = cur.prev;
			n.prev.next = n;
			n.next = cur;
			cur.prev = n;
			size ++;
			
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> cur = head;
		
		if(size >= index + 1 && index >= 0) {
			
			for (int i = 0; i <= index; i++) {
				cur = cur.next;
			}
			
		}
		else {
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		cur.next.prev = cur.prev;
		cur.prev.next = cur.next;
		size--;
		return cur.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException();
		}
		else if (index < 0 || index + 1 > size){
			throw new IndexOutOfBoundsException("Check out of bounds");
		}
		else {
			
			LLNode<E> cur = head;
			E oldE;
			for (int i = 0; i <= index; i++) {
				cur = cur.next;
			}
			oldE = cur.data;
			cur.data = element;
			
			return oldE;
		}
		
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;


	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
