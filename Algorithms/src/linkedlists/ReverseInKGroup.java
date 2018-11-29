package linkedlists;

public class ReverseInKGroup
{

	private static void printList(Node head)
	{
		Node tempHead = head;
		while(tempHead != null)
		{
			System.out.println(tempHead.data);
			tempHead = tempHead.next;
		}
	}
	
	public static void main(String[] args)
	{
		SinglyLinkedList s = new SinglyLinkedList();
		for (int i = 1; i < 11; ++i)
		{
			s.addNode(i);
		}
		
		Node head = s.head;
		//printList(head);
		
		Node newHead = new ReverseInKGroup().reverseInKGroups(head, 5);
		printList(newHead);
		
	}

	public Node reverse(Node head)
	{
		if(head == null || head.next == null)
			return head;
		Node secElem = head.next;
		// Disconnect the link to avoid cycles.
		head.next = null;
		Node remainingRev = reverse(secElem);
		secElem.next = head;
		return remainingRev;
	}
	
	private Node reverseInKGroups(Node head, int k)
	{
		if (head == null || head.next == null)
			return head;
		
		//Split the first n elements and the rest of the list.
		//Reverse the first n elements and then link it back to the remaining list.
		//Splitting the list
		int count = 1;
		Node tempHead = head;
		while(count < k) 
		{
			if(tempHead.next == null)
				break;
			tempHead = tempHead.next;
			++count;
		}
		if(count < k)
			return reverse(head);
	
		//Next elements to reverse
		Node nextElements = tempHead.next;
		//breaking the link
		tempHead.next = null;
		
		//Reverse the first k elements
		Node head1 = head;
		Node newHead = reverse(head1);
		// We need to append the old head to the new reversed list.
		head.next = reverseInKGroups(nextElements, k);
		//head = newHead;
		return newHead;
	}

}
