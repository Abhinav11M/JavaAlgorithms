package trees;

public class TreeNode2 {
	
	TreeNode2(int d1, int d2) {
		left = right = null;
		data1 = d1;
		data2 = d2;
	}

	public TreeNode2 left;
	public TreeNode2 right;
	public int data1;
	public int data2;
	@Override
	public String toString() {
		return "TreeNode2 [data1=" + data1 + ", data2=" + data2 + "]";
	}
}
