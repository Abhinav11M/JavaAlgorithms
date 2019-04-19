package trees;

public class TreeNode<T>
{
	public TreeNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public TreeNode(TreeNode<T> node) {
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}
	
	public T getData()
	{
		return data;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	public TreeNode<T> getLeft()
	{
		return left;
	}
	public void setLeft(TreeNode<T> left)
	{
		this.left = left;
	}
	public TreeNode<T> getRight()
	{
		return right;
	}
	public void setRight(TreeNode<T> right)
	{
		this.right = right;
	}

	@Override
	public String toString() {
		return "TreeNode [data=" + data + ", left=" + left + ", right=" + right + "]";
	}

	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
}
