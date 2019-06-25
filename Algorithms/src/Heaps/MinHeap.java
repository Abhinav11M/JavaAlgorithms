package Heaps;

public class MinHeap {

	public MinHeap(int size) {
		this.size = size;
		data = new Integer[size];
		count = 0;
	}
	
	public void add(int val) {
		if(count == data.length) {
			resize();
		}
		// Insert data at the last index
		data[count++] = val;
		int i = count - 1; // last index
		while(i >= 0 && data[(i-1)/2] > data[i]) { // If parent bigger that child then swap.
			int temp = data[i];
			data[i] = data[(i-1)/2];
			data[(i-1)/2] = temp;
		}
	}
	
	public int deleteMin() {
		if(count == 0) { // Empty heap
			return -1;
		}
		
		int retVal = data[0];
		count--;
		data[0] = data[count]; // Copy the last element to the top and percolateDown recursively.
		percolateDown(0);
		
		return retVal;
	}
	
	/**
	 * Delete element from an index of a min heap.
	 * @param i Index to be deleted.
	 * @return Value at the deleted index.
	 */
	public int deleteFromIndex(int i) {
		if(i > count - 1) {
			return -1;
		}
		int retVal = data[i];
		data[i] = data[count-1];
		count--;
		percolateDown(i);
		return retVal;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * Print all the values that are greater than k
	 * @param k
	 */
	public void printElementsGreaterThan(int k) {
		printElementsGreaterThan(k,0);
	}
	
	private void printElementsGreaterThan(int k, int i) {
		if(i == -1 || i > count-1) {
			return;
		}
		
		if(data[i] > k) {
			System.out.println(data[i]);
		}
		printElementsGreaterThan(k, getLeftIndex(i));
		printElementsGreaterThan(k, getRightIndex(i));
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
	
	private void percolateDown(int i) {
		int l = getLeftIndex(i);
		int r = getRightIndex(i);
		
		int min = i;
		
		if(l != -1 && data[min] > data[l]) { //left is smaller than root
			min = l;
		}
		
		if(r != -1 && data[min] > data[r]) {
			min = r;
		}
		
		if(min == i) {
			return;
		}
		//Swap
		int temp = data[i];
		data[i] = data[min];
		data[min] = temp;
		percolateDown(min);
	}
	
	private void resize() {
		Integer[] newData = new Integer[size * 2];
		size = size * 2;
		for(int i = 0; i < data.length; ++i) {
			newData[i] = data[i];
		}
		
		data = newData;
	}
	
	private Integer[] data;
	private int size;
	private int count;
}
