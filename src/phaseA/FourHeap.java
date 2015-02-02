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
 *    example, when deleteminIndex is on an empty heap, you could use
 *    UnderFlowException as is done in the Weiss text, or you could
 *    use NoSuchElementException (in which case it will be fine if you
 *    want to import it).
 * TODO: Develop appropriate JUnit tests for your FourHeap.
 * 
 * IMPORTANT NOTE:
 * 1. You MUST use the fields defined in the superclass!
 * 2. FourHeap should be a minIndex-HEAP, which means the "smallest"
 *    element according to the given comparator should be at the root. It
 *    is obvious from the name of methods, deleteminIndex() & findminIndex(),
 *    which should return the minIndeximum element determinIndexed by the given
 *    comparator. For example, the DataCountStringComparator considers
 *    the element with the highest count to be the "smallest", so when you
 *    call deleteminIndex() or findminIndex() your FourHeap should return the
 *    element with highest count.
 * 3. If you accidentally made it as MAX-HEAP instead of minIndex-HEAP, it
 *    will return the "largest" element (with
 *    DataCountStringComparator, this will be the one with lowest count) when
 *    deleteminIndex() is called, and you are going to lose considerable
 *    amount of FourHeap points.
 * 4. For Testing: Given the same comparator, FourHeap's deleteminIndex()
 *    should return the same element as Java's PriorityQueue's poll().
 */
public class FourHeap<E> extends Heap<E> {
	
	Comparator<? super E> comparator;
	
	public FourHeap(Comparator<? super E> c) {
		super(10);
		this.comparator = c;
	}

	@Override
	public void insert(E val) {
		if (size == heapArray.length - 1) {
			doubleSize();
		}
		int hole = size;
		size++;
		hole = percolateUp(hole, val);
		heapArray[hole]  = val;
	}
	

	@SuppressWarnings("unchecked")
	private void doubleSize() {
		E[] newArray = (E[]) new Object[heapArray.length * 2];
		for(int i = 0; i < heapArray.length; i++) {
			newArray[i] = heapArray[i];
		}
		heapArray = newArray;
	}

	@Override
	public E findMin() {
		return heapArray[0];
	}

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

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	private int percolateUp(int hole, E val) {
		while (hole > 0 && comparator.compare(val, 
				heapArray[(hole-1) / 4]) < 0) {
			heapArray[hole] = heapArray[(hole-1) / 4];
			hole = (hole-1) / 4;
		}
		return hole;
	}
	
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
			} else  {
				break;
			}
			hole = minIndex;
		}
		return hole;
	}
	


}