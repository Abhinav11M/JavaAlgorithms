package Heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import linkedlists.Node;
import linkedlists.SinglyLinkedList;

public class HeapQuestions {
	
	private int getMinElementFromMaxHeap(MaxHeap heap) {
		
		Integer[] data = heap.getAllData();
		int min = 1000;
		for(int i = heap.getCount()/2; i < heap.getCount(); ++i) {
			if(data[i] < min) {
				min = data[i];
			}
		}
		
		return min;
	}
	
	private Integer[] mergeTwoHeaps(MaxHeap heap1, MaxHeap heap2) {
		
		Integer[] data1 = heap1.getAllData();
		Integer[] data2 = heap2.getAllData();
		Integer[] newData = new Integer[heap1.getCount() + heap2.getCount()];

		int j = 0;
		for(int i = 0; i < heap1.getCount(); ++i) {
			newData[j++] = data1[i];
		}
		for(int i = 0; i < heap2.getCount(); ++i) {
			newData[j++] = data2[i];
		}
		
		// We need to call heapify (this heapifies upwards) from first non-leaf node
		for(int i = j/2; i >= 0; i-- ) {
			heapify(newData, i);
		}
		
		return newData;
	}

	private void heapify(Integer[] newData, int idx) {
		if (idx > newData.length) {
			return;
		}
		
		int maxIdx = idx;
		int l = getLeftHeapIndex(idx, newData.length);
		int r = getRightHeapIndex(idx, newData.length);
		
		if(l != -1 && newData[l] > newData[maxIdx]) { 
			maxIdx = l;
		}
		
		if(r != -1 && newData[r] > newData[maxIdx]) {	
			maxIdx = r;
		}
		
		if(maxIdx != idx) {
			int temp = newData[idx];
			newData[idx] = newData[maxIdx];
			newData[maxIdx] = temp;

			heapify(newData, maxIdx);
		}
		
	}
	
	private int getLeftHeapIndex(int idx, int count) {
		int leftIdx = 2*idx + 1;
		if(leftIdx > count-1) {
			return -1;
		}
		return leftIdx;
	}
	
	private int getRightHeapIndex(int idx, int count) {
		int rightIdx = 2*idx + 2;
		if(rightIdx > count-1) {
			return -1;
		}
		return rightIdx;
	}
	
	/**
	 * Merge k sorted arrays
	 * <p>
	 * Algo : Push the first element of each array in a min heap.
	 * 		  Pop from the min heap. If the element popped is from array p,
	 * 		  and save it in the result array and
	 * 		  then push the next element of array p in the min heap.
	 * 		  Continue this while the heap is not empty.
	 * </p>
	 * @param  sortedLists
	 * @return
	 */
	SinglyLinkedList<Integer> mergeKSortedLists(List<SinglyLinkedList<Integer>> sortedLists) {
//		MinHeap minHeap = new MinHeap(sortedLists.size());
		PriorityQueue<Node<Integer>> minHeap = new PriorityQueue<>(new Comparator<Node<Integer>>() {

			@Override
			public int compare(Node<Integer> o1, Node<Integer> o2) {
				return o1.data - o2.data;
			}
		}); // Min heap.
		SinglyLinkedList<Integer> finalList = new SinglyLinkedList<>();
		
		for(int k = 0; k < sortedLists.size(); ++k) {
			minHeap.add(sortedLists.get(k).head);
		}
		
		while(!minHeap.isEmpty()) {
			Node<Integer> min = minHeap.remove();
			finalList.addNode(min.data);
			if(min.next != null) {
				minHeap.add(min.next);
			}
		}
		
		return finalList;
	}
	
	public static void main(String[] args) {
		MaxHeap maxHeap = new MaxHeap(5);
		maxHeap.add(1);
		maxHeap.add(2);
		maxHeap.add(3);
		maxHeap.add(4);
		maxHeap.add(5);
		
//		System.out.println(maxHeap.getMax());
//		for(int d : arr) {
//			System.out.print(d + ",");
//		}
		
		/*System.out.println(maxHeap.deleteMax());
		System.out.println(maxHeap.deleteMax());
		System.out.println(maxHeap.deleteMax());
		System.out.println(maxHeap.deleteMax());
		System.out.println(maxHeap.deleteMax());
		System.out.println(maxHeap.deleteMax());*/
		
		HeapQuestions ques = new HeapQuestions();
//		System.out.println(ques.getMinElementFromMaxHeap(maxHeap));
		
		MinHeap minHeap = new MinHeap(5);
		minHeap.add(1);
		minHeap.add(2);
		minHeap.add(3);
		minHeap.add(4);
		minHeap.add(5);
		
		/*System.out.println(minHeap.deleteFromIndex(2));
		System.out.println("***********");
		System.out.println(minHeap.deleteMin());
		System.out.println(minHeap.deleteMin());
		System.out.println(minHeap.deleteMin());
		System.out.println(minHeap.deleteMin());
		System.out.println(minHeap.deleteMin());
		System.out.println(minHeap.deleteMin());*/
		
		minHeap.printElementsGreaterThan(3);
		
		Integer[] res = ques.mergeTwoHeaps(new MaxHeap(new int[] {4,2,1,9}), new MaxHeap(new int[] {6,12,3,19}));
		System.out.println("Merged lists");
		for(int d : res) {
			System.out.print(d + ",");
		}
		
		SinglyLinkedList<Integer> s1 = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> s2 = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> s3 = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> s4 = new SinglyLinkedList<>();
		
		s1.addNode(1);
		s1.addNode(5);
		s1.addNode(9);
		s1.addNode(15);

		s2.addNode(3);
		s2.addNode(6);
		s2.addNode(12);
		s2.addNode(18);

		s3.addNode(2);
		s3.addNode(4);
		s3.addNode(17);
		s3.addNode(19);

		s4.addNode(7);
		s4.addNode(8);
		s4.addNode(10);
		s4.addNode(11);
		
		List<SinglyLinkedList<Integer>> lists = new ArrayList<>();
		lists.add(s1);
		lists.add(s2);
		lists.add(s3);
		lists.add(s4);
		
		SinglyLinkedList<Integer> res1 = ques.mergeKSortedLists(lists);
		res1.traverse();
	}
}
