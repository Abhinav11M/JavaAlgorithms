package trees.binarytrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import trees.TreeNode;

public class TreeTraversal
{
	// ====== Recursive Algorithms ======
	/**
	 * Traverse the left node -> root node -> right node
	 * 
	 * @param bTree
	 */
	public static <T> void inOrderTraversal(TreeNode<T> bTree)
	{
		if (bTree != null)
		{
			inOrderTraversal(bTree.getLeft());
			System.out.println(bTree.getData());
			inOrderTraversal(bTree.getRight());
		}
	}

	/**
	 * Traverse the root node -> left node -> right node
	 * 
	 * @param bTree
	 */
	public static <T> void preOrderTraversal(TreeNode<T> bTree)
	{
		if (bTree != null)
		{
			System.out.println(bTree.getData());
			preOrderTraversal(bTree.getLeft());
			preOrderTraversal(bTree.getRight());
		}
	}

	/**
	 * Traverse the left node -> right node -> root node
	 * 
	 * @param bTree
	 */
	public static <T> void postOrderTraversal(TreeNode<T> bTree)
	{
		if (bTree != null)
		{
			postOrderTraversal(bTree.getLeft());
			postOrderTraversal(bTree.getRight());
			System.out.println(bTree.getData());
		}
	}
	// =================================

	// ====== Non-Recursive Algorithms ======

	public static <T> void inOrderTraversalNonRecursive(TreeNode<T> bTree)
	{
		Stack<TreeNode<T>> stack = new Stack<>();
		while (true)
		{
			// Traverse the left subtree and push all the nodes to the stack
			while (bTree != null)
			{
				stack.push(bTree);
				bTree = bTree.getLeft();
			}
			// pushed all the nodes of the left subtree
			if (stack.isEmpty())
			{
				break;
			}
			TreeNode<T> curNode = stack.pop();
			System.out.println(curNode.getData());
			bTree = curNode.getRight(); // Set the head so the left subtree of
										// this right subtree is again traversed
										// using the stack.
		}
	}

	/**
	 * We need to keep the track of the root node. So when processed, push that
	 * to a stack. pop the head to move to the right subtree
	 * 
	 * @param bTree
	 */
	public static <T> void preOrderTraversalNonRecursive(TreeNode<T> bTree)
	{
		Stack<TreeNode<T>> stack = new Stack<>();
		// Print the left subtree pushing the nodes in a stack.
		while (true)
		{
			// Traverse the left subtree
			while (bTree != null)
			{
				System.out.println(bTree.getData());
				stack.push(bTree);
				bTree = bTree.getLeft();
			}
			// If left subtree is exhausted, move to the next right subtree
			if (stack.empty())
			{
				break;
			}

			bTree = stack.pop(); // get the latest root node inserted in the
									// stack
			bTree = bTree.getRight(); // Reposition the head to the right
										// subtree, so that this gets traversed
										// in the same fashion.
		}
	}

	/**
	 * <a>https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack</a>
	 * @param bTree
	 */
	public static <T> void postOrderTraversalNonRecursive(TreeNode<T> bTree)
	{
		// Return if the tree is empty
		if(bTree == null) {
			return;
		}
		Stack<TreeNode<T>> stack = new Stack<>();
		TreeNode<T> previous = null;
		stack.push(bTree);
		
		while(!stack.isEmpty()) {
			TreeNode<T> current = stack.peek();
			if(previous == null || previous.getLeft() == current || previous.getRight() == current) {
				if(current.getLeft() != null) {
					stack.push(current.getLeft());
				}
				else if(current.getRight() != null) {
					stack.push(current.getRight());
				}
				else {
					// Its a left node with no left of right node
					System.out.println(stack.pop().getData());
				}
			}
			else if(current.getLeft() == previous) { // Travelling from left to root; right subtree is still remaining
				if(current.getRight() != null) {
					stack.push(current.getRight());
				}
				else { // No right subtree. Pop the root
					System.out.println(stack.pop().getData());
				}
			}
			else if(current.getRight() == previous) { // Right subtree already traversed. Pop the root
				System.out.println(stack.pop().getData());
			}
			previous = current;
 		}
	}

	/**
	 * Push the root to a queue.
	 * While queue is not empty, pop and print the the push the left and right of the node back to the queue
	 * Continue this while queue is not empty. 
	 * @param bTree
	 */
	public static <T> void levelOrderTraversal(TreeNode<T> bTree) {
		if(bTree == null) {
			return;
		}
		
		Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.add(bTree);
		while(! queue.isEmpty()) {
			TreeNode<T>data = queue.poll();
			System.out.println(data.getData());
			if(data.getLeft() != null) {
				queue.add(data.getLeft());
			}
			if(data.getRight() != null) {
				queue.add(data.getRight());
			}
		}
	}
	
