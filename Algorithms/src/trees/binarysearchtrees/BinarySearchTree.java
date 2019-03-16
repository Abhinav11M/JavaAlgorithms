package trees.binarysearchtrees;

import trees.TreeNode;
import trees.binarytrees.TreeTraversal;

public class BinarySearchTree {
	
	public void addElement(Integer data) {
		if(root == null) {
			root = new TreeNode<Integer>(data);
		}
		else {
			TreeNode<Integer> newNode = new TreeNode<Integer>(data);
			TreeNode<Integer> temp = root;
			// Find the position to insert
			TreeNode<Integer> parent = root;
			while(temp != null) {
				if(data < temp.getData()) { //move left
					parent = temp;
					temp = temp.getLeft();
					if(temp == null) {
						parent.setLeft(newNode);
					}
				}
				else { // move right
					parent = temp;
					temp = temp.getRight();
					if(temp == null) {
						parent.setRight(newNode);
					}
				}
			}
		}
	}
	
	public TreeNode<Integer> getRoot() {
		return root;
	}
	
	public TreeNode<Integer> findNode(int value) {
		return findNode(value, root);
	}
	
	public Integer getMaxElement() {
		if(root == null) {
			return -1;
		}
		
		TreeNode<Integer> temp = root; //Don't want to update the root node.
		
		while(temp.getRight() != null) {
			temp = temp.getRight();
		}
		return temp.getData();
	}
	
	public Integer getMinElement() {
		if(root == null) {
			return -1;
		}
		
		TreeNode<Integer> temp = root; //Don't want to update the root node.
		
		while(temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return temp.getData();
	}

	private TreeNode<Integer> findNode(int value, TreeNode<Integer> root) {
		if(root == null) {
			return null;
		}
		if(root.getData() == value) {
			return root;
		}
		
		if(value < root.getData()) { // Find in the left
			return findNode(value, root.getLeft());
		}
		else { // Find in the right
			return findNode(value, root.getRight());
		}
	}
	
	private TreeNode<Integer> root = null;
}

class TestBST {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.addElement(5);
		bst.addElement(2);
		bst.addElement(8);
		bst.addElement(14);
		bst.addElement(13);
		bst.addElement(23);
		bst.addElement(31);
		bst.addElement(7);
		bst.addElement(19);
		
		TreeTraversal.inOrderTraversal(bst.getRoot());
		TreeNode<Integer> fNode = bst.findNode(23);
		if(fNode != null) {
			System.out.println("Found!!");
		}
		else {
			System.out.println("Not Found!!");
		}

		TreeNode<Integer> fNode1 = bst.findNode(24);
		if(fNode1 != null) {
			System.out.println("Found!!");
		}
		else {
			System.out.println("Not Found!!");
		}
		
		System.out.println(bst.getMaxElement());
		System.out.println(bst.getMinElement());
	}
}
