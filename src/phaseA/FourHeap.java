/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package phaseA;
import java.util.NoSuchElementException;

import providedCode.*;


/**
* This is program uses array to implement a heap that each element can accept up to four children. 
* The element containing minimum value will stay at the top of the heap. Whenever we insert or
* delete an element, we need to move the related element to the appropriate position where 
* its value is larger than the value of its parents (or at the top of the heap) and 
* smaller than the value of its four children (or at the bottom of the heap).
 */
public class FourHeap<E> extends Heap<E> {
	public static final int DEFAULT_SIZE = 10;
    public static final int RE_SIZE = 2;
	
	private Comparator<? super E> comparator;
	
	/**constructs a comparator that can compare the elements in this heap.*/
	public FourHeap(Comparator<? super E> c) {
		super(DEFAULT_SIZE);
		this.comparator = c;
		this.size = 0;
	}
	
	/** Inserts one new element with given value to the current heap
	 * and move it to right position, where its value must larger than 
	 * the value of its parents and smaller than its children.
	 * @param Value of the new element
	 * @requires Double the capacity of the array if the current is already full.
	 * @modify the heap by inserting a new element with the given value to the heap
	 *         and move it to the right position.
	 */
	@Override
	public void insert(E val) {
		if (size == heapArray.length - 1) {
			reSize();
		}
		int hole = size;
		size++;
		hole = percolateUp(hole, val);
		heapArray[hole]  = val;
	}
	
	/** Double the size of the heap.
	 * @modify the heap by doubling the size of the heap
	 */
	@SuppressWarnings("unchecked")
	private void reSize() {
		E[] newArray = (E[]) new Object[heapArray.length * RE_SIZE];
		for(int i = 0; i < heapArray.length; i++) {
			newArray[i] = heapArray[i];
		}
		heapArray = newArray;
	}
	
	/** Find the element with minimum value.
	 * @effects find the element that contains minimum value
	 * @requires the heap is not empty.
	 * @exception if the heap is empty, throws NoSuchElementException.
	 * @return the element with minimum value
	 */
	@Override
	public E findMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return heapArray[0];
	}
	
	/** Delete the minimum value in current heap
	 * @modifies the heap by deleting the element with minimum value.
	 * @requires the heap is not empty.
	 * @exception if the heap is empty, throws NoSuchElementException.
	 * @return the element with minimum value.
	 */
	@Override
	public E deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E min = heapArray[0];
		heapArray[0] = heapArray[size - 1];
		size--;
		E val = heapArray[0];
		int hole = percolateDown(0, val);
		heapArray[hole] = val;	
		return min;
	}
	
	/** Check whether the current heap is empty.
	 * @return true if size is empty; false if not.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Compares the value of the element and the value of its parent.
	 * If its value is smaller than the value of its parent,
	 * we move it up one position by exchanging it with its parent.
	 * This action continues until it finds a position where its
	 * parent is smaller than it, or at the top of the heap.
	 * @param hole: the index of the element need to be moved up.
	 * @param val: the value of the element need to be moved up.
	 * @requires the index of the hole is larger than 0.
	 * @return the new position index of that element.
	 */
	private int percolateUp(int hole, E val) {
		while (hole > 0 && comparator.compare(val, 
				heapArray[(hole-1) / 4]) < 0) {
			heapArray[hole] = heapArray[(hole-1) / 4];
			hole = (hole-1) / 4;
		}
		return hole;
	}
	
	/**
	 * Compares the value of the element and the value of its four children. 
	 * If one of its children is smaller than it, we move the element down one
	 * position by exchanging it with its smallest children. This action continues 
	 * until the element find a position where all its four children are larger 
	 * than it or it approaches the bottom of the heap.
	 * @param hole: the index of the element need to be moved down.
	 * @param val: the value of the element need to be moved down.
	 * @requires the index of its parent should not exceed the size of the heap.
	 * @return the index of new position of that element.
	 */
	private int percolateDown(int hole, E val) {
		while (4*hole + 1 < size) {			
			int minIndex = 4 * hole + 1;
			for (int i = 2; i <= 4; i++) {
				if (4 * hole + i < size && 
						comparator.compare(heapArray[4 * hole + i], heapArray[minIndex]) < 0) {
					minIndex = 4 * hole + i;
				}
			}
			if(comparator.compare(heapArray[minIndex], val) < 0) {
				heapArray[hole] = heapArray[minIndex];
			} else {
				break;
			}
			hole = minIndex;
		}
		return hole;
	}
}