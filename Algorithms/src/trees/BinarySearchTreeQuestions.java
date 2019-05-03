package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.omg.CORBA.INTERNAL;

import linkedlists.DoublyLinkedList;
import linkedlists.Node;
import linkedlists.SinglyLinkedList;
import trees.binarytrees.TreeTraversal;

public class BinarySearchTreeQuestions {

	
	public TreeNode<Integer> createBST() {
		TreeNode<Integer> root = new TreeNode<>(10);
		root.setLeft(new TreeNode<Integer>(6));
		root.setRight(new TreeNode<Integer>(16));
		root.getLeft().setLeft(new TreeNode<Integer>(4));
		root.getLeft().setRight(new TreeNode<Integer>(9));
		root.getLeft().getRight().setLeft(new TreeNode<Integer>(7));
		root.getRight().setLeft(new TreeNode<Integer>(13));
		
		return root;
	}
	
	/**
	 * Find if a element exists in a BST or not
	 */
	public TreeNode<Integer> findElement(TreeNode<Integer> bSTree, int data) {
		if(bSTree == null) {
			return null;
		}
		
		if(data > bSTree.getData()) { // Search in right
			return findElement(bSTree.getRight(), data);
		}
		else if(data < bSTree.getData()) { // Search in left
			return findElement(bSTree.getLeft(), data);
		}
		
		// Return the node where data is found
		return bSTree;
	}
	
	/**
	 * Find the element with the minimum value
	 * Algo: Smallest element of a BST is the left most element with no left node.
	 */
	public TreeNode<Integer> findMin(TreeNode<Integer> bSTree) {
		if(bSTree == null) {
			return null;
		}
		if(bSTree.getLeft() == null) {
			return bSTree;
		}
		else {
			return findMin(bSTree.getLeft());
		}
	}
	
	/**
	 * Find the element with the maximum value
	 * Algo: Largest element of a BST is the right most element with no right node.
	 */
	public TreeNode<Integer> findMax(TreeNode<Integer> bSTree) {
		if(bSTree == null) {
			return null;
		}
		if(bSTree.getRight() == null) {
			return bSTree;
		}
		else {
			return findMin(bSTree.getRight());
		}
	}
	
	/**
	 * Find the Inorder Successor
	 * <b>algo:<b>
	 * <p>
	 * 		Find the element.
	 * 		If the right subtree is present, successor is the leftmost left node of the immediate right subtree
	 * 		Else, if the right subtree is not present:
	 * 			If the node is the left node to the parent, then the parent is the immediate successor.
	 * 			Else, parent is next immediate big ancestor to the node.
	 * 			To find the next big ancestor, we need to keep a track of the path traversed. The next just bigger value in the path
	 * 			will be the next successor of the node. If no such value exist in the path traversed, then it is the right most leaf
	 * 			node which does not have any successor.
	 * </p>
	 */
	public TreeNode<Integer> findImmediateInOrderSuccessorBST(TreeNode<Integer> root, int dataToFindSuccessorOf) {
		return findImmediateInOrderSuccessorBST(root, root, dataToFindSuccessorOf, new Stack<TreeNode<Integer>>());
	}
	
	private TreeNode<Integer> findImmediateInOrderSuccessorBST(
			TreeNode<Integer> root, 
			TreeNode<Integer> parent, 
			int dataToFindSuccessorOf, 
			Stack<TreeNode<Integer>> pathTraveresed) {
		if(root == null && parent == null) {
			return null;
		}
		
		if(root.getData() == dataToFindSuccessorOf) {
			if(root.getRight() == null) { // No right subtree
				if(parent.getLeft() == root) { // If it is a left node, parent will be the successor
					return parent;
				}
				else { // If it is a right node, parent will be the next in the parent chain
					// Find the next higher value than the data in the path traversed.
					// If there is no higher value, then that means that it is the rightmost leaf node. So there is no successor
					while(!pathTraveresed.isEmpty()) {
						TreeNode<Integer> retNode = pathTraveresed.pop();
						if(retNode.getData() > root.getData()) {
							return retNode;
						}
					}
					return null;
				}
			}
			else {// Right subtree present
				root = root.getRight();
				// Now get the leftmost node
				TreeNode<Integer> temp = root;
				while(temp.getLeft() != null) {
					temp = temp.getLeft();
				}
				return temp;
			}
		}

		else if(dataToFindSuccessorOf > root.getData()) { // Find in the right subtree
			pathTraveresed.add(root);
			return findImmediateInOrderSuccessorBST(root.getRight(), root, dataToFindSuccessorOf, pathTraveresed);
		}
		else { // Find in the right subtree
			pathTraveresed.add(root);
			return findImmediateInOrderSuccessorBST(root.getLeft(), root, dataToFindSuccessorOf, pathTraveresed);
		}
	}

