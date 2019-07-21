package graphs;

import java.util.LinkedList;
import java.util.List;
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
			System.out.println(adjacencyList[i].stream().map(x -> x.toString()).collect(Collectors.joining("->")));
		}
	}
	
	private List<Integer> adjacencyList[];
	private int verticesLength;
}
