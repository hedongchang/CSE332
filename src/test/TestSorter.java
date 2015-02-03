package test;
import main.Sorter;

import org.junit.Test;
import static org.junit.Assert.*;


import phaseA.StringComparator;


public class TestSorter {
	
	private static final int TIMEOUT = 10000;

	@Test(timeout = TIMEOUT)
	public void test_general_heap_sort() {
		String[] input = {"b","a","c"};
		String[] expected = {"a", "b", "c"};
		Sorter.heapSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_one_heap_sort() {
		String[] input = {"a"};
		String[] expected = {"a"};
		Sorter.heapSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_sorted_heap_sort() {
		String[] input = {"a", "b", "c", "d"};
		String[] expected = {"a", "b", "c", "d"};
		Sorter.heapSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_empty_heap_sort() {
		String[] input = {};
		String[] expected = {};
		Sorter.heapSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_general_insertion_sort() {
		String[] input = {"b","a","c"};
		String[] expected = {"a", "b", "c"};
		Sorter.insertionSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_one_insertion_sort() {
		String[] input = {"a"};
		String[] expected = {"a"};
		Sorter.insertionSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_sorted_insertion_sort() {
		String[] input = {"a", "b", "c", "d"};
		String[] expected = {"a", "b", "c", "d"};
		Sorter.insertionSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_empty_insertion_sort() {
		String[] input = {};
		String[] expected = {};
		Sorter.insertionSort(input, new StringComparator());
		assertArrayEquals(input, expected);
	}
}
