package trees.avltrees;

import trees.TreeNode;

public class AVLTreeNode {
	
	public AVLTreeNode(int argData) {
		data = argData;
		left = null;
		right = null;
		height = 0; // Assuming the height of a leaf node is 0.
		balanceFactor = 0;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	public AVLTreeNode getLeft() {
		return left;
	}

	public void setRight(AVLTreeNode right) {
		this.right = right;
	}

	public AVLTreeNode getRight() {
		return right;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
	
	public int getBalanceFactor() {
		return balanceFactor;
	}
	
	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
	
	public static void inOrderTraversal(AVLTreeNode bTree)
	{
		if (bTree != null)
		{
			inOrderTraversal(bTree.getLeft());
			System.out.println(bTree.getData());
			inOrderTraversal(bTree.getRight());
		}
	}
	
	@Override
	public String toString() {
		return "AVLTreeNode [data=" + data + ", height=" + height + ", balanceFactor=" + balanceFactor + "]";
	}

	private int data;
	private AVLTreeNode left;
	private AVLTreeNode right;
	private int height;
	private int balanceFactor;
}
