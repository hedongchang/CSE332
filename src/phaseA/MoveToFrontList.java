/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */
package phaseA;

import providedCode.*;



/**
 * This program can create a linked list that can keep move item to the 
 * front of the list and increase its count by one when the item was called. 
 * A new item with a count of 1 will be added to the front of the list. 
 * Whenever an existing item was retrieved, we move it to the front of the list. 
 */
public class MoveToFrontList<E> extends DataCounter<E> {
	//The pointer that point to the most front node in this list.
	private ListNode front;
	//The comparator that can compare the value of different nodes.
	private Comparator<? super E> comparator;
	
	private int size;
	
	/**A class to construct the node of the list.*/
	private class ListNode {
		E data;
		int count;
		ListNode next;
		
		/** Constructs a new node with given count number as its value and makes it points to null.
		 * @param Count of the number of nodes.
		 * @effects Constructs a new list node, store its count number as its data.
		 **/
		public ListNode(E count) {
			this.data = count;
			this.count = 1;
			this.next = null;
		}
	}
	
	/** Constructs a new list with given comparator and makes it points to null. 
	 * @param A comparator that can compare the value of different nodes.
	 * @effects Constructs a new MoveToFrontList. 
	 **/
	public MoveToFrontList(Comparator<? super E> c) {
		this.comparator = c;
		this.front = null;
		this.size = 0;
	}
	
	/** Increases the count number of one existing node with the given value and move it to
	 *  the front of the list; Add new node with the given value to the front if it is previously
	 *  not in this list.
	 * @param A data that indicates the value of a node.
	 * @effects Find the node with this data in the MoveToFrontList,
	 *          and move it from the original position to the front.
	 *          If the list is empty, add a new node with this data.
	 *          If there is no this node, add a new node with this data to the front.
	 **/
	@Override
	public void incCount(E data) {
		if (front == null) {
           this.front = new ListNode(data);
           size++;
		} else {
			if (comparator.compare(data, front.data) == 0) {
				front.count++;
				return;
			}
			ListNode current = front;
			while (current.next != null) {
				int com = comparator.compare(data, current.next.data);
				if (com == 0) {
					ListNode newFront = current.next;
					current.next=current.next.next;
					newFront.next = front;
					front = newFront;
					front.count++;
					return;
				}
				current=current.next;
			}
			ListNode newFront = new ListNode(data);
			newFront.next = front;
			front = newFront;
			size++;
		}
	}
    
	/** Get the size of the current list.
	 * @return The size of the MoveToFrontList 
	 **/
	@Override
	public int getSize() {
		return this.size;
	}
	
	/** Get the count number of the node with the given value.
	 * @param data: value of the node 
	 * @requires this.front does not equal to null
	 * @effect Move the node with the data to the front.
	 * @return The count number of the node with the data
	 **/
	@Override
	public int getCount(E data) {
		if (front != null) {
			ListNode current = front;
			if (comparator.compare(front.data, data) == 0) {
				return front.count;
			}
			while (current.next != null) {
				if (comparator.compare(current.next.data, data) == 0) {
					ListNode newFront = current.next;
					current.next = current.next.next;
					// delete the node
					newFront.next = front;
					front = newFront;
					return front.count;
				}
				current = current.next;
			}
		}
		return 0;
	}
	
	/** Constructs an iterator that can iterate over the whole list.
	 * @effects creates an iterator that can iterate over the list
	 *          without moving any nodes.
	 * @exception If there is no elements in DataCount, throws NoSuchElementException. 
	 * @return that iterator
	 */
	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>() {
			ListNode current = front;
			public boolean hasNext() {
				return current != null;
			}
			public DataCount<E> next() {
        		if(!hasNext()) {
        			throw new java.util.NoSuchElementException();
        		}
        		ListNode nextNode = current;
        		current = current.next;
        		return new DataCount<E>(nextNode.data, nextNode.count);
        	}
		};
	}
}