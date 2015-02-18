/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */
package main;
import phaseA.FourHeap;
import providedCode.Comparator;
import providedCode.Heap;


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
    
    /** Sort the array from minimum priority to maximum priority
     * @param array that needs to be sorted
     * @param comparator for comparing values
     * @effects Sort the given array from the least priority to most priority
     */
    public static <E> void heapSort(E[] array, Comparator<E> comparator) {
    	Heap<E> heap = new FourHeap<E>(comparator);
    	for (int i = 0; i < array.length; i++) {
    		heap.insert(array[i]);
    	}
    	for (int i = 0; i < array.length; i++) {
    		array[i] = heap.deleteMin();
    	}
    }
    
    /** Sort the first k elements in the array from maximum priority to minimum priority
     * @param k the number of elements that need to be sorted
     * @param array that needs to be sorted
     * @param comparator for comparing values
     * @effects Sort the given array from the least priority to most priority
     */
    public static <E> void topKSort(E[] array, Comparator<E> comparator, int k) {
    	if (k <= 0) {
    		throw new IllegalArgumentException();
    	}
    	if (k >= array.length) {
    		k = array.length;
    	}
    	Heap<E> heap = new FourHeap<E>(comparator);
    	for (int i = 0; i < k; i++) {
    		heap.insert(array[i]);
    	}
    	
    	for (int i = k; i < array.length; i++) {
    		if (comparator.compare(array[i], heap.findMin()) >= 0) {
    			heap.deleteMin();
    			heap.insert(array[i]);
    		}
    	}   	
    	for (int i = k - 1; i >= 0; i--) {
    		array[i] = heap.deleteMin();
    	}	
    }
    
    /** Sort the array from minimum priority to maximum priority
     * @param array that needs to be sorted
     * @param comparator for comparing values
     * @effects Sort the given array from the least priority to most priority
     */
    @SuppressWarnings("unchecked")
    public static <E> void otherSort(E[] array, Comparator<E> comparator) {
    	E[] temp = (E[]) new Object[array.length];
    	mergeSort(array, temp, comparator, 0, array.length - 1);
    }
    
    /** Sort the array from minimum priority to maximum priority
     * @param array that needs to be sorted
     * @param temp the array that holds temporary data
     * @param comparator for comparing values
     * @param left the start index of the left half of array
     * @param right the start index of the right half of array
     */
    private static <E> void mergeSort(E[] array, E[] temp, Comparator<E> comparator, 
    		int left, int right) {
    	if (left < right) {
    		int center = (left + right) / 2;
    		mergeSort(array, temp, comparator, left, center);
    		mergeSort(array, temp, comparator, center + 1, right);
    		merge(array, temp, comparator, left, center + 1, right);
    	}
    	
    }
    
    /**
     * Merge two sorted arrays in one array
     * @param array that needs to be sorted
     * @param temp the array that holds temporary data
     * @param comparator for comparing values
     * @param left the start index of the left half of array
     * @param right the start index of the right half of array
     * @param rightEnd the end index of the right half of array
     */
	private static <E> void merge(E[] array, E[] temp, Comparator<E> comparator, 
    		int left, int right, int rightEnd) {
		int leftEnd = right - 1;
        int tempIndex = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd) {
            if (comparator.compare(array[left], array[right]) <= 0) {
                temp[tempIndex++] = array[left++];
            } else {
                temp[tempIndex++] = array[right++];
            }
        }

        while (left <= leftEnd) {   // Copy rest of the left half
            temp[tempIndex++] = array[left++];
        }

        while (right <= rightEnd)  // Copy rest of the right half
            temp[tempIndex++] = array[right++];

        // Copy temp back
        for(int i = 0; i < num; i++, rightEnd--) {
            array[rightEnd] = temp[rightEnd];
        }
	}
}
