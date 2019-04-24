package linkedlists;

public class Node<T> {
	public Node()
	{}
	public Node(T d) 
	{
		data = d;
		next = null;
	}
	public T data;
	public Node<T> next;
}
