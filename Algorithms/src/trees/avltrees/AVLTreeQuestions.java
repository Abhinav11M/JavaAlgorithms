package trees.avltrees;

public class AVLTreeQuestions {
	public static void main(String[] args) {
		// ====== Testing RR Rotation ======
/*		AVLTree head = new AVLTree();
		head.insertNode(10);
		head.insertNode(15);
		head.insertNode(18);
*/		// ====== Testing RR Rotation ======
		
		// ====== Testing LL Rotation ======
/*		AVLTree head = new AVLTree();
		head.insertNode(10);
		head.insertNode(5);
		head.insertNode(3);
*/		// ====== Testing LL Rotation ======
		
		// ====== Testing LR Rotation ======
/*		AVLTree head = new AVLTree();
		head.insertNode(10);
		head.insertNode(5);
		head.insertNode(8);
*/		// ====== Testing LR Rotation ======

		// ====== Testing RR Rotation ======
		AVLTree head = new AVLTree();
		head.insertNode(10);
		head.insertNode(15);
		head.insertNode(18);
		head.insertNode(1);
		head.insertNode(11);
		head.insertNode(28);
		head.insertNode(6);
		head.insertNode(13);
		
		AVLTreeNode.inOrderTraversal(head.getHead());
		// ====== Testing RR Rotation ======
		
		head.deleteNode(15);
		System.out.println("After deletion");
		AVLTreeNode.inOrderTraversal(head.getHead());
		
	}
}
