package trees.avltrees;

public class AVLTreeQuestions {
	
	/**
	 * Generate an AVL tree of height h with minimum number of nodes
	 * @param args
	 */
	public AVLTreeNode generateAVLTreeWithMinimumNodes(int h) {
		if(h <= 0) {
			return null;
		}
		
		AVLTreeNode head = new AVLTreeNode(count++);
		head.setHeight(h);
		head.setLeft(generateAVLTreeWithMinimumNodes(h-1));
		head.setRight(generateAVLTreeWithMinimumNodes(h-2));
		
		return head;
	}

	/**
	 * Print all nodes with values between [k1, k2]
	 * @param args
	 */
	public void printDataBetweenK1K2(AVLTreeNode root, int k1, int k2) {
		if(root == null) {
			return;
		}
		
		if(root.getData() < k1) { // Move to the right subtree as we need values that should be greater or equal to k1
			printDataBetweenK1K2(root.getRight(), k1, k2);
		}
		
		else if(root.getData() > k2) { // Move to the left subtree as we need values that are less than or equal to k2
			printDataBetweenK1K2(root.getLeft(), k1, k2);
		}
		else { // Traverse in both the trees to find
			System.out.println(root.getData());
			printDataBetweenK1K2(root.getLeft(), k1, k2);
			printDataBetweenK1K2(root.getRight(), k1, k2);
		}
	}
	
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

	/*	// ====== Testing RR Rotation ======
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
		AVLTreeNode.inOrderTraversal(head.getHead());*/
		AVLTreeQuestions avl = new AVLTreeQuestions();
		// === Create AVL Tree with minimum nodes ====
		/*
		AVLTreeNode avlHead = avl.generateAVLTreeWithMinimumNodes(4);
		
		AVLTreeNode.inOrderTraversal(avlHead);
		// === Create AVL Tree with minimum nodes ====*/
		
		AVLTree head = new AVLTree();
		head.insertNode(10);
		head.insertNode(15);
		head.insertNode(18);
		head.insertNode(1);
		head.insertNode(11);
		head.insertNode(28);
		head.insertNode(6);
		head.insertNode(13);
		
		avl.printDataBetweenK1K2(head.getHead(), 0, 30);
	}
	
	private static int count = 0;
}
