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
