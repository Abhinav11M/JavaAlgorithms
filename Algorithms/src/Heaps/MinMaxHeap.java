package Heaps;

import java.util.HashMap;
import java.util.Map;

public class MinMaxHeap {
	
	MinMaxHeap(int size) {
		this.size = size;
		minData = new int[size];
		maxData = new int[size];
		this.count = 0;
		minToMaxIndex = new HashMap<>();
		maxToMinIndex = new HashMap<>();
	}

	public void add(int argData) {
		if(count == 0) {
			minData[0] = argData;
			maxData[0] = argData;
			minToMaxIndex.put(0, 0);
			maxToMinIndex.put(0, 0);
			++count;
			return;
		}
		
		if(count == size) {
			int[] newMinData  = new int[size * 2];
			int[] newMaxData  = new int[size * 2];
			
			for(int i = 0; i < minData.length; ++i) {
				newMaxData[i] = minData[i];
				newMaxData[i] = maxData[i];
				
				minData = newMinData;
				maxData = newMaxData;
			}
		}
		
		minToMaxIndex.put(count, count);
		maxToMinIndex.put(count, count);

		minData[count] = argData;
		minHeapyfy(count);

		maxData[count] = argData;
		maxHeapify(count);

   		++count;
	}
	
	private void minHeapyfy(int index) {
		int i = count;
		while(i >= 0 && minData[(i-1)/2] > minData[i]) {
			// Swap and update the map
			int temp = minData[i];
			minData[i] = minData[(i-1)/2];
			minData[(i-1)/2] = temp;
			
			// Since the location of the data changed, we need to update the map of indices.
			// Swapping elements of maxToMin
			// Swap keys of the map of min to max map; Since the index of minData in changing.
			// This is equivalent to swapping the values at these given keys.
			swapKeys(minToMaxIndex, i, (i-1)/2);
			
			// Adjust the maxToMin map now. So we need to swap the values of old and new indices.
			// We need to first find the keys to those and then swap the values.
			swapKeys(maxToMinIndex, i, (i-1)/2);
			i = (i-1)/2;
		}
	}
	
	private void maxHeapify(int index) {
		int i = count;
		while(i >= 0 && maxData[(i-1)/2] < maxData[i]) { // Parent is less than the child.
			// Swap and update the map
			int temp = maxData[i];
			maxData[i] = maxData[(i-1)/2];
			maxData[(i-1)/2] = temp;
			
			// Since the location of the data changed, we need to update the map of indices.
			// Swapping elements of maxToMin
			// Swap keys of the map of min to max map; Since the index of minData in changing.
			// This is equivalent to swapping the values at these given keys.
			swapKeys(minToMaxIndex, i, (i-1)/2);
			
			// Adjust the maxToMin map now. So we need to swap the values of old and new indices.
			// We need to first find the keys to those and then swap the values.
			swapKeys(maxToMinIndex, i, (i-1)/2);
			i = (i-1)/2;
		}
	}

	private void swapKeys(Map<Integer, Integer> minToMaxIndex, int oldIndex, int newIndex) {
		int temp = minToMaxIndex.get(oldIndex);
		minToMaxIndex.put(oldIndex, minToMaxIndex.get(newIndex));
		minToMaxIndex.put(newIndex, temp);
	}

//	private int getRight(int index) {
//		int right = 2*index + 2;
//		if(right > count) {
//			return -1;
//		}
//		
//		return right;
//	}
//
//	private int getLeft(int index) {
//		int left = 2*index + 1;
//		if(left > count) {
//			return -1;
//		}
//		
//		return left;
//	}

	private int size;
	private int count;
	// Data to hold minHeap;
	private int[] minData;
	// Data to hold maxHeap;
	private int[] maxData;
	Map<Integer, Integer> minToMaxIndex;
	Map<Integer, Integer> maxToMinIndex;
}
