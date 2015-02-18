/**
 * Dongchang He & Juan Cai
 * CSE 332B Project 2 Phase A
 * 2/2/2015
 * Instructor: Anderson Ruth
 */

package phaseA;
import providedCode.*;


/** This program creates an AVL tree. It requires the heights of two children of a node differ
 *  in no larger than 1, in which case it is balanced. The top of the tree
 *  will be the minimum value of this tree. The left child's value should be smaller than
 *  its parent, and the right child's value should be larger than its parents. If the tree
 *  is unbalanced after a new node insertion, it can rotate the tree in proper ways to make 
 *  it balanced again. 
 */
public class AVLTree<E> extends BinarySearchTree<E> {
	
	/** Creates a class that constructs the node for the AVL tree.
	 * @author Dongchang He, Cai Juan
	 */
	private class AVLNode extends BSTNode {
		//the height of the node
		private int height;
		
		/** Constructs the node with the given value.
		 * @param data: the value of the node.
		 */
		public AVLNode(E data) {
			super(data);
			this.height = 0;
		}
	}
    
	/** Constructs a AVL tree.
	 * @effects Constructs a new AVL Tree with a comparator that can
	 *          compare the values of different nodes.
	 */
	public AVLTree(Comparator<? super E> c) {
		super(c);
	}
	
	/** Increase the count of the node with the given value. 
	 * @param data: the value of a new node
	 * @modifies the AVL tree when there is no node contains the given data by adding
	 *           it to the tree and make the count be one.
	 * @effects increase the count of the existing node that contains the given data by one.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void incCount(E data) {
		overallRoot = insert((AVLNode) overallRoot, data);
	}
	
	/** Get the height of the current tree.
	 * @return height: the height of the root.
	 */
	public int getHeight() {
		return height(overallRoot);
	}
	
	/** Insert a new node to the AVL tree with the given data as its value.
	 * @param current: the current node that built most recently.
	 * @param data: the data of inserted node.
	 * @return the new inserted node.
	 */
	@SuppressWarnings("unchecked")
	private AVLNode insert(AVLNode current, E data) {
		if (current == null) {
			return new AVLNode(data);
		}
		int cmp = comparator.compare(data, current.data);
    	if (cmp == 0) {            // a. Current node is a match
    		current.count++;
        }else if(cmp < 0) {       // b. Data goes left of current node
        	current.left = insert((AVLNode) current.left, data);
        	if (height(current.left) - height(current.right) > 1) {
        		if (comparator.compare(data, current.left.data) < 0) {
        			//singleRotateRight
        			current = rotateLeftLeft(current);
        		} else {
        			current = rotateLeftRight(current);
        		}
        	} 
        } else {                    // c. Data goes right of current node
        	current.right = insert((AVLNode) current.right, data);
        	if (height(current.right) - height(current.left) > 1) {
        		if (comparator.compare(data, current.right.data) < 0) {
        			current = rotateRightLeft(current);
        		} else {
        			//singleRotateLeft
        			current = rotateRightRight(current);
        		}
        	} 
        }
    	current.height = Math.max(height(current.left), height(current.right)) + 1;
    	return current;
	}
	
	/** 
	 * @param current: the given node
	 * @return height: the height of that node.
	 */
	@SuppressWarnings("unchecked")
	private int height(BSTNode current) {
		if (current == null) {
			return -1;
	    } else {
	    	return ((AVLNode) current).height;
	    }
	}
	
	/** rotate the appropriate nodes to make sure that the AVL
	 *  tree is balanced (the two children's heights differs no larger than 1).
	 *  The imbalance is caused by inserting in the left child of left subtree 
	 *  of the current node.
	 * @param current: the node that occurs an imbalance.
	 * @modifies the tree by rotating it.
	 * @return the node after rotation.
	 */
	@SuppressWarnings("unchecked")
	private AVLNode rotateLeftLeft(BSTNode current) {
		BSTNode left = current.left;
		BSTNode leftRight = left.right;
		AVLNode newCurrent = (AVLNode) left; 
		current.left = leftRight;
		newCurrent.right = current;
		
		AVLNode temp = (AVLNode) current;
		temp.height = Math.max(height(current.right), height(current.left)) + 1;
		newCurrent.height = Math.max(height(newCurrent.left), temp.height) + 1;
		return newCurrent;
	}
	
	/** rotate the appropriate nodes to make sure that the AVL
	 *  tree is balanced (the two children's heights differs no larger than 1).
	 *  The imbalance is caused by inserting in the right child of left subtree 
	 *  of the current node.
	 * @param current: the node that occurs an imbalance.
	 * @modifies the tree by rotating it.
	 * @return the node after rotation.
	 */
	private AVLNode rotateLeftRight(BSTNode current) {
		current.left = rotateRightRight(current.left);
		return rotateLeftLeft(current);
	}
	
	/** rotate the appropriate nodes to make sure that the AVL
	 *  tree is balanced (the two children's heights differs no larger than 1).
	 *  The imbalance is caused by inserting in the left child of right subtree 
	 *  of the current node.
	 * @param current: the node that occurs an imbalance.
	 * @modifies the tree by rotating it.
	 * @return the node after rotation.
	 */
	private AVLNode rotateRightLeft(BSTNode current) {
		current.right = rotateLeftLeft(current.right);
		return rotateRightRight(current);
	}
	
	/** rotate the appropriate nodes to make sure that the AVL
	 *  tree is balanced (the two children's heights differs no larger than 1).
	 *  The imbalance is caused by inserting in the right child of right subtree 
	 *  of the current node.
	 * @param current: the node that occurs an imbalance.
	 * @modifies the tree by rotating it.
	 * @return the node after rotation.
	 */
	@SuppressWarnings("unchecked")
	private AVLNode rotateRightRight(BSTNode current) {
		BSTNode right = current.right;
		BSTNode rightLeft = right.left;
		AVLNode newCurrent = (AVLNode) right;
		current.right = rightLeft;
		newCurrent.left = current;
		AVLNode temp = (AVLNode) current;
		
		temp.height = Math.max(height(current.right), height(current.left)) + 1;
		newCurrent.height = Math.max(height(newCurrent.right), temp.height) + 1;
		return newCurrent;
	}

}