	// ==== Morris algorithms for non-recursive tree traversal ====
	/**
	 * Algo:
	 * <p>
	 * 	Keep traversing until the root becomes null. 
	 * 	We need to get back to root, after traversing the left subtree.
	 * 	If the the left subtree does not exist, traverse the root node and then the right subtree.
	 * 	Else, find the Inorder predecessor of the node and connect the inorder predecessor's node->right to current node.
	 * 	If while finding the inorder predecessor, we find the node itself, it means that the link has already been created 
	 * 	and needs to be removed
	 * 	<a>https://www.youtube.com/watch?v=wGXB9OWhPTg</a>
	 * @param bTree
	 */
	public static<T> void morrisInOrderTraversal(TreeNode<T> bTree ) {
		while(bTree != null) {
			if(bTree.getLeft() != null) {
				TreeNode<T> inOrderPredecessor = getInOrderPredecessor(bTree);
				if(inOrderPredecessor.getRight() != null) { // Since we created this link, now should be removed.
					inOrderPredecessor.setRight(null);
					System.out.println(bTree.getData());
					bTree = bTree.getRight(); // move to right subtree.
				}
				else { // No link, create the link to come back
					inOrderPredecessor.setRight(bTree);
					bTree = bTree.getLeft();
				}
			}
			else { // No left subtree, just traverse the root and then move to the right subtree.
				System.out.println(bTree.getData());
				bTree = bTree.getRight();
			}
			
		}
	}

	public static<T> void morrisPreOrderTraversal(TreeNode<T> bTree ) {
		while(bTree != null) {
			// Visit the root
			// If left subtree is not present, move to the right subtree
			if(bTree.getLeft() == null) {
				System.out.println(bTree.getData());
				bTree = bTree.getRight();
			}
			else {
				// Find the InOrder Predecessor
				TreeNode<T> inOrderPredecessor = getInOrderPredecessor(bTree);
				if(inOrderPredecessor.getRight() == bTree ) {
					// Link is already created and traversed. Delete the link and move to the right.
					inOrderPredecessor.setRight(null);
					bTree = bTree.getRight();
				}
				else { // Link is not created. Create the link from predecessor to the node
					System.out.println(bTree.getData());
					inOrderPredecessor.setRight(bTree);
					bTree = bTree.getLeft();
				}
			}
		}
	}
	
	private static<T> TreeNode<T> getInOrderPredecessor(TreeNode<T> bTree) {
		TreeNode<T> predecessor = bTree.getLeft();
		while(predecessor.getRight() != null) {
			if(predecessor.getRight() == bTree) {
				return predecessor;
			}
			predecessor = predecessor.getRight();
		}

		return predecessor;
	}
}

class TestTraversal
{
	public static void main(String[] args)
	{
		// Create binary Tree
		TreeNode<Integer> bTree = createTree();
//		System.out.println("Recursive InOrder Traversal");
//		TreeTraversal.inOrderTraversal(bTree);
//		System.out.println("Non-Recursive InOrder Traversal");
//		TreeTraversal.inOrderTraversalNonRecursive(bTree);
		// System.out.println("Recursive PreOrder Traversal");
		// TreeTraversal.preOrderTraversal(bTree);
		// System.out.println("Non-Recursive PreOrder Traversal");
		// TreeTraversal.preOrderTraversalNonRecursive(bTree);
//		System.out.println("Recursive Post-Order Traversal"); 
//		TreeTraversal.postOrderTraversal(bTree);
//		System.out.println("Non-Recursive Post-Order Traversal");
//		TreeTraversal.postOrderTraversalNonRecursive(bTree);
//		TreeTraversal.levelOrderTraversal(bTree);
//		TreeTraversal.morrisInOrderTraversal(bTree);
		TreeTraversal.morrisPreOrderTraversal(bTree);
	}

	private static TreeNode<Integer> createTree()
	{
		TreeNode<Integer> bTree = new TreeNode<>(1);
		bTree.setLeft(new TreeNode<Integer>(2));
		bTree.setRight(new TreeNode<Integer>(3));
		bTree.getLeft().setLeft(new TreeNode<Integer>(4));
		bTree.getLeft().setRight(new TreeNode<Integer>(5));
		bTree.getRight().setLeft(new TreeNode<Integer>(6));
		bTree.getRight().setRight(new TreeNode<Integer>(7));
		return bTree;
	}
}
