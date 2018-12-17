package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import javafx.util.Pair;

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
	
	/*
	 * Find the number of half nodes
	 */
	public static int numHalfNodes(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return 0;
		}
		if(bTree.getLeft() == null && bTree.getRight() != null) {
			return 1 + numHalfNodes(bTree.getRight());
		}
		else if(bTree.getLeft() != null && bTree.getRight() == null) {
			return 1 + numHalfNodes(bTree.getLeft());
		}
		else {
			return numHalfNodes(bTree.getLeft()) + numHalfNodes(bTree.getRight());
		}
	}
	
	/**
	 * Check if two trees are structurally identical.
	 */
	public static boolean isStructurallyIdentical(TreeNode<Integer> bTree1, TreeNode<Integer> bTree2) {
		if(bTree1 == null && bTree2 == null) {
			return true;
		}
		if(bTree1 == null || bTree2 == null) {
			return false;
		}
		
		return bTree1.getData() == bTree2.getData() && isStructurallyIdentical(bTree1.getLeft(), bTree2.getLeft())
				&& isStructurallyIdentical(bTree1.getRight(), bTree2.getRight());
	}
	
	/*
	 * Get the height of the tree
	 */
	public static <T> int heightOfTree(TreeNode<T> bTree) {
		if(bTree == null) {
			return 0;
		}
		
		int left = 1 + heightOfTree(bTree.getLeft());
		int right = 1 + heightOfTree(bTree.getRight());
		return left > right ? left : right;
	}
	
	/**
	 * Get the height of the tree non-recursive
	 * <p>
	 * Algo: <br/>
	 * 		push the root node and a null to mark the end of a level.</br>
	 * 		Pop and push the child nodes.</br>
	 * 		Increment the level if the element popped from the queue is a null (and the queue is not empty).</br>
	 * 		Continue this until the queue is empty.</br>
	 * </p>
	 */
	public static <T> int heightOfTreeNonRecursive(TreeNode<T> bTree) {
		if(bTree == null) {
			return 0;
		}
		Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.add(bTree);
		queue.add(null);
		int level = 0;
	
		while(!queue.isEmpty()) {
			TreeNode<T> item = queue.poll();

			if(item == null) {
				if(!queue.isEmpty()) { // Only add a null if the queue is not empty. If the queue is empty means we have reached the end of the tree.
					queue.add(null); // Mark the end of a level
				}
				++level;
			}
			
			else {
				if(item.getLeft() != null) {
					queue.add(item.getLeft());
				}
				if(item.getRight() != null) {
					queue.add(item.getRight());
				}
			}
		}
		return level;
	}
	
	/**
	 * Calculate the diameter of a tree
	 * <b>diameter = max(height of left subtree, height of right subtree, (height of tree left subtree + height of right subtree + 1))</b>
	 */
	public static int getDiameterOfTree(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return 0;
		}

		int lHeight = heightOfTree(bTree.getLeft());
		int rHeight = heightOfTree(bTree.getRight());

		int diaLeftSubtree = getDiameterOfTree(bTree.getLeft());
		int diaRightSubtree = getDiameterOfTree(bTree.getRight());
		return Math.max(Math.max(diaLeftSubtree, diaRightSubtree), lHeight + rHeight + 1);
	}
	
	/**
	 * Find the level that has the maximum sum in the binary tree
	 * <p>
	 * Algo: <br/>
	 * 		push the root node and a null to mark the end of a level.</br>
	 * 		Pop and push the child nodes.</br>
	 * 		Reset the sum if the element popped from the queue is a null (and the queue is not empty).</br>
	 * 		Continue this until the queue is empty.</br>
	 * </p>
	 */
	
	public static int getMaxSumAtLevel(TreeNode<Integer> bTree) {
		if (bTree == null) {
			return 0;
		}
		
		int maxSum = 0;
		int sum = 0;
		Queue<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();
		queue.add(bTree);
		queue.add(null);
		while(!queue.isEmpty()) {
			TreeNode<Integer> item = queue.poll();
			if(item == null)
			{
				if(!queue.isEmpty()) { // End of a level
					queue.add(null);
				}
				maxSum = maxSum < sum ? sum : maxSum;	
				sum = 0;
			}
			else {
				sum += item.getData();
				if(item.getLeft() != null) {
					queue.add(item.getLeft());
				}
				if(item.getRight() != null) {
					queue.add(item.getRight());
				}
			}
			
		}
		return maxSum;
	}
	
	/**
	 * Print the contents of a stack
	 */
	public static <T> void printStack(Stack<T> contents) {
		ListIterator<T> it = contents.listIterator(0);
		while(it.hasNext()) {
			System.out.print(it.next() + "=>");
		}
		System.out.println();
	}
	
	/**
	 * Uses a algo similar to inorder traversal to find all the root to leaf nodes path.
	 * @param bTree
	 * @param stack
	 */
	private static void printAllRootToLeafPaths(TreeNode<Integer> bTree, Stack<Integer> stack) {
		if(bTree == null) {
			return;
		}
		
		stack.push(bTree.getData());
		printAllRootToLeafPaths(bTree.getLeft(), stack);

		if(bTree.getLeft() == null && bTree.getRight() == null) {
			printStack(stack);
		}

		printAllRootToLeafPaths(bTree.getRight(), stack);
		
		stack.pop();
	}

	/**
	 * Prints the path of the root to leaf nodes saved in the array
	 * @param paths
	 * @param pathIndex
	 */
	public static void printPath(int[] paths, int pathIndex) {
		for(int i = 0; i < pathIndex; ++i) {
			System.out.print(paths[i] + "=>");
		}
		System.out.println();
	}
	
	/**
	 * Method 2 to find out the paths from the root to all the leaf nodes.
	 * We keep saving the paths in an array and print the array when a leaf is hit.
	 * @param bTree
	 * @param paths
	 * @param pathIndex
	 */
	public static void printAllRootToLeafPaths(TreeNode<Integer> bTree, int[] paths, int pathIndex) {
		if(bTree == null) {
			return;
		}
		
		paths[pathIndex++] = bTree.getData();
		
		if(bTree.getLeft() == null && bTree.getRight() == null) { // Leaf node. Print the path
			printPath(paths, pathIndex);
		}
		printAllRootToLeafPaths(bTree.getLeft(), paths, pathIndex);
		printAllRootToLeafPaths(bTree.getRight(), paths, pathIndex);
	}

	/**
	 * Get all the paths from root to the leaf nodes. </br>
	 * <a>https://www.youtube.com/watch?v=zIkDfgFAg60</a>
	 */
	public static void printAllRootToLeafPaths(TreeNode<Integer> bTree) {
//		Stack<Integer> stack = new Stack<>();
//		printAllRootToLeafPaths(bTree, stack);
		int[] paths = new int[20];
		printAllRootToLeafPaths(bTree, paths, 0);
	}
	
	/**
	 * Get all paths from the root to leaf nodes in a non-recursive way
	 */
	public static void printAllRootToLeafPathsNonRecursive(TreeNode<Integer> bTree) {
		if(bTree == null) {
			return;
		}
		Map<TreeNode<Integer>, TreeNode<Integer>> parent = new HashMap<>();
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		queue.add(bTree);
		
		while(!queue.isEmpty()) {
			TreeNode<Integer> item = queue.poll();
			if(item.getLeft() == null && item.getRight() == null) { // Encountered a leaf node
				printPathFromTopToBottom(item, parent);
			}
			
			if(item.getLeft() != null) {
				queue.add(item.getLeft());
				parent.put(item.getLeft(), item);
			}

			if(item.getRight() != null) {
				queue.add(item.getRight());
				parent.put(item.getRight(), item);
			}
		}
	}

	private static void printPathFromTopToBottom(TreeNode<Integer> item,
			Map<TreeNode<Integer>, TreeNode<Integer>> parent) {
		if(item == null) {
			return;
		}
		
		Stack<TreeNode<Integer>> stack = new Stack<>();
		stack.add(item);
		
		while(parent.get(item) != null) {
			stack.add(parent.get(item));
			item = parent.get(item);
		}
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop().getData() + "=>");
		}
		System.out.println();
	}
	
	
	public static Pair<Boolean, Pair<Integer, int[]>> hasPathSum(TreeNode<Integer> bTree, int sumOfNodes, int[] path, int pathIndex) {
		if(bTree == null) {
			return new Pair<Boolean, Pair<Integer, int[]>>(false, null);
		}
		sumOfNodes -= bTree.getData();
		path[pathIndex++] = bTree.getData();
		if(sumOfNodes == 0) {
			Pair<Integer, int[]> childPair = new Pair<>(pathIndex, path);
			return new Pair<Boolean, Pair<Integer, int[]>>(true, childPair);
		}
		Pair<Boolean, Pair<Integer, int[]>> lResult = hasPathSum(bTree.getLeft(), sumOfNodes, path, pathIndex);
		if(lResult.getKey() == true) {
			return lResult;
		}
		
		Pair<Boolean, Pair<Integer, int[]>> rResult = hasPathSum(bTree.getRight(), sumOfNodes, path, pathIndex);
		if(rResult.getKey() == true) {
			return rResult;
		}
		return new Pair<Boolean, Pair<Integer, int[]>>(false, null);
	}
	
	/**
	 * Find if the path for the given sum exists in the tree.
	 * @param bTree
	 * @param sumOfNodes
	 * @return
	 */
	public static boolean hasPathSum(TreeNode<Integer> bTree, int sumOfNodes) {
		if(bTree == null) {
			return false;
		}
		
		sumOfNodes -= bTree.getData();
		if(sumOfNodes == 0) {
			return true;
		}
		return hasPathSum(bTree.getLeft(), sumOfNodes) || hasPathSum(bTree.getRight(), sumOfNodes);
	}
}

