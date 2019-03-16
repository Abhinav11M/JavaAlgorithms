package trees;

public class BinarySearchTree<T> {
	
	public BinarySearchTree() {
		root = null;
	}

	public void addData(T data) {
		if(root == null) {
			// Adding the root node
			root = new TreeNode<T>(data);
		}
		else {
			// Find where to insert
			TreeNode<T> insertPos = findWhereToInsert(data);
		}
	}
	
	private TreeNode<T> findWhereToInsert(T data) {
//		TreeNode<T> root1 = root;
		return null;
	}
	
	private TreeNode<T> root;
}
