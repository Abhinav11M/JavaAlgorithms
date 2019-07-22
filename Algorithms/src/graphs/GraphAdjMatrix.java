package graphs;

public class GraphAdjMatrix {
	public GraphAdjMatrix(int numOfVertices) {
		numVertices = numOfVertices;
		adjacencyMatrix = new int[numOfVertices][numOfVertices];
		
		initializeAdjMatrix();
	}
	
	private void initializeAdjMatrix() {
		for(int i = 0; i < adjacencyMatrix.length; ++i) {
			for(int j = 0; j < adjacencyMatrix.length; ++j ) {
				adjacencyMatrix[i][j] = 0;
			}
		}
	}

	public void addEdge(int a, int b) {
		adjacencyMatrix[a][b] = 1;
		adjacencyMatrix[b][a] = 1;
	}
	
	public void printAdjacencyMatrix() {
		for(int i = 0; i < adjacencyMatrix.length; ++i) {
			for(int j = 0; j < adjacencyMatrix.length; ++j ) {
				System.out.print(adjacencyMatrix[i][j] + ",");
			}
			System.out.println();
		}
	}
	
	int[][] adjacencyMatrix;
	private int numVertices;
}
