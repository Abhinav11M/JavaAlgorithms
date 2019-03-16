package trees.generictrees;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Pair;

public class GenericTreeQuestions {
	
	/*
	 * Get the sum of all the elements of a generic tree
	 */
	
	public static int getSumOfAllNodes(GenericTreeNode<Integer> gTree) {
		if(gTree == null) {
			return 0;
		}

		return gTree.getData() + getSumOfAllNodes(gTree.getFirstChild()) + getSumOfAllNodes(gTree.getNextSibling());
	}
	
	/**
	 * Given a parent arr, int[]. int[i] = j => parent of i is j.
	 * Find the depth(max height) of the tree.
	 * For root node, the parent is -1.
	 * <p>Algo:<br/>
	 * For each node, keep going till you find the root (i.e. node for which parent is -1).</br>
	 * Keep a track of max height while iterating for each node.</br>
	 * Ex : Parents : {-1, 0, 1, 6, 6, 0, 0, 2, 7 }
	 * 		Children :  0  1  2  3  4  5  6  7  8
	 * </p>
	 * @param parentArr
	 * @return
	 */
	public static Pair<Integer, List<Integer>> findDepthOfGenericTree(int[] parentArr ) {
		int maxHeight = -1;
		List<Integer> maxHeightPath = new ArrayList<>();
		for(int i = 0; i < parentArr.length; ++i) {
		List<Integer> pathToNode = new ArrayList<>();
			int height = 0;
			int j = parentArr[i];
			pathToNode.add(i);
			while(true) {
				if(j == -1) {
					break;
				}
				pathToNode.add(j);
				++height;
				j = parentArr[j];
			}
			if(height > maxHeight) {
				maxHeight = height;
				maxHeightPath = pathToNode;
			}
		}
		return new Pair<Integer, List<Integer>>(maxHeight, maxHeightPath);
	}
	
	/**
	 * Find the number of siblings of a given node.
	 */
	public static int getNumberOfSiblings(GenericTreeNode<Integer> gTree) {
		if(gTree == null) {
			return 0;
		}
		return 1 + getNumberOfSiblings(gTree.getNextSibling());
	}
	
	/**
	 * Get the number of children of a given node.
	 */
	public static int getNumberOfChildren(GenericTreeNode<Integer> gTree) {
		if(gTree == null) {
			return 0;
		}
		
		int numOfChildren = 0; 
		gTree = gTree.getFirstChild();
		if(gTree == null) {// No children
			return 0;
		}
		
		++numOfChildren;
		while(gTree.getNextSibling() != null) {
			++numOfChildren;
			gTree = gTree.getNextSibling();
		}
		
		return numOfChildren;
	}
	
	/**
	 * Create a generic tree
	 * @return
	 */
	public static GenericTreeNode<Integer> createGenericTree() {
		GenericTreeNode<Integer> gTree1 = new GenericTreeNode<Integer>(1);
		GenericTreeNode<Integer> gTree2 = new GenericTreeNode<Integer>(2);
		GenericTreeNode<Integer> gTree3 = new GenericTreeNode<Integer>(3);
		GenericTreeNode<Integer> gTree4 = new GenericTreeNode<Integer>(4);
		GenericTreeNode<Integer> gTree5 = new GenericTreeNode<Integer>(5);
		GenericTreeNode<Integer> gTree6 = new GenericTreeNode<Integer>(6);
		GenericTreeNode<Integer> gTree7 = new GenericTreeNode<Integer>(7);
		GenericTreeNode<Integer> gTree8 = new GenericTreeNode<Integer>(8);
		GenericTreeNode<Integer> gTree9 = new GenericTreeNode<Integer>(9);
		GenericTreeNode<Integer> gTree10 = new GenericTreeNode<Integer>(10);
		GenericTreeNode<Integer> gTree11 = new GenericTreeNode<Integer>(11);
		GenericTreeNode<Integer> gTree12 = new GenericTreeNode<Integer>(12);
		GenericTreeNode<Integer> gTree13 = new GenericTreeNode<Integer>(13);
		GenericTreeNode<Integer> gTree14 = new GenericTreeNode<Integer>(14);
		
		gTree1.setFirstChild(gTree2);
		gTree2.setNextSibling(gTree3);
		gTree3.setNextSibling(gTree4);
		gTree4.setNextSibling(gTree5);
		gTree5.setNextSibling(gTree6);
		gTree2.setFirstChild(gTree7);
		gTree7.setNextSibling(gTree8);
		gTree3.setFirstChild(gTree9);
		gTree9.setNextSibling(gTree10);
		gTree10.setNextSibling(gTree11);
		gTree8.setFirstChild(gTree12);
		gTree12.setNextSibling(gTree13);
		gTree13.setNextSibling(gTree14);
		
		return gTree1;
	}
	
	public static void main(String[] args) {
		GenericTreeNode<Integer> gTree = createGenericTree();
		System.out.println(getSumOfAllNodes(gTree));
		Pair<Integer, List<Integer>> heightAndPath = findDepthOfGenericTree(new int[] {-1, 0, 1, 6, 6, 0, 0, 2, 7 });
		System.out.println("Height = " + heightAndPath.left);
		System.out.println("Path = " + heightAndPath.right.stream().map(String::valueOf).collect(Collectors.joining(",")));
		System.out.println("Number of silings : " + getNumberOfSiblings(gTree.getFirstChild()));
		System.out.println("Number of children : " + getNumberOfChildren(gTree));
	}
}
