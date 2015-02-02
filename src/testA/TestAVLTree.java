package testA;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import phaseA.AVLTree;
import providedCode.Comparator;
;


public class TestAVLTree<E> extends TestBinarySearchTree {
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	private AVLTree<String> AVLTree;
	
	/** Creates BinarySearchTree before each test cases **/
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
		dc.incCount("a");
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
		AVLTree.incCount("b");
		AVLTree.incCount("a");
		AVLTree.incCount("c");
		AVLTree.incCount("e");
		AVLTree.incCount("d");
		assertEquals("The height of [b,a,c,e,d] after right-left rotation", 2, AVLTree.getHeight());	
	}
	
	
}