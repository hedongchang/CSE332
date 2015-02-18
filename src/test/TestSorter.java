/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package test;

import main.Sorter;

import org.junit.Test;

import phaseA.StringComparator;
import static org.junit.Assert.*;



	public class TestSorter {
		private static final int TIMEOUT = 2000; // 2000ms = 2sec
		private static final String[] testUnique = {"c", "f", "a", "b", "h", "g", "e", "i", "m", "d", "k", "n", "j","l"};
		private static final String[] testDuplicate = {"c", "c", "a", "b", "a", "b", "e", "e", "m", "d", "d", "m", "j", "i"};
		private static final String[] testEmpty = {"", "d", "a", ""};
		private static final String[] testSymbol = {"{", "}", ";", "*", "&"};
		
		/** Test heapSort ===========================================================**/
		@Test(timeout = TIMEOUT)
		public void test_heapsort_empty() {
			Sorter.heapSort(testEmpty, new StringComparator());
			String[] expected = {"", "", "a", "d"};
			assertArrayEquals(testEmpty, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_heapsort_Symbol() {
			Sorter.heapSort(testSymbol, new StringComparator());
			String[] expected = {"&", "*", ";", "{", "}"};
			assertArrayEquals(testSymbol, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_heapsort_unique() {
			Sorter.heapSort(testUnique, new StringComparator());
			String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
			assertArrayEquals(testUnique, expected);
		}

		@Test(timeout = TIMEOUT)
		public void test_heapsort_duplicate() {
			Sorter.heapSort(testDuplicate, new StringComparator());
			/*
			for (int i = 0; i < testDuplicate.length; i++) {
				System.out.print(testDuplicate[i]);
			} */
			String[] expected = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "i", "j", "m", "m"};
			assertArrayEquals(testDuplicate, expected);
		}
		
		/** Test insertionSort ===========================================================**/
		@Test(timeout = TIMEOUT)
		public void test_insertionSort_empty() {
			Sorter.insertionSort(testEmpty, new StringComparator());
			String[] expected = {"", "", "a", "d"};
			assertArrayEquals(testEmpty, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_insertionSort_Symbol() {
			Sorter.insertionSort(testSymbol, new StringComparator());
			String[] expected = {"&", "*", ";", "{", "}"};
			assertArrayEquals(testSymbol, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_insertionSort_unique() {
			Sorter.insertionSort(testUnique, new StringComparator());
			String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
			assertArrayEquals(testUnique, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_insertionSort_duplicate() {
			Sorter.insertionSort(testDuplicate, new StringComparator());
			String[] expected = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "i", "j", "m", "m"};
			assertArrayEquals(testDuplicate, expected);
		}
		
		/** Test topKSort ===========================================================**/
		@Test(timeout = TIMEOUT)
		public void test_topKSort_empty() {
			Sorter.topKSort(testEmpty, new StringComparator(), 14);
			String[] expected = {"d", "a", "", ""};
			assertArrayEquals(testEmpty, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_topKSort_Symbol() {
			Sorter.topKSort(testSymbol, new StringComparator(), 14);
			String[] expected = {"}", "{", ";", "*", "&"};
			assertArrayEquals(testSymbol, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_topKSort_unique() {
			Sorter.topKSort(testUnique, new StringComparator(), 14);
			String[] expected = {"n", "m", "l", "k", "j", "i",  "h", "g", "f", "e", "d", "c", "b", "a",};
			assertArrayEquals(testUnique, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_topKSort_duplicate() {
			Sorter.topKSort(testDuplicate, new StringComparator(), 14);
			String[] expected = {"m", "m", "j", "i",  "e", "e", "d", "d", "c", "c", "b", "b", "a", "a"};
			assertArrayEquals(testDuplicate, expected);
		}
		
		/** Test otherSort ===========================================================**/
		@Test(timeout = TIMEOUT)
		public void test_otherSort_empty() {
			Sorter.otherSort(testEmpty, new StringComparator());
			for (int i = 0; i < testEmpty.length; i++) {
				System.out.print(testEmpty[i]);
			}
			System.out.println();
			String[] expected = {"", "", "a", "d"};
			assertArrayEquals(testEmpty, expected);
		}
		
		@Test(timeout = TIMEOUT) 
		public void test_otherSort_Symbol() {
			Sorter.otherSort(testSymbol, new StringComparator());
			String[] expected = {"&", "*", ";", "{", "}"};
			assertArrayEquals(testSymbol, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_otherSort_unique() {
			Sorter.otherSort(testUnique, new StringComparator());
			for (int i = 0; i < testUnique.length; i++) {
				System.out.print(testUnique[i]);
			}
			String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
			assertArrayEquals(testUnique, expected);
		}
		
		@Test(timeout = TIMEOUT)
		public void test_otherSort_duplicate() {
			Sorter.otherSort(testDuplicate, new StringComparator());
			String[] expected = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "i", "j", "m", "m"};
			assertArrayEquals(testDuplicate, expected);
		}
}

