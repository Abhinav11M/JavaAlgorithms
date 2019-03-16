package trees.karytrees;

public class KAryTreeQuestions {
	private static int index = 0;
	
	/**
	 * Given a pre-order traversal, create a K-Ary binary Tree
	 * <b>Important condition: index * k + i < preOrder.length</b>
	 */
	public KAryTree<Integer> createKAryTreeFromPreOrder(int preOrder[], int k) {
		if(index > preOrder.length) {
			return null;
		}

		KAryTree<Integer> tree = new KAryTree<>(preOrder[index++]);
		// Set the children
		for(int i = 0; i < k; ++i) {
			if(index * k + i < preOrder.length) {
				// recursively add child and create its subtree
				tree.addChild(createKAryTreeFromPreOrder(preOrder, k));
			}
			else {
				if(index < preOrder.length)
					tree.addChild(new KAryTree<Integer>(preOrder[index++]));
			}
		}
		
		return tree;
	}
	
	public static void main(String[] args) {
		KAryTreeQuestions ob = new KAryTreeQuestions();
		KAryTree<Integer> tree = ob.createKAryTreeFromPreOrder(new int[] {1,2,3,4,6,7,8,9,10,11}, 3);
		KAryTree.printKAryTree(tree);
	}
}
