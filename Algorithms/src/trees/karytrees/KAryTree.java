package trees.karytrees;

import java.util.ArrayList;
import java.util.List;

/**
 * In a k-ary tree, each node has k or 0 children
 * @author aabhi
 *
 */
public class KAryTree <T> {

	public KAryTree(T data) {
//		children = new ArrayList<>();
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void addChild(KAryTree<T> child) {
		if(children == null) {
			children = new ArrayList<>();
		}
		children.add(child);
	}
	
	public List<KAryTree<T>> getChildren() {
		return children;
	}
	
	public static <T> void printKAryTree(KAryTree<T> tree) {
		if(tree == null) {
			return;
		}
		System.out.print(tree.getData() + ",");
		if(tree.getChildren() != null) {
			
			for(KAryTree<T> child: tree.getChildren()) {
				printKAryTree(child);
			}
		}
	}
	
	private T data;
	private List<KAryTree<T>>children;
}
