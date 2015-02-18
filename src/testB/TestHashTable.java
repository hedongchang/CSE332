/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package testB;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import phaseB.HashTable;
import phaseB.StringHasher;
import providedCode.Comparator;
import providedCode.DataCount;
import providedCode.SimpleIterator;

public class TestHashTable {
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	private HashTable<String> table;	
	
	public HashTable<String> getDataCounter() {
		return new HashTable<String>(new Comparator<String> () {
			public int compare(String e1, String e2) { return ((String) e1).compareTo((String) e2); }
		} ,new StringHasher());
	}
	
	@Before
	public void createTable() {
		table = (HashTable<String>) getDataCounter();
	}
	
	/** Test incCount ===========================================================**/
	@Test(timeout = TIMEOUT)
	public void test_incCount_empty() {
		table.incCount("");
		assertEquals("Empty string cannot be added to the hash table.", 1, table.getSize());
		table.incCount("");
		assertEquals("Empty string cannot be added to the hash table.", 1, table.getSize());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_incCount_normal_unique() {
		String[] testArray = {"a", "b", "c", "d"};
		addAndTestSize("The string cannot be incremented.", testArray, 4);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_incCount_normal_duplicate() {
		String[] testArray = {"a", "b", "c", "e", "d", "c", "a", "s"};
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 6);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_incCount_one_duplicate_() {
		String[] testArray = {"a", "a", "a", "a", "a", "a", "a", "a"};
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 1);
	}
	
	/** Test rehash ===========================================================**/
	@Test(timeout = TIMEOUT)
	public void test_rehash_beforeRehashing() {
		String[] testArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				              "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1"};
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 27);
		assertEquals("The table hasn't rehashed yet.", 37, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_37_duplicates() {
		String[] testArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				              "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "a", "b", "c", "d", "e", "f"};
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 27);
		assertEquals("The table hasn't been rehashed yet.", 37, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_73_unique() {
		String[] testArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				              "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3"};
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 29);
		assertEquals("The table has been rehashed.", 73, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_149_unique() {
		String[] testArray = new String[56];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = Character.toString((char) ('a' + i));
		}
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 56);
		assertEquals("The table has been rehashed.", 149, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_149_duplicates() {
		String[] testArray = new String[56];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = Character.toString((char) ('a' + i));
		}
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 56);
		table.incCount("a0");
		table.incCount("a4");
		table.incCount("a100");
		assertEquals("The table has been rehashed.", 149, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_307_unique() {
		String[] testArray = new String[113];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = Character.toString((char) ('a' + i));
		}
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 113);
		assertEquals("The table has been rehashed.", 307, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_307_duplicates() {
		String[] testArray = new String[113];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = Character.toString((char) ('a' + i));
		}
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 113);
		table.incCount("a0");
		table.incCount("a0");
		table.incCount("a4");
		table.incCount("a100");
		table.incCount("a200");
		table.incCount("a306");
		assertEquals("The table has been rehashed.", 307, table.tableLength());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_rehash_199961_unique() {
		String[] testArray = new String[79436];
		for (int i = 0; i < 60000; i++) {
			testArray[i] = Character.toString((char) ('a' + i));
		}
		for (int i = 60000; i < testArray.length; i++) {
			testArray[i] = Character.toString((char) ('a' + i)) + "b";
		}
		addAndTestSize("The string cannot be incremented or the increment amounts are wrong.", testArray, 79436);
		assertEquals("The table has been rehashed.", 199961, table.tableLength());
	}
	
	/** Test getCount ===========================================================**/
	@Test(timeout = TIMEOUT)
	public void test_getCount_empty() {
		assertEquals("There is no empty string in the initial table.", 0, table.getCount(""));
		table.incCount("");
		assertEquals("Empty string cannot be added to the hash table.", 1, table.getSize());
		assertEquals(1, table.getCount(""));
		table.incCount("");
		assertEquals("Empty string was added two times into the table.", 2, table.getCount(""));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_getCount_normal_unique() {
		String[] testArray = {"a", "b", "c", "d"};
		addAndTestSize("The string cannot be incremented.", testArray, 4);
		assertEquals("The count of the string is wrong.", 0, table.getCount(""));
		assertEquals("The count of the string is wrong.", 1, table.getCount("a"));
		assertEquals("The count of the string is wrong.", 1, table.getCount("b"));
		assertEquals("The count of the string is wrong.", 1, table.getCount("c"));
		assertEquals("The count of the string is wrong.", 1, table.getCount("d"));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_getCount_normal_duplicate() {
		String[] testArray = {"a", "b", "c", "d", "d", "c", "a", "c"};
		addAndTestSize("The string cannot be incremented.", testArray, 4);
		assertEquals("The count of the string is wrong.", 0, table.getCount(""));
		assertEquals("The count of the string is wrong.", 2, table.getCount("a"));
		assertEquals("The count of the string is wrong.", 1, table.getCount("b"));
		assertEquals("The count of the string is wrong.", 3, table.getCount("c"));
		assertEquals("The count of the string is wrong.", 2, table.getCount("d"));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_getCount_normal_duplicate_empty() {
		String[] testArray = {"", "", "", "", ""};
		addAndTestSize("The string cannot be incremented.", testArray, 1);
		assertEquals("The count of the string is wrong.", 5, table.getCount(""));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_getCount_normal_duplicate_one() {
		String[] testArray = {"a", "a", "a", "a", "a", "a", "a", "a"};
		addAndTestSize("The string cannot be incremented.", testArray, 1);
		assertEquals("The count of the string is wrong.", 8, table.getCount("a"));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_getCount_normal_duplicate_three() {
		String[] testArray = {"a", "a", "a", "b", "b", "b", "b", "c", ""};
		addAndTestSize("The string cannot be incremented.", testArray, 4);
		assertEquals("The count of the string is wrong.", 1, table.getCount(""));
		assertEquals("The count of the string is wrong.", 3, table.getCount("a"));
		assertEquals("The count of the string is wrong.", 4, table.getCount("b"));
		assertEquals("The count of the string is wrong.", 1, table.getCount("c"));
	}
	
	/** Test Iterator =======================================================**/

	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_iterator_empty() {
		SimpleIterator<DataCount<String>> iter = table.getIterator();
		iter.next(); 
	}
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data_int() {

	    String[] startArray = {"4", "6", "9", "0", "7", "9", "3", "1", "2", "4", "3", "2", "0"};

	    // Expected array has no duplicates and is sorted
	    for (int i = 0; i < startArray.length; i++) { 
		table.incCount(startArray[i]); 
	    }

	    String[] expected = { "0", "1", "2", "3", "4", "6", "7", "9"};

	    // Actual array returned by the iterator
	    int i = 0;
	    SimpleIterator<DataCount<String>> iter = table.getIterator();
	    String[] actual = new String[expected.length];
	    while (iter.hasNext()) { 
		actual[i++] = iter.next().data; 
	    }

	    // Sort and test
	    Arrays.sort(actual);
	    assertArrayEquals("Added " + Arrays.toString(startArray) + 
			      "Got " + Arrays.toString(actual), expected, actual);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data() {

	    String[] startArray = {"f", "c", "i", "a", "e", "h", "j", "d"};

	    // Expected array has no duplicates and is sorted
	    for (int i = 0; i < startArray.length; i++) { 
		table.incCount(startArray[i]); 
	    }

	    String[] expected = { "a", "c", "d", "e", "f", "h", "i", "j"};

	    // Actual array returned by the iterator
	    int i = 0;
	    SimpleIterator<DataCount<String>> iter = table.getIterator();
	    String[] actual = new String[expected.length];
	    while (iter.hasNext()) { 
		actual[i++] = iter.next().data; 
	    }

	    // Sort and test
	    Arrays.sort(actual);
	    assertArrayEquals("Added " + Arrays.toString(startArray) + 
			      "Got " + Arrays.toString(actual), expected, actual);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data_duplicate() {

	    String[] startArray = {"c", "c", "f", "f", "i", "i", "a", "a", "e", "e", "d", "j", "d"};

	    // Expected array has no duplicates and is sorted
	    for (int i = 0; i < startArray.length; i++) { 
		table.incCount(startArray[i]); 
	    }

	    String[] expected = { "a", "c", "d", "e", "f", "i", "j"};

	    // Actual array returned by the iterator
	    int i = 0;
	    SimpleIterator<DataCount<String>> iter = table.getIterator();
	    String[] actual = new String[expected.length];
	    while (iter.hasNext()) { 
		actual[i++] = iter.next().data; 
	    }

	    // Sort and test
	    Arrays.sort(actual);
	    assertArrayEquals("Added " + Arrays.toString(startArray) + 
			      "Got " + Arrays.toString(actual), expected, actual);
	}
	
	/** Private methods =====================================================**/
	private void addAndTestSize(String message, String[] input, int unique) {
		for (String s : input) { 
		    table.incCount(s); 
		}
		assertEquals(message, unique, table.getSize());
	}

}