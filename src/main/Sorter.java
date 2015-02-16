/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */
package main;
import phaseA.FourHeap;
import providedCode.Comparator;


/** 
 * This program implements multiple sorting algorithms to sort array from the maximum 
 * value to the minimum value.
 */
public class Sorter {
	
	/**
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be ordered according to the comparator.
     * This code uses insertion sort. The code is generic, but in this project
     * we use it with DataCount<String> and DataCountStringComparator.
     * @param counts array to be sorted.
     * @param comparator for comparing elements.
     */
    public static <E> void insertionSort(E[] array, Comparator<E> comparator) {
        for (int i = 1; i < array.length; i++) {
            E x = array[i];
            int j;
            for (j=i-1; j>=0; j--) {
                if (comparator.compare(x,array[j]) >= 0) { break; }
                array[j + 1] = array[j];
            }
            array[j + 1] = x;
        }
    }
    
    /** Sort the array from minimum value to maximum value
     * @param array that needs to be sorted
     * @param comparator
     * @effects Sort the given array.
     */
    public static <E> void heapSort(E[] array, Comparator<E> comparator) {
    	FourHeap<E> heap = new FourHeap<E>(comparator);
    	for (int i = 0; i < array.length; i++) {
    		heap.insert(array[i]);
    	}
    	int i = 0;
    	while (!heap.isEmpty()) {
    		array[i] = heap.deleteMin();
    		i++;
    	}
    }
    
    /**
     * TODO: REPLACE this comment with your own as appropriate.  In
     * topKSort, you will need to use a different comparator which
     * considers the element with the lowest count to be the
     * "smallest". You should *NOT* change how your FourHeap
     * interprets the comparator result.  The heap should always interpret
     * a negative number from the comparator as the first argument being
     * "smaller" than the second.
     * 
     * Make sure topK sort only prints the first k elements. In 
     * WordCount, you can either modify the signature of the existing
     * print method or add another print method for topKSort.
     */
    public static <E> void topKSort(E[] array, Comparator<E> comparator, int k) {
    	if (k > array.length) {
    		k = array.length;
    	}
    	FourHeap<E> heap = new FourHeap<E>(comparator);
    	for (int i = 0; i < array.length; i++) {
    		if (i >= k && comparator.compare(array[i], heap.findMin()) > 0) {
    			heap.deleteMin();
    		}
    		heap.insert(array[i]);
    	}
    	for (int i = k - 1; i >= 0; i--) {
    		array[i] = heap.deleteMin();
    	}
    }
    
    public static <E> void otherSort(E[] array, Comparator<E> comparator) {
    	mergeSort(array, comparator, 0, array.length);
    }
    
    private static <E> void mergeSort(E[] array, Comparator<E> comparator, 
    		int left, int right) {
    	if (left < right) {
    		int center = (left + right) / 2;
    		mergeSort(array, comparator, left, center);
    		mergeSort(array, comparator, center + 1, right);
    		merge(array, comparator, left, center + 1, right);
    	}
    	
    }
    
    @SuppressWarnings("unchecked")
	private static <E> void merge(E[] array, Comparator<E> comparator, 
    		int left, int center, int right) {
    	E[] temp = (E[]) new Object[array.length];
    	for (int i = 0; i < temp.length; i++) {
    		temp[i] = array[i];
    	}
    	int leftIndex = left;
    	int rightIndex = center + 1;
    	int resultIndex = left;
    	while (leftIndex <= center && rightIndex <= right) {
    		if (comparator.compare(temp[leftIndex], temp[rightIndex]) < 0) {
    			array[resultIndex] = temp[leftIndex];
    			leftIndex++;
    		} else {
    			array[resultIndex] = temp[rightIndex];
    			rightIndex++;
    		}
    		resultIndex++;
    	}
    	while (leftIndex <= center) {
    		array[resultIndex] = temp[leftIndex];
    		resultIndex++;
    		leftIndex++;
    	}
    }

}
