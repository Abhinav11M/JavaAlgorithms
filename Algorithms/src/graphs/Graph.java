package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph {

	@SuppressWarnings("unchecked")
	public Graph(int verticesLen) {
		this.verticesLength = verticesLen;
		adjacencyList = new LinkedList[verticesLen];
		
		for(int i = 0; i < verticesLen; ++i) {
			adjacencyList[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int a, int b) {
		adjacencyList[a].add(b);
		adjacencyList[b].add(a);
	}
	
	public void printGraph() {
		for(int i = 0; i < verticesLength; ++i) {
			System.out.println(Integer.toString(i) + "=>"
					+ adjacencyList[i].stream().map(x -> x.toString()).collect(Collectors.joining("->")));
		}
	}
	
	public void bfsTraversal(int startNode) {
		boolean[] isVisited = new boolean[verticesLength];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startNode);
		
		while(!queue.isEmpty()) {
			int nodeToTraverse = queue.poll();
			if(isVisited[nodeToTraverse] != true) {
				System.out.print(nodeToTraverse + ", ");
				isVisited[nodeToTraverse] = true;
			}
			// print all the nodes linked to nodeToTraverse
			for(int i = 0; i < adjacencyList[nodeToTraverse].size(); ++i) {
				if(isVisited[adjacencyList[nodeToTraverse].get(i)] == false) {
					System.out.print(adjacencyList[nodeToTraverse].get(i) + ", ");
					isVisited[adjacencyList[nodeToTraverse].get(i)] = true;
					queue.add(adjacencyList[nodeToTraverse].get(i));
				}
			}
		}
	}
	
	private boolean allVisited(List<Integer> list, boolean[] isVisited) {
		for(int i = 0; i < list.size(); ++i ) {
			if(isVisited[list.get(i)] == false) {
				return false;
			}
		}
		return true;
	}

	public void dfsTraversal(int startNode) {
		dfsTraversal(startNode, new boolean[verticesLength]);
	}
	
	public void dfsTraversal(int startNode, boolean[] isVisited) {
		if(isVisited[startNode] == true) {
			return;
		}
		
		if(adjacencyList[startNode].size() == 0 || allVisited(adjacencyList[startNode], isVisited)) {
			System.out.print(Integer.toString(startNode) + ",");
			isVisited[startNode] = true;
			return;
		}
		
		System.out.print(Integer.toString(startNode) + ",");
		isVisited[startNode] = true;
		
		for(int i = 0; i < adjacencyList[startNode].size(); ++i) {
			dfsTraversal(adjacencyList[startNode].get(i), isVisited);
		}
	}
	

	private List<Integer> adjacencyList[];
	private int verticesLength;
}
