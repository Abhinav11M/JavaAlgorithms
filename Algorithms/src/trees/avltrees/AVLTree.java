package trees.avltrees;

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
