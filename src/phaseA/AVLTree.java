package phaseA;
import providedCode.*;


/**
 * TODO: REPLACE this comment with your own as appropriate.
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override incCount method such that it creates AVLNode instances
 *    instead of BSTNode instances.
 * 3. Do NOT "replace" the left and right fields in BSTNode with new
 *    left and right fields in AVLNode.  This will instead mask the
 *    super-class fields (i.e., the resulting node would actually have
 *    four node fields, with code accessing one pair or the other
 *    depending on the type of the references used to access the
 *    instance).  Such masking will lead to highly perplexing and
 *    erroneous behavior. Instead, continue using the existing BSTNode
 *    left and right fields.
 * 4. Cast left and right fields to AVLNode whenever necessary in your
 *    AVLTree. This will result a lot of casts, so you can also follow
 *    the hints given during section to reduce the number of casts.
 * 5. Do NOT override the dump method of BinarySearchTree & the toString
 *    method of DataCounter. They are used for grading.
 * TODO: Develop appropriate JUnit tests for your AVLTree (TestAVLTree
 * in testA package).
 */
public class AVLTree<E> extends BinarySearchTree<E> {

	public AVLTree(Comparator<? super E> c) {
		super(c);
	}
	
	public void incCount(E data) {
		overallRoot = insert((AVLNode) overallRoot, data);
	}
	
	private AVLNode insert(AVLNode current, E data) {
		if (current == null) {
			current = new AVLNode(data);
			return current;
		}
		AVLNode left = (AVLNode) current.left;
		AVLNode right = (AVLNode) current.right;
		int cmp = comparator.compare(data, current.data);
    	if(cmp == 0) {            // a. Current node is a match
    		current.count++;
        }else if(cmp < 0) {       // b. Data goes left of current node
        	
        	if (left.height - right.height > 1) {

        		if (comparator.compare(data, left.left.data) < 0) {
        			current = rotateLeftLeft(current);
        		} else {
        			current = rotateLeftRight(current);
        		}
            	left = (AVLNode) insert(left, data);
        	} 
        }else{                    // c. Data goes right of current node
  
        	if (right.height - left.height > 1) {
        		if (comparator.compare(data, left.left.data) < 0) {
        			current = rotateRightLeft(current);
        		} else {
        			current = rotateRightRight(current);
        		}
        	} 
        	right = (AVLNode) insert(right, data);
        }
    	current.height = Math.max(left.height, right.height) + 1;
    	return current;
	}
	
	private AVLNode rotateLeftLeft(AVLNode current) {
		AVLNode left = (AVLNode) current.left;
		AVLNode newCurrent = left; 
		AVLNode leftRight = (AVLNode) left.right;
		current.left = leftRight;
		newCurrent.right = current;
		return newCurrent;
	}
	
	private AVLNode rotateLeftRight(AVLNode current) {
		current.left = rotateRightRight((AVLNode) current.left);
		return rotateLeftLeft(current);
	}
	
	private AVLNode rotateRightLeft(AVLNode current) {
		current.right = rotateLeftLeft((AVLNode) current.right);
		return rotateRightRight(current);
	}
	
	private AVLNode rotateRightRight(AVLNode current) {
		AVLNode right = (AVLNode) current.right;
		AVLNode rightLeft = (AVLNode) right.left;
		AVLNode newCurrent = right;
		current.right = (AVLNode) rightLeft;
		newCurrent.left = current;
		return newCurrent;
	}
	
	protected class AVLNode extends BSTNode {
		int height;
		
		public AVLNode(E data) {
			super(data);
			this.height = 0;
		}
	}

}
