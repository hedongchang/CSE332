/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package testA;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import phaseA.AVLTree;
import providedCode.Comparator;
import providedCode.DataCount;
import providedCode.SimpleIterator;


public class TestAVLTree extends TestBinarySearchTree {
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	private AVLTree<String> AVLTree;
	
	/** Creates AVLTree before each test cases **/
	public AVLTree<String> getDataCounter() {
		return new AVLTree<String>(new Comparator<String> () {
			public int compare(String e1, String e2) { return ((String) e1).compareTo((String) e2); }
		});
	}
	
	@Before
	public void createTree() {
		AVLTree = (AVLTree<String>) getDataCounter();
	}
	
	
	/** Test Height and Rotation ===========================================================**/
	@Test(timeout = TIMEOUT)
	public void test_null_AVL_height(){
		assertEquals("The height of a null AVL Tree", -1, AVLTree.getHeight());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_one_node_height() {
		AVLTree.incCount("a");
		assertEquals("The height of [a]", 0, AVLTree.getHeight());	
	}

	@Test(timeout = TIMEOUT)
	public void test_two_same_node_height() {
		AVLTree.incCount("a");
		AVLTree.incCount("a");
		assertEquals("The height of [a, a]", 0, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void test_two_same_three_nodes_height() {
		AVLTree.incCount("a");
		AVLTree.incCount("a");
		AVLTree.incCount("c");
		assertEquals("The height of [a, a, c]", 1, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void test_two_diff_node_height() {
		AVLTree.incCount("a");
		AVLTree.incCount("b");
		assertEquals("The height of [a, b]", 1, AVLTree.getHeight());	
	}	
	
	@Test(timeout = TIMEOUT)
	public void test_three_nodes_AVL_height() {
		AVLTree.incCount("b"); 
		AVLTree.incCount("a");
		AVLTree.incCount("c");
		assertEquals("The height of [b, a, c]", 1, AVLTree.getHeight());	
	}	
	
	@Test(timeout = TIMEOUT)
	public void right_right_rotation() {
		AVLTree.incCount("a");
		AVLTree.incCount("b");
		AVLTree.incCount("c");
		assertEquals("The height of [a,b,c] after right-right rotation", 1, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void left_left_rotation() {
		AVLTree.incCount("c");
		AVLTree.incCount("b");
		AVLTree.incCount("a");
		assertEquals("The height of [c,b,a] after left-left rotation", 1, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void left_right_rotation() {
		AVLTree.incCount("d");
		AVLTree.incCount("b");
		AVLTree.incCount("c");
		assertEquals("The height of [d,b,c] after left-right rotation", 1, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void right_left_rotation() {
		AVLTree.incCount("b");
		AVLTree.incCount("d");
		AVLTree.incCount("c");
		assertEquals("The height of [b,d,c] after right-left rotation", 1, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void right_left_rotation_five_nodes() {
		String[] list = {"b", "a", "c", "e", "d"};
		for (int i = 0; i < list.length; i++) {
			AVLTree.incCount(list[i]);
		}
		assertEquals("The height of" + Arrays.toString(list) + "after right-left rotation", 2, AVLTree.getHeight());
	}
	
	@Test(timeout = TIMEOUT)
	public void left_left_rotation_seven_nodes() {
		String[] list = {"f", "c", "i", "b", "h", "j", "a"};
		for (int i = 0; i < list.length; i++) {
			AVLTree.incCount(list[i]);
		}
		assertEquals("The height of" + Arrays.toString(list) + "after left-left rotation", 2, AVLTree.getHeight());
	}
	
	public void left_right_rotation_nine_nodes() {
		String[] list = {"f", "c", "j", "b", "d", "i", "k", "g", "h"};
		for (int i = 0; i < list.length; i++) {
			AVLTree.incCount(list[i]);
		}
		assertEquals("The height of" + Arrays.toString(list) + "after left-right rotation", 3, AVLTree.getHeight());
	}
	
	@Test(timeout = TIMEOUT)
	public void right_right_rotation_nine_nodes() {
		String[] list = {"g", "c", "i", "b", "d", "h", "j", "e", "f"};
		for (int i = 0; i < list.length; i++) {
			AVLTree.incCount(list[i]);
		}
		assertEquals("The height of" + Arrays.toString(list) + "after right-right rotation", 3, AVLTree.getHeight());	
	}
	
	@Test(timeout = TIMEOUT)
	public void right_left_rotation_nine_nodes() {
		String[] list = {"g", "c", "i", "b", "d", "h", "j", "f", "e"};
		for (int i = 0; i < list.length; i++) {
			AVLTree.incCount(list[i]);
		}
		assertEquals("The height of" + Arrays.toString(list) + "after right-left rotation", 3, AVLTree.getHeight());	
	}
	/** Test Iterator =======================================================**/

	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_iterator_empty() {
		SimpleIterator<DataCount<String>> iter = dc.getIterator();
		iter.next(); 
	}
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data() {

	    String[] startArray = {"f", "c", "i", "a", "e", "h", "j", "d"};

	    // Expected array has no duplicates and is sorted
	    for (int i = 0; i < startArray.length; i++) { 
		dc.incCount(startArray[i]); 
	    }

	    String[] expected = { "a", "c", "d", "e", "f", "h", "i", "j"};

	    // Actual array returned by the iterator
	    int i = 0;
	    SimpleIterator<DataCount<String>> iter = dc.getIterator();
	    String[] actual = new String[expected.length];
	    while (iter.hasNext()) { 
		actual[i++] = iter.next().data; 
	    }

	    // Sort and test
	    Arrays.sort(actual);
	    assertArrayEquals("Added " + Arrays.toString(startArray) + 
			      "Got " + Arrays.toString(actual), expected, actual);
	}
	
}