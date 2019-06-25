package Heaps;

import org.apache.commons.lang3.Pair;

public class MinMaxHeap {
	
	@SuppressWarnings("unchecked")
	MinMaxHeap(int size) {
		this.size = size;
		minData = (Pair<Integer, Integer>[])new Pair[size];
		maxData = (Pair<Integer, Integer>[])new Pair[size];
		this.count = 0;
	}

	@SuppressWarnings("unchecked")
	public void add(int argData) {
		if(count == size) {
			Pair<Integer, Integer>[] newMinData  = (Pair<Integer, Integer>[])new Pair[size * 2];
			Pair<Integer, Integer>[] newMaxData  = (Pair<Integer, Integer>[])new Pair[size * 2];
			
			for(int i = 0; i < minData.length; ++i) {
				newMaxData[i] = minData[i];
				newMaxData[i] = maxData[i];
				
				minData = newMinData;
				maxData = newMaxData;
			}
			
			Pair<Integer, Integer> temp = Pair.of(argData, count);
			
			int index = minInsert(temp);
		}
	}
	
	private int minInsert(Pair<Integer, Integer> temp) {
		minData[count] = temp;
		minPercolateDown(0);
		return 0;
	}

	private int minPercolateDown(int index) {
		if(minData.length == 0) {
			return -1;
		}
		
		int left = getLeft(minData, index);
		int right = getRight(minData, index);
		
		int minIndex = index;
		
		if(left != -1 && minData[minIndex].left > minData[left].left) {
			minIndex = left;
		}
		
		if(right != -1 && minData[minIndex].left > minData[right].left) {
			minIndex = right;
		}
		
		// Swap
		if(minIndex != index ) {
			Pair<Integer, Integer> temp = minData[index];
			minData[index] = minData[minIndex];
			minData[minIndex] = temp;
		}
		
		minPercolateDown(minIndex);
	}

	private int getRight(Pair<Integer, Integer>[] maxData, int index) {
		int right = 2*index + 2;
		if(right > count) {
			return -1;
		}
		
		return right;
	}

	private int getLeft(Pair<Integer, Integer>[] minData, int index) {
		int left = 2*index + 1;
		if(left > count) {
			return -1;
		}
		
		return left;
	}

	private int size;
	private int count;
	// List of pair of data and the corresponding index maxHeap;
	private Pair<Integer, Integer>[] minData;
	// List of pair of data and the corresponding index minHeap;
	private Pair<Integer, Integer>[] maxData;
}
