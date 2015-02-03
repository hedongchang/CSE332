package testA;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import phaseA.FourHeap;
import phaseA.StringComparator;
import providedCode.Comparator;



public class TestFourHeap {
	
	protected FourHeap<String> heap;
	private static final int TIMEOUT = 2000;
	private String[] expected = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
	private String[] expected1 = {"a", "b", "c", "d", "e"};
	
	/** Creates FourHeap before each test cases **/
	private void createHeap() {
		Comparator<? super String> com = new StringComparator();
		this.heap = new FourHeap<String>(com);
	}
	
	private void insertArray(String[] arr) {
		for (String item: arr) {
			heap.insert(item);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_five_asc_inputs() {
		String[] test = {"a", "b", "c", "d", "e", "f"};
		createHeap();
		insertArray(test);
		for (String expected: test) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expected);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_ten_asc_inputs() {
		String[] test = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		createHeap();
		insertArray(test);
		for (String expected: test) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expected);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_five_desc_inputs() {
		String[] test = {"f", "e", "d", "c", "b", "a"};
		createHeap();
		insertArray(test);
		for (String expect: expected1) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expect);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_ten_general_inputs() {
		String[] test = {"c", "d", "e", "a", "f", "b", "h", "g", "i", "j"};
		createHeap();
		insertArray(test);
		for (String expect: expected) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expect);
		}
	}
	
	@Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
	public void test_insert_empty_inputs() {
		createHeap();
		heap.deleteMin();
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_twenty_duplicate_inputs() {
		String[] test = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "f", "f"};
		createHeap();
		insertArray(test);
		for (String expect: test) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expect);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_two_inputs() {
		String[] test = {"b", "a"};
		createHeap();
		insertArray(test);
		String deleted = heap.deleteMin();
		assertEquals(deleted, "a");
		String deleted2 = heap.deleteMin();
		assertEquals(deleted2, "b");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_is_empty() {
		createHeap();
		assertTrue(heap.isEmpty());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_is_not_empty() {
		createHeap();
		String[] test = {"a"};
		insertArray(test);
		assertTrue(!heap.isEmpty());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_duplicate_inputs_find_min() {
		String[] test = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "f", "f"};
		createHeap();
		insertArray(test);
		String min = heap.findMin();
		assertEquals(min, "a");
	}
	
	@Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
	public void test_empty_find_min() {
		createHeap();
		heap.findMin();
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_five_input_find_min() {
		String[] test = {"b", "a", "d", "c", "e"};
		createHeap();
		insertArray(test);
		String min = heap.findMin();
		assertEquals(min, "a");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_ten_input_find_min() {
		String[] test = {"a", "b", "d", "c", "f", "e"};
		createHeap();
		insertArray(test);
		String min = heap.findMin();
		assertEquals(min, "a");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_five_input_delete_find_min() {
		String[] test = {"b", "a", "d", "c", "e"};
		createHeap();
		insertArray(test);
		heap.deleteMin();
		String min = heap.findMin();
		assertEquals(min, "b");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_ten_input_delete_find_min() {
		String[] test = {"b", "a", "d", "c", "e", "g", "h", "i", "k", "j"};
		createHeap();
		insertArray(test);
		heap.deleteMin();
		heap.deleteMin();
		String min = heap.findMin();
		assertEquals(min, "c");
	}
}