class TreeQuestionsMain {
	
	public static void main(String[] args)
	{
//		TreeNode<Integer> bTree = createTree();
//		System.out.println("Maximum Element : " + TreeQuestions.getMaxElemBTree(bTree));
//		System.out.println(TreeQuestions.findElementInBTree(bTree, 5));
//		System.out.println(TreeQuestions.findElementInBTree(bTree, 10));
//		System.out.println("Size of tree : " + TreeQuestions.sizeOfBinaryTree(bTree));
//		TreeQuestions.levelOrderTraversalInReverse(bTree);
//		System.out.println("Height of the tree is : " + TreeQuestions.getHeightOfBTree(bTree));
//		System.out.println("Height of the tree is : " + TreeQuestions.getHeightOfBTreeNonRecursive(bTree));
//		System.out.println("Number of leaf nodes : " + TreeQuestions.numLeafNodes(bTree));
//		System.out.println("Number of leaf nodes : " + TreeQuestions.numLeafNodesNonRecursive(bTree));
//		System.out.println("Number of full nodes : " + TreeQuestions.numFullNodes(bTree));
//		System.out.println("Number of full nodes : " + TreeQuestions.numFullNodesNonRecursive(bTree));
		
		TreeNode<Integer> bTree = createTreeWithHalfNodes();
//		TreeTraversal.inOrderTraversal(bTree);
//		System.out.println("Number of half nodes : " + TreeQuestions.numHalfNodes(bTree));
		
		TreeNode<Integer> bTreeAllNodes = createTree();
		
		System.out.println(TreeQuestions.isStructurallyIdentical(bTree, bTreeAllNodes));
		System.out.println(TreeQuestions.isStructurallyIdentical(bTree, bTree));
		System.out.println(TreeQuestions.isStructurallyIdentical(bTreeAllNodes, bTreeAllNodes));
		
//		System.out.println(TreeQuestions.getHeightOfBTree(bTreeAllNodes));
//		System.out.println(TreeQuestions.getHeightOfBTreeNonRecursive(bTreeAllNodes));
		System.out.println(TreeQuestions.getHeightOfBTreeNonRecursive(bTreeAllNodes));
		System.out.println(TreeQuestions.heightOfTreeNonRecursive(bTreeAllNodes));
		
		System.out.println(TreeQuestions.getMaxSumAtLevel(bTreeAllNodes));
		System.out.println(TreeQuestions.getMaxSumAtLevel(bTree));
		
//		Stack<Integer> s = new Stack<>();
//		s.add(0);
//		s.add(1);
//		s.add(2);
//		s.add(3);
//		s.add(4);
//		s.add(5);
//		s.add(6);
//		TreeQuestions.printStack(s);
		
		System.out.println("----------------------------------------------");
		TreeQuestions.printAllRootToLeafPaths(bTreeAllNodes);
		System.out.println("----------------------------------------------");
		TreeQuestions.printAllRootToLeafPaths(bTree);
		System.out.println("----------------------------------------------");
		TreeQuestions.printAllRootToLeafPathsNonRecursive(bTreeAllNodes);
		System.out.println("----------------------------------------------");
		TreeQuestions.printAllRootToLeafPathsNonRecursive(bTree);
		System.out.println("----------------------------------------------");
		System.out.println(TreeQuestions.hasPathSum(bTreeAllNodes, 8));
		System.out.println(TreeQuestions.hasPathSum(bTreeAllNodes, 6));
		System.out.println("----------------------------------------------");
		int[] arr = new int[20];
		Pair<Boolean, Pair<Integer, int[]>>valueAndPath = TreeQuestions.hasPathSum(bTreeAllNodes, 8, arr, 0);
		if(valueAndPath.getKey() == true) {
			System.out.println("Path Found");
			TreeQuestions.printPath(valueAndPath.getValue().getValue(), valueAndPath.getValue().getKey());
		}
		else {
			System.out.println("Path Not Found");	
		}
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

	private static TreeNode<Integer> createTreeWithHalfNodes()
	{
		TreeNode<Integer> bTree = new TreeNode<>(1);
		bTree.setLeft(new TreeNode<Integer>(2));
		bTree.setRight(new TreeNode<Integer>(3));
		bTree.getLeft().setRight(new TreeNode<Integer>(5));
		bTree.getRight().setLeft(new TreeNode<Integer>(6));
		bTree.getLeft().getRight().setLeft(new TreeNode<Integer>(7));
		bTree.getRight().getLeft().setLeft(new TreeNode<Integer>(9));
		
		return bTree;
	}

}
