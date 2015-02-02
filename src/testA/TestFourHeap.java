package testA;
import static org.junit.Assert.*;

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
		createHeap();
		for (String item: arr) {
			heap.insert(item);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_five_asc_inputs() {
		String[] test = {"a", "b", "c", "d", "e", "f"};
		insertArray(test);
		for (String expected: test) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expected);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_ten_asc_inputs() {
		String[] test = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		insertArray(test);
		for (String expected: test) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expected);
		}
	}
	
	@Test(timeout = TIMEOUT)
	public void test_insert_desc_inputs() {
		String[] test = {"f", "e", "d", "c", "b", "a"};
		insertArray(test);
		for (String expect: expected1) {
			String deleted = heap.deleteMin();
			assertEquals(deleted, expect);
		}
	}
	

}
