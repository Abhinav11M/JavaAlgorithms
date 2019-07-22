package graphs;

public class GraphTest {

	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.addEdge(0, 1); 
	    g.addEdge(0, 4); 
	    g.addEdge(1, 2); 
	    g.addEdge(1, 3); 
	    g.addEdge(1, 4); 
	    g.addEdge(2, 3); 
	    g.addEdge(3, 4); 
	    
	    g.printGraph();
	    
	    System.out.println("Adjacency Matrix method");
	    
	    GraphAdjMatrix g1 = new GraphAdjMatrix(5);

	    g1.addEdge(0, 1); 
	    g1.addEdge(0, 4); 
	    g1.addEdge(1, 2); 
	    g1.addEdge(1, 3); 
	    g1.addEdge(1, 4); 
	    g1.addEdge(2, 3); 
	    g1.addEdge(3, 4); 
	    
	    g1.printAdjacencyMatrix();
	}

}
