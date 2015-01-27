package phaseA;
import java.util.NoSuchElementException;

import providedCode.*;


/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. It is exactly like the binary heap we studied, except nodes
 *    should have 4 children instead of 2. Only leaves and at most one
 *    other node will have fewer children.
 * 2. Use an array-based implementation, beginning at index 0 (Root
 *    should be at index 0).  Construct the FourHeap by passing
 *    appropriate argument to superclass constructor.  Hint: Complete
 *    written homework #2 before attempting this.
 * 3. Throw appropriate exceptions in FourHeap whenever needed. For
 *    example, when deleteMin is on an empty heap, you could use
 *    UnderFlowException as is done in the Weiss text, or you could
 *    use NoSuchElementException (in which case it will be fine if you
 *    want to import it).
 * TODO: Develop appropriate JUnit tests for your FourHeap.
 * 
 * IMPORTANT NOTE:
 * 1. You MUST use the fields defined in the superclass!
 * 2. FourHeap should be a MIN-HEAP, which means the "smallest"
 *    element according to the given comparator should be at the root. It
 *    is obvious from the name of methods, deleteMin() & findMin(),
 *    which should return the minimum element determined by the given
 *    comparator. For example, the DataCountStringComparator considers
 *    the element with the highest count to be the "smallest", so when you
 *    call deleteMin() or findMin() your FourHeap should return the
 *    element with highest count.
 * 3. If you accidentally made it as MAX-HEAP instead of MIN-HEAP, it
 *    will return the "largest" element (with
 *    DataCountStringComparator, this will be the one with lowest count) when
 *    deleteMin() is called, and you are going to lose considerable
 *    amount of FourHeap points.
 * 4. For Testing: Given the same comparator, FourHeap's deleteMin()
 *    should return the same element as Java's PriorityQueue's poll().
 */
public class FourHeap<E> extends Heap<E> {
	
	Comparator<? super E> comparator;
	
	public FourHeap(Comparator<? super E> c) {
		super(10);
		this.comparator = c;
	}

	@Override
	public void insert(E item) {
		size++;
		if (size > heapArray.length) {
			doubleSize();
		}
		heapArray[size - 1] = item;
		percolateUp();
	}
	
	private void doubleSize() {
		size *= 2;
		this.heapArray = (E[]) new Object[size];
	}

	@Override
	public E findMin() {
		return heapArray[0];
	}

	@Override
	public E deleteMin() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E min = heapArray[0];
		int hole = percolateDown(0, heapArray[size - 1]);
		size--;
		return min;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void percolateUp() {
		
	}
	
	private int percolateDown(int hole, E last) {
		while (4 * hole <= size) {
			int first = 4 * hole + 1;
			int second = 4 * hole + 2;
			int third = 4 * hole + 3;
			int fourth = 4 * hole + 4;
		}
		return hole;
	}

}
