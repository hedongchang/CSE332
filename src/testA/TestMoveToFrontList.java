package testA;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import phaseA.MoveToFrontList;
import providedCode.Comparator;
import providedCode.DataCount;
import providedCode.DataCounter;
import providedCode.SimpleIterator;
import test.TestDataCounter;


public class TestMoveToFrontList extends TestDataCounter<String> {
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	
	
	/** Creates MoveToFrontList before each test cases **/
	@Override
	public DataCounter<String> getDataCounter() {
		return new MoveToFrontList<String>(new Comparator<String>() {
			public int compare(String e1, String e2) { return e1.compareTo(e2); }
		});
	}
	
	
	/** Test Size ===========================================================**/
	@Test(timeout = TIMEOUT)
	public void test_size_empty(){
		assertEquals("List should have size 0 when constructed", 0, dc.getSize());
	}
	
	@Test(timeout = TIMEOUT) 
	public void test_size_after_adding_single_string(){
		addAndTestSize("List should have size 1 after adding single 5", new String[] {"a"}, 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_many_same_string(){		
		addAndTestSize("List should have size 1 after adding multiple 5", new String[] {"a", "a", "a"}, 1);
	}

	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_unique_strings(){
		String[] testArray = {"a", "b", "c", "d", "e"};
		addAndTestSize("Added" + Arrays.toString(testArray), testArray, 5);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_duplicate_nums(){
		String[] testArray = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "f", "f"};
		addAndTestSize("Added" + Arrays.toString(testArray), new String[] {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e", "f", "f"}, 6);
	}
	/** Test getCount =======================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_get_count_after_adding_many_same_string(){
		String key = "a";
		String[] testArray = {"a", "a", "a", "a", "a", "a", "a"};
		addAndGetCount("Added " + Arrays.toString(testArray) + ", key=" + key, testArray, key, 7);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_get_count_after_adding_many_diff_strings(){
		String key = "c";
		String[] testArray = {"a", "b", "c", "d", "c", "b", "c",};
		addAndGetCount("Added " + Arrays.toString(testArray) + ", key=" + key, testArray, key, 3);
	}
	
	/** Test Iterator =======================================================**/

	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_iterator_empty() {
		SimpleIterator<DataCount<String>> iter = dc.getIterator();
		iter.next(); 
	}
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data() {

	    String[] startArray = {"9", "8", "7", "6", "5", "4", "1"};

	    // Expected array has no duplicates and is sorted
	    for (int i = 0; i < startArray.length; i++) { 
	    	dc.incCount(startArray[i]); 
	    }

	    String[] expected = { "1", "4", "5", "6", "7", "8", "9" };

	    // Actual array returned by the iterator
	    int i = 0;
	    SimpleIterator<DataCount<String>> iter = dc.getIterator();
	    String[] actual = new String[expected.length];
	    while (iter.hasNext()) { 
	    	actual[i] = iter.next().data; 
	    	i++;
	    }

	    // Sort and test
	    Arrays.sort(actual);
	    assertArrayEquals("Added " + Arrays.toString(startArray) + 
			      "Got " + Arrays.toString(actual), expected, actual);

	}	
	
	@Test(timeout = TIMEOUT)
	public void test_first_getCount() {
		String[] test = {"a", "b", "c", "d"};
		for (String s: test) {
			dc.incCount(s);
		}
		dc.getCount("c");
		testFirst("any elements called should move to front", "c");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_first_incCount() {
		String[] test = {"a", "b", "c", "d"};
		for (String s: test) {
			dc.incCount(s);
		}
		dc.incCount("c");
		testFirst("any elements called should move to front", "c");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_new_first_incCount() {
		String[] test = {"a", "b", "c", "d"};
		for (String s: test) {
			dc.incCount(s);
		}
		dc.incCount("f");
		testFirst("any elements called should move to front", "f");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_two_first_incCount() {
		String[] test = {"a", "b", "c", "d", "e"};
		for (String s: test) {
			dc.incCount(s);
		}
		dc.incCount("c");
		dc.incCount("b");
		testFirst("any elements called should move to front", "b");
	}
	
	@Test(timeout = TIMEOUT)
	public void test_five_first_incCount() {
		String[] test = {"a", "b", "c", "d", "e", "g", "h"};
		for (String s: test) {
			dc.incCount(s);
		}
		dc.incCount("c");
		dc.incCount("b");
		dc.incCount("h");
		dc.incCount("e");
		dc.incCount("d");
		testFirst("any elements called should move to front", "d");
	}
	/** Private methods =====================================================**/
	
	private void testFirst(String message, String expected) {
		SimpleIterator<DataCount<String>> itr = dc.getIterator();
		String first = itr.next().data;
		assertEquals(message, first, expected);
	}

	private void addAndTestSize(String message, String[] input, int unique) {
		for (String s : input) { 
		    dc.incCount(s); 
		}
		assertEquals(message, unique, dc.getSize());
	}
	
	private void addAndGetCount(String message, String[] input, String key, int expected) {
		for (String s : input) { 
		    dc.incCount(s); 
		}
		assertEquals(message, expected, dc.getCount(key));
	}

}

