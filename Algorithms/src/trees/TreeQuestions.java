package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeQuestions
{
	/**
	 * Find the maximum element in a binary tree
	 */
	public static Integer getMaxElemBTree(TreeNode<Integer> root) {
		int maxElement = -1;
		if(root != null) {
			int left = getMaxElemBTree(root.getLeft());
			int right = getMaxElemBTree(root.getRight());
			if(left > right) {
				maxElement = left;
			}
			else {
				maxElement = right;
			}
			return (maxElement > root.getData()) ? maxElement : root.getData();
		}
		return maxElement;
	}
	
	/** 
	 * Find if a element with the given data is present in the tree or not.
	 * Return 1 if found, 0 otherwise
	 */
	public static int findElementInBTree(TreeNode<Integer> bTree, int dataToSearch) {
		if(bTree == null) { 
			return 0;
		}
		if(bTree.getData() == dataToSearch) {
			return 1;
		}
		else {
			if(findElementInBTree(bTree.getLeft(), dataToSearch) == 1) {
				return 1;
			}
			else {
				return (findElementInBTree(bTree.getRight(), dataToSearch));
			}
		}
	}
	
	/**
	 * Calculate the size of a binary tree (Number of nodes in the binary tree)
	 */
	public static int sizeOfBinaryTree(TreeNode<Integer> bTree) {
		if(bTree == null ) {
			return 0;
		}
		return sizeOfBinaryTree(bTree.getLeft()) + 1 + sizeOfBinaryTree(bTree.getRight());
	}
	
	/**
	 * Print the elements of the tree in reverse order.
	 * Algo: Use level order traversal. Pop elements and push them on a stack.
	 * 		 Pop all elements from the stack to get the elements in the reverse order.
	 */
	public static void levelOrderTraversalInReverse(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return;
		}
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		Stack<TreeNode<Integer>> stack = new Stack<>();
		queue.add(bTree);
		while(! queue.isEmpty()) {
			TreeNode<Integer> data = queue.poll();
			stack.push(data);
			
			if(data.getLeft() != null) {
				queue.add(data.getLeft());
			}
			if(data.getRight() != null) {
				queue.add(data.getRight());
			}
		}
		while(! stack.isEmpty()) {
			System.out.println(stack.pop().getData());
		}
	}
	
	/**
	 * Find the height of a binary tree (recursively)
	 */
	public static int getHeightOfBTree(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return 0;
		}
		int left = getHeightOfBTree(bTree.getLeft()) + 1;
		int right = getHeightOfBTree(bTree.getRight()) + 1;

		if(left > right) {
			return left;
		}
		else {
			return right;
		}
	}

	/**
	 * Traverse each level of the tree and keep pushing them to the queue.
	 * End of each level should be marked by a NULL element in the queue.
	 * Each time a NULL is popped from the queue, the value of level should be incremented by 1.
	 * Continue doing this till the queue is empty.
	 * @param bTree
	 * @return
	 */
	public static int getHeightOfBTreeNonRecursive(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return 0;
		}
		
		int level = 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		// Push the root element to the queue
		queue.add(bTree);
		// Mark the end of the level by inserting a NULL
		queue.add(null);
		
		while(! queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if(node == null) { // We have reached the end of one level
				if(! queue.isEmpty()) { // To avoid last NULL recursion
					queue.add(null); // Pushed a null to mark the end of the level 
									//since we already pushed left and right nodes of the level.
				}
				++level;
			}
			else {
				if(node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if(node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
		}
		
		return level;
	}
	
	/**
	 * Find the number of leaf nodes in a tree
	 * @param bTree
	 */
	public static int numLeafNodes(TreeNode<Integer> bTree ) {
		if(bTree == null) {
			return 0;
		}
		else if(bTree.getLeft() == null && bTree.getRight() == null) {
			return 1;
		}
		else {
			int leaves = 0;
			if(bTree.getLeft() != null) {
				leaves += numLeafNodes(bTree.getLeft());
			}
			if(bTree.getRight() != null) {
				leaves += numLeafNodes(bTree.getRight());
			}
			return leaves;	
		}
	}
	
	public static int numLeafNodesNonRecursive(TreeNode<Integer> bTree ) {
		if(bTree == null) {
			return 0;
		}
		int leaves = 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(bTree);
		while(! queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if( node.getLeft() == null && node.getRight() == null ) { // Leaf node
				++leaves;
			}
			else {
				if(node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				if(node.getRight() != null) {
					queue.add(node.getRight());
				}
			}
		}
		
		return leaves;
	}
	
	/**
	 * Finding the number of full nodes in a tree.
	 * Full node => A node that has both left and right child node.
	 */
	public static int numFullNodes(TreeNode<Integer> bTree) {
		if (bTree == null) {
			return 0;
		}

		
		if ( bTree.getLeft() == null || bTree.getRight() == null ) {
			return 0;
		}
		
		return 1 + numFullNodes(bTree.getLeft()) + numFullNodes(bTree.getRight());
	}
	
	public static int numFullNodesNonRecursive(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return 0;
		}
		
		int numFullNodes = 0;		
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(bTree);
		while(!queue.isEmpty()) {
			TreeNode<Integer> node = queue.poll();
			if(node.getLeft() != null && node.getRight() != null) {
				++numFullNodes;
			}
			if(node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			if(node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
		
		return numFullNodes;
	}
}

class TreeQuestionsMain {
	
	public static void main(String[] args)
	{
		TreeNode<Integer> bTree = createTree();
		System.out.println("Maximum Element : " + TreeQuestions.getMaxElemBTree(bTree));
		System.out.println(TreeQuestions.findElementInBTree(bTree, 5));
		System.out.println(TreeQuestions.findElementInBTree(bTree, 10));
		System.out.println("Size of tree : " + TreeQuestions.sizeOfBinaryTree(bTree));
		TreeQuestions.levelOrderTraversalInReverse(bTree);
		System.out.println("Height of the tree is : " + TreeQuestions.getHeightOfBTree(bTree));
		System.out.println("Height of the tree is : " + TreeQuestions.getHeightOfBTreeNonRecursive(bTree));
		System.out.println("Number of leaf nodes : " + TreeQuestions.numLeafNodes(bTree));
		System.out.println("Number of leaf nodes : " + TreeQuestions.numLeafNodesNonRecursive(bTree));
		System.out.println("Number of full nodes : " + TreeQuestions.numFullNodes(bTree));
		System.out.println("Number of full nodes : " + TreeQuestions.numFullNodesNonRecursive(bTree));
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