	/**
	 * Find the InOrder predecessor.
	 * <b>algo:<b>
	 * <p>
	 * 		Find the element.
	 * 		If the left subtree is present, then the right most node of the immediate left node is the successor.
	 * 		If left subtree is not present:
	 * 			If found node is the right child of the parent, then parent is the predecessor.
	 * 			If the found node is the left child of the parent, then we need to find the immediate smaller number in the list
	 * 			of ancestors. So we keep a track of the path traced in a stack. The first node in the stack (top to bottom) with
	 * 			a smaller value that the data is the successor.
	 * </p>
	 * @param root : Root node.
	 * @param data : Data to find the InOrder predecessor of.
	 * @return : InOrder predecessor as a TreeNode<Integer>.
	 */
	public TreeNode<Integer> findImmediateInOrderPredecessorBST(TreeNode<Integer> root, int data) {
		return findImmediateInOrderPredecessorBST(root, root, data, new Stack<TreeNode<Integer>>());
	}
	
	public TreeNode<Integer> findImmediateInOrderPredecessorBST(
			TreeNode<Integer> root, 
			TreeNode<Integer> parent, 
			int dataToFindPredecessorOf, 
			Stack<TreeNode<Integer>> pathTraversed) { 
		if(root == null) {
			return null;
		}
		
		if(root.getData() == dataToFindPredecessorOf) {
			if(root.getLeft() != null) { // Left subtree not null. So the right most node of the immediate left node is the predecessor.
//				return root.getLeft();
				TreeNode<Integer> retVal = root.getLeft();
				while(retVal.getRight() != null) {
					retVal = retVal.getRight();
				}
				return retVal;
			}
			else {
				if(parent.getRight() == root) { // If it is a right node to the parent, then parent is the predecessor.
					return parent;
				}
				while(!pathTraversed.isEmpty()) { // Find a smaller value in the stack
					TreeNode<Integer> retVal = pathTraversed.pop();
					if(retVal.getData() < root.getData()) {
						return retVal;
					}
				}
				return null;
			}
		}
		else if(root.getData() > dataToFindPredecessorOf) { // Find in the left subtree
			pathTraversed.add(root);
			return findImmediateInOrderPredecessorBST(root.getLeft(), root, dataToFindPredecessorOf, pathTraversed);
		}
		else { // Find in the left subtree
			pathTraversed.add(root);
			return findImmediateInOrderPredecessorBST(root.getRight(), root, dataToFindPredecessorOf, pathTraversed);
		}
	}
	
	/**
	 * Delete node from a BST
	 * <p>
	 * 	Algo:
	 * 		Case1: If the node to be deleted is a leaf node => Just delete it by setting the parent left/right to null.
	 * 		Case2: If the node to be deleted has one child => Just set the child of the node to be deleted as the child of its parent.
	 * 		Case3: If the node to be deleted has both the child nodes => Replace the node to be deleted with the largest node in the left subtree.
	 * 																		Delete the node used for replacing. The largest node will always be a right node.
	 * 																		So if the largest node is a leaf node, just simply set its parent to point to null.
	 * 																		If it has a left child, then the parent of the largest node now should point to this
	 * 																		child. So rather call, delete recursively on this node and everything should 
	 * 																		be taken care of.
	 * </p>
	 * @param args
	 */
	public void deleteElementFromBST(TreeNode<Integer> root, int elementToBeDeleted) {
		deleteElementFromBST(root, null, elementToBeDeleted);
	}
	
