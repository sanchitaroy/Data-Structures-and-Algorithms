package adjacencyListImplementation;

/**
 * @author SRoy
 * returns the Edge type variable
 */
public class Edge {
	public int nodeNum; // at end of edge
	public Edge nextEdge; // next in adj list
	public int edgeLen; // edge length
	
	/**
	 * @param num : node number
	 * @param e : edge = next node num
	 * @param len : edge length/weight
	 */
	public Edge(int num, Edge e, int len) {
		nodeNum = num;
		nextEdge = e;
		edgeLen = len;
	}
}