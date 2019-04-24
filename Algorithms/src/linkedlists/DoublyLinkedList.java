package linkedlists;

import trees.TreeNode;

public class DoublyLinkedList<T> {
	public DoublyLinkedList(T data) {
		head = new TreeNode<T>(data);
	}

	public DoublyLinkedList(TreeNode<T> data) {
		head = new TreeNode<T>(data);
	}
	
	public TreeNode<T> getHead() {
		return head;
	}
	
	public void addElement(T data) {
		TreeNode<T> newNode = new TreeNode<T>(data);
		TreeNode<T> tempHead = head;
		// Iterate till the end.
		while(tempHead.getRight() != null) {
			tempHead = tempHead.getRight();
		}
		tempHead.setRight(newNode);
		newNode.setLeft(tempHead);
	}
	
	public int countElement() {
		if(head == null) {
			return 0;
		}
		TreeNode<T> tempHead = head;
		int counter = 0;
		while(tempHead != null) {
			++counter;
			tempHead = tempHead.getRight();
		}
		
		return counter;
	}
	
	public void printElements() {
		TreeNode<T> tempHead = head;
		// Iterate till the end.
		while(tempHead.getRight() != null) { // Last node
			System.out.println(tempHead.getData() + ", ");
			tempHead = tempHead.getRight();
		}
	}
	
	private TreeNode<T> head;
}
