package trees;

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

	public static void main(String[] args) {
		BinarySearchTreeQuestions ob = new BinarySearchTreeQuestions();
		TreeNode<Integer> bSTree = ob.createBST();
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
		
		System.out.println(ob.findMin(bSTree).getData());
		System.out.println(ob.findMax(bSTree).getData());
	}
	
}
