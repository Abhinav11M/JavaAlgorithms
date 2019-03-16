package trees.generictrees;

/*
 * Generic tree are structured in a way called first child/next sibling representation.
 */
public class GenericTreeNode<T> {
	public GenericTreeNode(T data) {
		this.data = data;
		this.firstChild = null;
		this.nextSibling = null;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public GenericTreeNode<T> getFirstChild() {
		return firstChild;
	}
	
	public void setFirstChild(GenericTreeNode<T> firstChild) {
		this.firstChild = firstChild;
	}
	
	public GenericTreeNode<T> getNextSibling() {
		return nextSibling;
	}
	
	public void setNextSibling(GenericTreeNode<T> nextSibling) {
		this.nextSibling = nextSibling;
	}

	private T data;
	private GenericTreeNode<T> firstChild;
	private GenericTreeNode<T> nextSibling;
}