	private void deleteElementFromBST(TreeNode<Integer> root, TreeNode<Integer> parent, int elementToBeDeleted) {
		// Find the element
		if(root.getData() == elementToBeDeleted) {
			deleteElementFromBST(root, parent);
		}
		else if(elementToBeDeleted > root.getData()) {
			deleteElementFromBST(root.getRight(), root, elementToBeDeleted); // Find in the right subtree
		}
		else {
			deleteElementFromBST(root.getLeft(), root, elementToBeDeleted); // Find in the left subtree
		}	
	}
	
	private void deleteElementFromBST(TreeNode<Integer> root, TreeNode<Integer> parent) {
		// Leaf node check
		if (root.getLeft() == null && root.getRight() == null) {
			if (parent == null) {
				root = null;
				return;
			}
			else {
				if (parent.getRight() == root) {
					parent.setRight(null);
				}
				else {
					parent.setLeft(null);
				}
			}
		}
		// Single node present check
		else if (root.getLeft() == null && root.getRight() != null) {
			if (parent.getRight() == root) {
				parent.setRight(root.getRight());
			}
			else {
				parent.setLeft(root.getRight());
			}
		} 
		else if (root.getRight() == null && root.getLeft() != null) {
			if (parent.getRight() == root) {
				parent.setRight(root.getLeft());
			}
			else {
				parent.setLeft(root.getLeft());
			}
		}
		// When both left and right nodes are present
		else {
			TreeNode<Integer> maxDataNode = findMax(root.getLeft());
			int dataToSet = maxDataNode.getData();
			// TODO: This may be optimized as we are deleting the largest node recursively from the root again (But not the actual root).
			deleteElementFromBST(root, maxDataNode.getData());
			root.setData(dataToSet);
		}
	}
	
	/**
	 * Find the least common ancestor of two given nodes in a binary search tree.
	 * @param root: Root node
	 * @param node1: Node1
	 * @param node2: Node2
	 * @return: Least common ancestor of node1 and node2
	 * @algo:
	 * 	<p>
	 * 		Start traversing from the root.
	 * 		If the root data is greater than both the nodes data, then the LCA is in the left subtree.
	 * 		If the root data is less than both the nodes data, then the LCA is in the right subtree.
	 * 		Otherwise, the root itself is the LCA
	 * 	</p>
	 */
	public TreeNode<Integer> findLCAOfBST(TreeNode<Integer> root, Integer node1, Integer node2) {
		if(root == null) {
			return null;
		}
		
		if(node1 > root.getData() && node2 > root.getData()) { // LCA lies to the right
			return findLCAOfBST(root.getRight(), node1, node2);
		}

		if(node1 < root.getData() && node2 < root.getData()) { // LCA lies to the right
			return findLCAOfBST(root.getLeft(), node1, node2);
		}
		
		return root;
	}
	
	public int findMax1(TreeNode<Integer> root) {
		return 100;
	}

