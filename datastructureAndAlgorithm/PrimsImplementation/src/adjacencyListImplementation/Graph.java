package adjacencyListImplementation;

/**
 * @author SRoy
 * Creates adjacency list representation
 */

public class Graph {
	public int nodes; // number of nodes
	public Node[] graph;

	/**
	 * @param n : number of nodes
	 */
	public Graph(int n) {
		nodes = n;
		graph = new Node[nodes];
		for (int i = 0; i < nodes; i++)
			graph[i] = new Node();
	}

	/**
	 * @param num1 : first node
	 * @param num2 : second node
	 * @param len : edge length/weight
	 * insertEdge: insert directed edge for a pair of num1 and num2 nodes
	 */
	public void insertEdge(int num1, int num2,
			int len) {
		// patch the new node in at the start
		graph[num1].firstEdge = new Edge(num2,
				graph[num1].firstEdge, len);
	}

	/**
	 * Prints the graph in adjacency list order
	 */
	public void printGraph() {
		for (int i = 0; i < nodes; i++) {
			System.out.print(i + ": ");
			Edge e = graph[i].firstEdge;
			while (e != null) {
				System.out.print("[" + e.nodeNum +
						"," + e.edgeLen + "]");
				e = e.nextEdge;
				if (e != null)System.out.print(",");
				else System.out.print("\n");
			}
		}
	}

	/**
	 * @param v1 : node 1
	 * @param v2 : node 2
	 * @return length : edge length/weight of the edge connecting v1 and v2
	 */
	public int edgeLength(int v1, int v2){
		int length = 0;
		for (int i = 0; i < nodes; i++) {
			Edge e = graph[i].firstEdge;
			while (e != null) {
				if(i == v1){
					if(e.nodeNum == v2){
						length = e.edgeLen;
					}
				}
				e = e.nextEdge;
			}
		}		
		return length;
	}

	/**
	 * @param v1 : node 1
	 * @param v2 : node 2
	 * removes the edge between node 1 and node 2 by making the edge length 0 and nodes to -1
	 */
	public void removeEdge(int v1, int v2){
		for (int i = 0; i < nodes; i++) {
			Edge e = graph[i].firstEdge;
			while (e != null) {
				if(i == v1){
					if(e.nodeNum == v2){
						e.edgeLen = 0;
						e.nodeNum = -1;//to mark the node as visited
					}
				}
				e = e.nextEdge;
			}
		}
	}

	/**
	 * @param index : the key node of adjacency list
	 * @return listNode : list of nodes connected to the key
	 */
	public int[] nodeList(int index){
		int n = getNumNodes(index);
		int[] listNode = new int[n];
		int i = 0;
		Edge e = graph[index].firstEdge;
		while (e != null) {
			listNode[i] = e.nodeNum;
			e = e.nextEdge;	        	
			if (e != null)i++;
			else System.out.print(" ");
		}
		return listNode;
	}

	/**
	 * @param index : the key node of adjacency list
	 * @return n : array containing the elements of the node(length, first node, last node)
	 */
	public int[] getNode(int index){
		int[] n = new int[3];
		Edge e = graph[index].firstEdge;
		n[0] = index;
		n[1] = e.edgeLen;
		n[2] = e.nodeNum;
		return n;
	}

	/**
	 * @param index : the key node of adjacency list
	 * @return numNode : number of nodes connected to the key node
	 */
	public int getNumNodes(int index) {
		int numNode = 1;
		Edge e = graph[index].firstEdge;
		while (e != null) {
			e = e.nextEdge;	        	
			if (e != null)numNode++;
			else System.out.print(" ");
		}
		return numNode;
	}

	/**
	 * @param l : edge length
	 * @return v : array containg the two nodes connecting the edge
	 * note : this is not used. Kept for future use
	 */
	public int[][] getVertex(int l) {
		int[][] v = new int[2][2];
		for (int i = 0; i < nodes; i++) {
			Edge e = graph[i].firstEdge;
			while (e != null) {
				if(e.edgeLen == l){
					v[0][0] = i;
					v[0][1] = e.nodeNum;
				}
				e = e.nextEdge;
			}
		}

		return v;
	}
}
