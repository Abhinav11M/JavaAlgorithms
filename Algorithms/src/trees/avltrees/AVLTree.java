package trees.avltrees;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class AVLTree {
	
	public AVLTree() {
		head = null;
	}
	
	public AVLTreeNode getHead() {
		return head;
	}
	
	public void insertNode(int data) {
		head = insertNode(head, data);
	}

	private AVLTreeNode insertNode(AVLTreeNode head, int data) {
		if(head == null) {
			return new AVLTreeNode(data);
		}
		
		if(data < head.getData()) { // Insert to left subtree.
			head.setLeft(insertNode(head.getLeft(), data));
		}

		else if(data > head.getData()) { // Insert to right subtree.
			head.setRight(insertNode(head.getRight(), data));
		}

		updateHeight(head);
		
		return balance(head);
	}
	
	public AVLTreeNode findNode(int data) {
		return findNode(data, head);
	}
	
	private AVLTreeNode findNode(int data, AVLTreeNode head) {
		if(head == null) {
			return null;
		}
		
		if(data < head.getData()) {
			return findNode(data, head.getLeft());
		}
		else if(data > head.getData()) {
			return findNode(data, head.getRight());
		}

		return head;
	}
	
	/**
	 * Delete a node from AVL Tree.
	 * @param data: Data of the node to be deleted. If the node is not found, just skip deleting.
	 */
	public void deleteNode(int data) {
		deleteNode(data, head);
	}
	
	private AVLTreeNode deleteNode(int data, AVLTreeNode head) {
		if(head == null) {
			return null;
		}

		if(data < head.getData()) {
			head.setLeft(deleteNode(data, head.getLeft()));
		}

		else if(data > head.getData()) {
			head.setRight(deleteNode(data, head.getRight()));
		}

		else {
			if(head.getLeft() == null) { // left subtree is empty, so return the right subtree
				return head.getRight();
			}
			if(head.getRight() == null) { // right subtree is empty, so return the left subtree
				return head.getLeft();
			}
			else { // If both the subtrees exist, find the inorder predecessor and swap the contents
				AVLTreeNode tempNode = head.getLeft();
				while(tempNode.getRight() != null) {
					tempNode = tempNode.getRight();
				}
				head.setData(tempNode.getData()); // Copy the contents of the inorder predecessor to the head.
				head.setRight(deleteNode(data, head.getRight())); // Delete the inorder predecessor.
			}
		}
		
		updateHeight(head);
		return balance(head);
	}
	
	private AVLTreeNode rotateRR(AVLTreeNode head) {
		AVLTreeNode temp = head;
		head = head.getRight();
		AVLTreeNode headLeft = head.getLeft();
		head.setLeft(temp);
		temp.setRight(headLeft);
		
		updateHeight(temp);
		updateHeight(head);
		
		return head;
	}

	private AVLTreeNode rotateLL(AVLTreeNode head) {
		AVLTreeNode temp = head;
		head = head.getLeft();
		AVLTreeNode headRight = head.getRight();
		head.setRight(temp);
		temp.setLeft(headRight);
		
		updateHeight(temp);
		updateHeight(head);
		
		return head;	
	}

	private AVLTreeNode rotateLR(AVLTreeNode head) {
		// Apply left rotation on head->left
		head.setLeft(rotateRR(head.getLeft()));
		updateHeight(head.getLeft());
		// Apply right rotation on head
		head = rotateLL(head);
		updateHeight(head);
		return head;
	}
	
	private AVLTreeNode rotateRL(AVLTreeNode head) {
			// Apply right rotation on head->right
		head.setRight(rotateLL(head.getRight()));
		updateHeight(head.getRight());
		// Apply right rotation on head
		head = rotateRR(head);
		updateHeight(head);
		return head;
	}

	private AVLTreeNode balance(AVLTreeNode head) {
		if(head.getBalanceFactor() == 2) { // Right subtree heavy
			if (head.getRight().getBalanceFactor() >= 0) {
		        return rotateRR(head);

		      // Right-Left case.
		      } else {
		        return rotateRL(head);
		      }	
		}
		else if(head.getBalanceFactor() == -2) { // Left subtree heavy
			// Left-Left case.
		      if (head.getLeft().getBalanceFactor() <= 0) {
		        return rotateLL(head);
		        
		      // Left-Right case.
		      } else {
		        return rotateLR(head);
		      }
		}
		return head;
	}

	private void updateHeight(AVLTreeNode head) {
		int leftHeight = head.getLeft() == null ? -1 : head.getLeft().getHeight();
		int rightHeight = head.getRight() == null ? -1 : head.getRight().getHeight();
		
		// Set the height of the node
		head.setHeight(Math.max(leftHeight, rightHeight) + 1);
		
		// Set the balance factor of the node
		head.setBalanceFactor(rightHeight - leftHeight);
	}

	private AVLTreeNode head;
}
