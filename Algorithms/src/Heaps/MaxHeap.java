package Heaps;

public class MaxHeap {
	public MaxHeap(int size) {
		data = new Integer[size];
		this.size = size;
		count = 0;
	}
	
	public MaxHeap(int[] data) {
		this.data = new Integer[data.length];
		size = data.length;
		count = data.length;
		
		for(int i = 0; i < data.length; ++i) {
			this.data[i] = data[i];
		}
		
		percolateDown(0);
	}
	
	/**
	 * @param val element to be inserted to the heap
	 * Algo: Insert the new data to the last available index.
	 * 		Heapify: Check if the data is bigger that its parent (index (i-1)/2, i is the index of insertion).
	 * 				 If it is bigger, swap it with parent (Parent should be bigger as this is maxHeap).
	 * 				 Now keep on doing this for until we have found the correct position for the data.
	 * 				 So, now update the index to the parent index ((i-1)/2), and keep running the loop until the data is smaller that its parent
	 * 				 or the index goes negative.
	 */
	public void add(int val) {
		if(count == size) { // Increase the data size
			resize();
		}
		data[count++] = val;
		
		int i = count - 1;
		while(i >= 0 && data[i] > data[(i-1)/2]) { // Data of child greater than parent, then swap
			int temp = data[i];
			data[i] = data[(i-1)/2];
			data[(i-1)/2] = temp;
			i = (i-1)/2;
		}
	}
	
	private void resize() {
		Integer[] newData = new Integer[size * 2];
		size = size * 2;
		for(int i = 0; i < data.length; ++i) {
			newData[i] = data[i];
		}
		
		data = newData;
	}
	
	public int deleteMax() {
		if(count == 0) {
			return -1;
		}
		
		int retVal = data[0];
		data[0] = data[count-1]; // Move the last data to the top and percolateDown(Heapify) recursively.
		count--;
		percolateDown(0);
		
		return retVal;
	}
	
	/**
	 * Heapifies the heap's internal array.
	 * @param i : index to be heapified from
	 */
	private void percolateDown(int i) {
		int l = getLeftIndex(i);
		int r = getRightIndex(i);
		int max = i;
		
		if(l != -1 && data[l] > data[i]) {
			max = l;
		}
		if(r != -1 && data[r] > data[max]) {
			max = r;
		}
		if(max == i) {
			return;
		}
		// Swap the elements
		int temp = data[i];
		data[i] = data[max];
		data[max] = temp;
		percolateDown(max);
	}

	private int getLeftIndex(int i) {
		int leftIndex = 2*i + 1;
		if(leftIndex >= count) {
			return -1;
		}
		
		return leftIndex;
	}
	
	
	private int getRightIndex(int i) {
		int rightIndex = 2*i + 2;
		if(rightIndex >= count) {
			return -1;
		}
		
		return rightIndex;
	}

	
	public Integer getMax() {
		return data[0];
	}
	
	public Integer[] getAllData() {
		return data;
	}
	
	public int getCount() {
		return count;
	}
	
	private Integer[] data;
	private int size;
	private int count;
}
