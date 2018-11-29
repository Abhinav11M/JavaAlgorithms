package linkedlists;

public class SinglyLinkedList {

	public void addNode(int nodeData) {
		if(head == null) {
			head = new Node();
			head.data = nodeData;
			head.next = null;
		}
		else {
			// Traverse to the end of the list and add the node.
			Node nextNode = head;
			while(nextNode.next != null) {
				nextNode = nextNode.next;
			}
			// Add a node to the end.
			nextNode.next = new Node();
			nextNode.next.data = nodeData;
			nextNode.next.next = null;
		}
	}
	
	public void addAtBeg(int nodeData) {
		if(head == null) {
			// Simply add a new node.
			head = new Node();
			head.next = null;
			head.data = nodeData;
		}
		else {
			// Update the head to point to the first node.
			Node newHead = new Node();
			newHead.data = nodeData;
			newHead.next = head;
			this.head = newHead;
		}
	}
	
	public void addAtPos(int pos, int nodeData) {
		if(pos == 0) {
			addAtBeg(nodeData);
		}
		else {
			Node travNode = head;
			for(int i = 0; i < pos; ++i) {
				if(travNode != null) {
					travNode = travNode.next;
				}
				else {
					System.out.println("Invalid position to insert...");
					return;
				}
			}
			Node newNode = new Node();
			newNode.data = nodeData;
			//Link the new node.
			newNode.next = travNode.next;
			travNode.next = newNode;
		}
	}
	
	public void deleteFromBeg() {
		if(head == null) {
			System.out.println("The list is empty. Can't delete from head...");
			return;
		}
		head = head.next;
	}
	
	public void deleteFromPos(int pos) {
		if(pos == 0) {
			deleteFromBeg();
		}
		else {
			Node prevNode = head;
			Node nextNode = head.next;
			for (int i = 0; i < pos; ++i) {
				if(nextNode != null) {
					prevNode = nextNode;
					nextNode = nextNode.next;
				}
				else {
					System.out.println("Invalid position to delete...");
					return;
				}
			}
			//Connect previous to the next node.
			prevNode.next = nextNode.next;
		}
	}
	
	public void traverse() {
		Node travNode = head;
		while(travNode != null) {
			System.out.println(travNode.data);
			travNode = travNode.next;
		}
	}
	
	public int countNodes(Node list) {
		int count = 0;
		while(list != null) {
			++count;
			list = list.next;
		}
		return count;
	}
	
	// Find the nth node from the end of a linked list (Using recursion)
	public void getNthNodeFromEnd(int n) {
		if(head == null) {
			System.out.println("List is empty...");
			return;
		}
		Node tempHead = head;
		while(tempHead != null) {
			if(countNodes(tempHead.next) < n-1) {
				System.out.println("List has less number of nodes...");
				return;
			}
			if(countNodes(tempHead.next) == n-1) {
				System.out.println("Required node is : " + tempHead.data);
				return;
			}
			tempHead = tempHead.next;
		}
	}
	
	// Find the nth node from the end of a  linked list in one scan.
	public void getNthNodeFromEndEff(int n) {
		if(head == null) {
			System.out.println("The list is empty");
			return;
		}
		Node tempHead = head;
		Node posHead = head;
		// Move the tempHead to the nth position from the beginning
		for(int i = 0; i < n-1; ++i) {
			if(tempHead == null) {
				System.out.println("The number of nodes in the list is less than " + n);
				return;
			}
			// Stop moving both the pointers until the tempHead reaches the last node.
			while(tempHead.next != null) {
				tempHead = tempHead.next;
				posHead = posHead.next;
			}
			System.out.println("NTh node from the end is " + posHead.data);
		}
	}
	
	public Node head = null;
}

class MainClass {	
	
	public void travFromLast(Node head) {
		Node tempHead = head; //Keeping the head unaltered.
		if(tempHead == null) {
			return;
		}
		travFromLast(tempHead.next);
		System.out.println(tempHead.data);
	}
	
	public void trav(Node head) {
		Node tempHead = head;
		while(tempHead != null) {
			System.out.print(tempHead.data + ", ");
			tempHead = tempHead.next;
		}
		System.out.println("\n");
	}
	
	// Reverse a linked list (Using recursion)
	public Node reverse(Node head)
	{
		if(head == null) {
			return null;
		}
		//If the list has only one element, we don't need to reverse. We can just send the entire list itself.
		if(head.next == null) {
			return head;
		}
		Node firstElement = head;
		Node secondElement = head.next;
		// Detach the first element from the rest of the list as it may create cycle while reversing.
		firstElement.next = null;
		Node remainingList = reverse(secondElement);
		//Link the first node at the last of the reversed node.
		secondElement.next = firstElement;
		return remainingList;
	}
	
	// Reverse a linked list (Without recursion)
	public Node reverseOpt(Node head) {
		Node tempHead = head;
		Node finalList = null;
		while (tempHead != null) {
			Node newNode = tempHead;
			tempHead = tempHead.next;
			if(finalList == null) {
				//Adding the first node.
				finalList = newNode;
				finalList.next = null;
			}
			else {
				newNode.next = finalList;
				finalList = newNode;
			}
		}
		
		return finalList;
	}
	
	public static void main(String[] args) {
		SinglyLinkedList list = createLinkedList();
		MainClass m = new MainClass();
		m.trav(list.head);
		//Node revList = m.reverse(list.head);
		Node revList = m.reverseOpt(list.head);
		System.out.println("\nReversed List : ");
		m.trav(revList);
		m.travFromLast(revList);

	}

	private static SinglyLinkedList createLinkedList() {
		SinglyLinkedList ll = new SinglyLinkedList();
		for (int i = 1; i <= 10; ++i) {
			ll.addNode(i);
		}
		return ll;
	}
}
