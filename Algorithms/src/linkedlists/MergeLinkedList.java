package linkedlists;

public class MergeLinkedList {

	public static void main(String[] args) {
		SinglyLinkedList list1 = new SinglyLinkedList();
		SinglyLinkedList list2 = new SinglyLinkedList();
		list1.addNode(1);
		list1.addNode(5);
		list1.addNode(9);
		list1.addNode(10);
		list1.addNode(17);
		list2.addNode(3);
		list2.addNode(7);
		list2.addNode(12);
		list2.addNode(14);
		list2.addNode(15);
		Node head = null;
		mergeLists(head, list1, list2);
	}

	private static Node mergeLists(Node head, SinglyLinkedList list1, SinglyLinkedList list2) {
		if(list1 == null && list2 == null) {
			return head;
		}
		Node head1 = list1.head;
		Node head2 = list2.head;
		if(head1 == null) {
			head.next = list2.head;
		}
		else if(head2 == null) {
			head.next = list1.head;
		}
		else {
			if(list1.head.data > list2.head.data) {
				if(head == null) {
					head = new Node();
					head.data = list2.head.data;
					list2.head = list2.head.next;
					head.next = mergeLists(head, list1, list2);
				}
				else {
					
				}
			}
		}
		return null;
	}

}
