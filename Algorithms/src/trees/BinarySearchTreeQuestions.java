package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
		TreeNode<Integer> successor = ob.findImmediateInOrderSuccessorBST(bst.getRootNode(), 20);
		if(successor != null) {
			System.out.println("Found the successor");
			System.out.println(successor.getData());
		}
		else {
			System.out.println("Couldn't find the successor");
		}
		
		TreeNode<Integer> predecessor = ob.findImmediateInOrderPredecessorBST(bst.getRootNode(), 20);
		if(predecessor != null) {
			System.out.println("Found the successor");
			System.out.println(predecessor.getData());
		}
		else {
			System.out.println("Couldn't find the predecessor");
		}
	}
	
}
