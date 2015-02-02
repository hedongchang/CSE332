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
	
	private class AVLNode extends BSTNode {
		public int height;
		
		public AVLNode(E data) {
			super(data);
			this.height = 0;
		}
	}
    
	/*@effects Constructs a new AVL Tree.*/
	public AVLTree(Comparator<? super E> c) {
		super(c);
	}
	
	/*@param General type data
	  @effects  */
	@SuppressWarnings("unchecked")
	@Override
	public void incCount(E data) {
		overallRoot = insert((AVLNode) overallRoot, data);
	}
	
	public int getHeight() {
		return height(overallRoot);
	}
	
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
	
	@SuppressWarnings("unchecked")
	private int height(BSTNode current) {
		if (current == null) {
			return -1;
	    } else {
	    	return ((AVLNode) current).height;
	    }
	}
	
	//singleRotateRight
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
	
	//doubleRotateRight
	private AVLNode rotateLeftRight(BSTNode current) {
		current.left = rotateRightRight(current.left);
		return rotateLeftLeft(current);
	}
	
	//doubleRotateLeft
	private AVLNode rotateRightLeft(BSTNode current) {
		current.right = rotateLeftLeft(current.right);
		return rotateRightRight(current);
	}
	
	//singleRotateLeft
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