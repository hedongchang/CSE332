package phaseA;

import providedCode.*;


/**
 * TODO: REPLACE this comment with your own as appropriate.
 * 1. The list is typically not sorted.
 * 2. Add new items (with a count of 1) to the front of the list.
 * 3. Whenever an existing item has its count incremented by incCount
 *    or retrieved by getCount, move it to the front of the list. That
 *    means you remove the node from its current position and make it
 *    the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 *    elements to the front.  The iterator should return elements in
 *    the order they are stored in the list, starting with the first
 *    element in the list.
 * TODO: Develop appropriate JUnit tests for your MoveToFrontList.
 */
public class MoveToFrontList<E> extends DataCounter<E> {
	
	public ListNode front;
	public Comparator<? super E> comparator;
	
	private class ListNode {
		E data;
		int count;
		ListNode next;
		
		/* @param Count of the number of nodes.
		 * @effects Constructs a new list node, store its count number as its data.*/
		public ListNode(E count) {
			this.data = count;
			this.count = 1;
			this.next = null;
		}
	}
	
	/* @param A comparator.
	 * @effects Constructs a new MoveToFrontList. */
	public MoveToFrontList(Comparator<? super E> c) {
		this.comparator = c;
		this.front = null;
	}
	
	/* @param A data
	 * @effects Find the node with this data in the MoveToFrontList,
	 *          and move it from the original position to the front.
	 *          If the list is null, add a new node with this data.
	 *          If there is no this node, add a new node with this data to the front.*/
	@Override
	public void incCount(E data) {
		if (front == null) {
           this.front = new ListNode(data);
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
		}
	}
    
	/* @return The size of the MoveToFrontList */
	@Override
	public int getSize() {
		int size = 0;
		ListNode current = front;
		while (current != null) {
			size++;
			current = current.next;
		}
		return size;
	}
	
	/** @param data: value of the node 
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