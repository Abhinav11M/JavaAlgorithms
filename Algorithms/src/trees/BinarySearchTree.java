package trees;

// Only supports Integer for now
public class BinarySearchTree {
	
	public BinarySearchTree() {
		root = null;
	}

	public void addData(Integer data) {
		if(root == null) {
			// Adding the root node
			root = new TreeNode<Integer>(data);
		}
		else {
			TreeNode<Integer> temp = root;
			addData(data, temp);
		}
	}
	
	public TreeNode<Integer>getRootNode() {
		return root;
	}
	
	private void addData(Integer data, TreeNode<Integer> root) {
		//Base condition
		if(root == null) {
			root = new TreeNode<Integer>(data);
			return;
		}
		
		if(data > root.getData()) {
			if(root.getRight() == null) { // Add to the right
				root.setRight(new TreeNode<Integer>(data));
				return;
			}
			else { // Move ahead in the right-subtree
				addData(data, root.getRight());
			}
		}

		else if(data < root.getData()) { // Add to the left
			if(root.getLeft() == null) { 
				root.setLeft(new TreeNode<Integer>(data));
				return;
			}
			else {
				addData(data, root.getLeft());
			}
		}
		else {
			throw new RuntimeException("Cannot insert repeated elements in a BST");
		}
		
//		if(data > root.getRight().getData()) { // Insert to the right
//			addData(data, root.getRight());
//		}
//		else if(data < root.getData()) { // Insert to the left
//			addData(data, root.getLeft());
//		}
	}
	
	private TreeNode<Integer> root;
}