	/**
	 *  Check if a binary tree is a binary search tree or not.
	 * @param root: Root node
	 * @return: True if the tree is a binary search tree else false.
	 */
	public boolean isBinarySearchTree(TreeNode<Integer> root) {
		if(root == null) {
			return true;
		}
		
		// If any of the nodes in the left subtree is greater than the root node data, return false
		if( root.getLeft() != null && root.getData() < findMax(root.getLeft()).getData()) {
			return false;
		}
		
		// If any of the nodes in the right subtree is less than the root node data, return false
		if( root.getRight() != null && (root.getData() > findMin(root.getRight()).getData()) ) {
			return false;
		}
		
		// Check recursively for left and right subtree
		if( !isBinarySearchTree(root.getLeft()) || !isBinarySearchTree(root.getRight()) ) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Create a circular doubly linked list from a BST
	 * @param root: Root node of the tree.
	 * @algo: 
	 * 		<p>
	 * 			Create left and right circular linked lists recursively.
	 * 			Join the left and root and then join the resultant with right.
	 * 		</p>
	 */
	public TreeNode<Integer> createCDLLFromBST(TreeNode<Integer> root) {
		if(root == null) {
			return null;
		}
		
		TreeNode<Integer> left = createCDLLFromBST(root.getLeft());
		TreeNode<Integer> right = createCDLLFromBST(root.getRight());
	
		// Create a circular linked list for root
		root.setLeft(root);
		root.setRight(root);
		
		return mergeCDLLS(mergeCDLLS(left, root), right);
	}
	
	private TreeNode<Integer> mergeCDLLS(TreeNode<Integer> left, TreeNode<Integer> right) {
		if(left == null) {
			return right;
		}
		if(right == null) {
			return left;
		}
		
		TreeNode<Integer> leftLast = left.getLeft();
		TreeNode<Integer> rightLast = right.getLeft();
		
		leftLast.setRight(right);
		right.setLeft(leftLast);

		left.setLeft(rightLast);
		rightLast.setRight(left);
		
		return left;
	}

	private TreeNode<Integer> createBinaryTree() {
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.setLeft(new TreeNode<Integer>(12));
		root.setRight(new TreeNode<Integer>(15));
		root.getLeft().setLeft(new TreeNode<Integer>(25));
		root.getLeft().setRight(new TreeNode<Integer>(30));
		root.getRight().setLeft(new TreeNode<Integer>(36));
		root.getRight().setRight(new TreeNode<Integer>(44));
		return root;
	}
	
	private DoublyLinkedList<Integer> createSortedLinkedList() {
		DoublyLinkedList<Integer> dList = new DoublyLinkedList<Integer>(1);
		dList.addElement(2);
		dList.addElement(3);
		dList.addElement(4);
		dList.addElement(5);
		dList.addElement(6);
		dList.addElement(7);
		return dList;
	}

	/**
	 * Take a doubly linked list and create a balanced binary search tree.
	 * @param dll: Head of the doubly linked list.
	 * @return: head of the balanced binary search tree.
	 */
	public TreeNode<Integer> createBalBTreeFromDLL(DoublyLinkedList<Integer> dll) {
		// Number of nodes should be (2^n - 1)
		if(! isBalBTreeContructible(dll.countElement()) ) {
			return null;
		}
		
		return createBalBTreeFromDLL(dll.getHead());
	}

	private TreeNode<Integer> createBalBTreeFromDLL(TreeNode<Integer> head) {
		if(head.getRight() == null) { // Only one node is available i.e root node
			return head;
		}
		
		// Find the middle
		TreeNode<Integer> middleNode = getMiddleNode(head);
		TreeNode<Integer> left = head;
		TreeNode<Integer> right = middleNode.getRight();
		middleNode.getLeft().setRight(null);
		middleNode.setLeft(null);
		middleNode.getRight().setLeft(null);
		middleNode.setRight(null);
		middleNode.setLeft(createBalBTreeFromDLL(left));
		middleNode.setRight(createBalBTreeFromDLL(right));
		return middleNode;
	}

	private TreeNode<Integer> getMiddleNode(TreeNode<Integer> head) {
		if(head == null) {
			return null;
		}

		TreeNode<Integer> endNode = head;
		int counter = 1;

		while(endNode.getRight() != null) {
			++counter;
			endNode = endNode.getRight();
		}
		
		if(counter%2 != 1) {
			throw new RuntimeException("Cannot find the middle of a linked list with even number of nodes : " + counter);
		}
		
		while(head != endNode) {
			head = head.getRight();
			endNode = endNode.getLeft();
		}
		
		return endNode;
	}

	/**
	 * Check if count is 2^n -1 or not
	 * @param countElement
	 * @return
	 */
	private boolean isBalBTreeContructible(int count) {
		if(count == 1) { //Only root node
			return true;
		}
		
		++count;
		while(count%2 == 0) {
			count /= 2;
		}
		
		if(count == 1) {
			return true;
		}
		
		return false;
	}

	/**
	 * Return the inorder predecessor. If the right node of a node is the root node itself, then return that node itself.
	 * @param bTree
	 * @return
	 */
	private TreeNode<Integer> getInOrderPredecessor(TreeNode<Integer> bTree) {
		TreeNode<Integer> inOrderPredecessor = bTree.getLeft();

		while(inOrderPredecessor.getRight() != null && inOrderPredecessor.getRight() != bTree) {
			inOrderPredecessor = inOrderPredecessor.getRight();
		}

		return inOrderPredecessor;
	}	
	/**
	 * Use Morris InOrder traversal and keep a count of the nodes.
	 * @param bTree
	 * @param k
	 * @return
	 */
	public TreeNode<Integer> getKthSmallestElement(TreeNode<Integer> bTree, int k) {
		if(bTree == null) {
			return null;
		}
		int count = 0;
		TreeNode<Integer> nodeToReturn = bTree;
		while(bTree != null) {
			if(count == k) {
				break;
			}

			if(bTree.getLeft() == null) {
				nodeToReturn = bTree;
				++count;
				bTree = bTree.getRight();
			}
			else {
				TreeNode<Integer> inOrderPredecessor = getInOrderPredecessor(bTree);
				if(inOrderPredecessor.getRight() == bTree) { // Link is already created. Break this link.
					nodeToReturn = bTree;
					++count;
					inOrderPredecessor.setRight(null);					
					bTree = bTree.getRight();
				}
				else { // Create the link from the inOrderPredecessor to the node.
					inOrderPredecessor.setRight(bTree);
					bTree = bTree.getLeft();
				}
			}
		}
		return nodeToReturn;
	}
	
	
	/**
	 * Find the floor node in a BST.
	 * @param root : Root node of the tree
	 * @param num : Number for which floor need to be found
	 * @return : Floor node
	 * <p> 
	 * 	If the root node is equal to num, root node itself is the floor.
	 * 	If num is less that root node, it means that floor will always be in the left subtree (Since floor has to be less than or equal to num).
	 * 	So recursively search in the left subtree.
	 * 	If num is greater that root, then there are two possibilities now, either the root node or the right subtree.
	 * 	So we need to pick the closer one. The closer one will be one with the higher value. (The right subtree will also give a floor value and the other 
	 * 	floor is the root.) Both are less than num, but the number with higher value will be closer to num. So we pick the higher value.
	 * </p>
	 */
	public int getFloorOfBST(TreeNode<Integer> root, int num) {
		if(root == null) {
			return Integer.MAX_VALUE;
		}
		
		if(root.getData() == num) {
			return root.getData();
		}
		
		if(num < root.getData()) {
			return getFloorOfBST(root.getLeft(), num);
		}
		
		else {
			int rightFloor = getFloorOfBST(root.getRight(), num);
			return num > rightFloor ? rightFloor : root.getData();
		}
		
	} 
	
	public int getCeilOfBST(TreeNode<Integer> root, int num) {
		if(root == null) {
			return Integer.MIN_VALUE;
		}

		if(root.getData() == num) {
			return root.getData();
		}
		
		if(num < root.getData()) { // Ceil will be between the root and the left subtree
			int leftCeil = getCeilOfBST(root.getLeft(), num);
			return num < leftCeil ? leftCeil : root.getData();
		}
		else {
			return getCeilOfBST(root.getRight(), num);
		}
	}
	
	
	/**
	 * Print all the numbers between K1 and K2 in a BST
	 * @param args
	 */
	public void bstRangePrinter(TreeNode<Integer> root, int k1, int k2) {
		if(root == null) { 
			return;
		}
		
		if(root.getData() > k1) { // If root->data is greater than k1, print the nodes in the left subtree.
			bstRangePrinter(root.getLeft(), k1, k2);
		}

		if(root.getData() >= k1 && root.getData() <= k2) {
			System.out.println(root.getData());
		}

		if(root.getData() < k2) { // If K2 is greater than root->data, cover the nodes in the right subtree.
			bstRangePrinter(root.getRight(), k1, k2);
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTreeQuestions ob = new BinarySearchTreeQuestions();
//		TreeNode<Integer> bSTree = ob.createBST();
//		TreeNode<Integer> pos = ob.findElement(bSTree, 10);
//		if(pos != null ) {
//			System.out.println("Element found: " + pos.getData());
//		}
//
//		pos = ob.findElement(bSTree, 3);
//		if(pos != null ) {
//			System.out.println("Element found: " + pos.getData());
//		}
//		else {
//			System.out.println("3 not found");
//		}
		
//		System.out.println(ob.findMin(bSTree).getData());
//		System.out.println(ob.findMax(bSTree).getData());
		
		BinarySearchTree bst = new BinarySearchTree();
		bst.addData(20);
		bst.addData(8);
		bst.addData(22);
		bst.addData(4);
		bst.addData(12);
		bst.addData(10);
		bst.addData(14);
		bst.addData(28);
		bst.addData(24);
		
//		TreeTraversal.inOrderTraversal(bst.getRootNode());
//		TreeNode<Integer> successor = ob.findImmediateInOrderSuccessorBST(bst.getRootNode(), 20);
//		if(successor != null) {
//			System.out.println("Found the successor");
//			System.out.println(successor.getData());
//		}
//		else {
//			System.out.println("Couldn't find the successor");
//		}
//		
//		TreeNode<Integer> predecessor = ob.findImmediateInOrderPredecessorBST(bst.getRootNode(), 20);
//		if(predecessor != null) {
//			System.out.println("Found the successor");
//			System.out.println(predecessor.getData());
//		}
//		else {
//			System.out.println("Couldn't find the predecessor");
//		}
		
//		System.out.println("Before Deletion");
//		TreeTraversal.inOrderTraversal(bst.getRootNode());
//		ob.deleteElementFromBST(bst.getRootNode(), 22);
//		System.out.println("After Deletion");
//		TreeTraversal.inOrderTraversal(bst.getRootNode());
	
//		System.out.println(ob.findLCAOfBST(bst.getRootNode(), 10, 14).getData());
//		System.out.println(ob.findLCAOfBST(bst.getRootNode(), 14, 8).getData());
//		System.out.println(ob.findLCAOfBST(bst.getRootNode(), 10, 22).getData());
		
		// ====== Create Doubly Circular linked list from tree Begins =======
		/*TreeNode<Integer> root = ob.createBinaryTree();
//		TreeNode<Integer> t = null;
		TreeNode<Integer> dll = ob.createCDLLFromBST(root);
		TreeNode<Integer> temp = dll;
		while(temp.getRight() != dll) {
			System.out.println(temp.getData());
			temp = temp.getRight();
		}
		System.out.println(temp.getData());*/
		// ====== Create Doubly Circular linked list from tree Ends =======
		
		// ===== Create balanced BST from doubly linked list Begins ========
		/*DoublyLinkedList<Integer> dll = ob.createSortedLinkedList();
		TreeNode<Integer> balBTree = ob.createBalBTreeFromDLL(dll);
		TreeTraversal.inOrderTraversal(balBTree);*/
		// ===== Create balanced BST from doubly linked list Ends ========

		// ===== Find the K-th smallest element of a binary tree ======
//		TreeNode<Integer> node = ob.getKthSmallestElement(bst.getRootNode(), 3);
//		System.out.println(node.getData());
		// ===== Find the K-th smallest element of a binary tree ======
		
		// ==== Find the floor in a BST ==== 
//		System.out.println(ob.getFloorOfBST(bst.getRootNode(), 5));
//		System.out.println(ob.getCeilOfBST(bst.getRootNode(), 5));
		// ==== Find the floor in a BST ==== 
		
		// ==== Print all the numbers in range k1 and k2 ====
		ob.bstRangePrinter(bst.getRootNode(), 8, 27);
		// ==== Print all the numbers in range k1 and k2 ====
	}
}
