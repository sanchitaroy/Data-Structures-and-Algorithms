/**
 * @author: SANCHITA
 * @Class: DFSoriginal.java
 * @Description: DFSorignal check the input matrix is connected or not and return TRUE if connected and FLASE if not
 *
 */

package randomGen;

import java.util.*;

/**
 * @author SRoy
 * Checks if adjacency matrix of a graph represents connected graph 
 */
public class DFSCheck{
	Stack<Integer> st;
	int vFirst;
	int[][] adjMatrix;
	int[] isVisited;
	int counter=1;
	public DFSCheck(){

	}	

	/**
	 * @param Matrix : adjacency matrix
	 * @param noVert : number of nodes
	 */
	public DFSCheck(int[][] Matrix, int noVert){
		System.out.println("Readched DFS");
		this.adjMatrix = Matrix;
		this.isVisited=new int[noVert];
		st = new Stack<Integer>();
	} 

	/**
	 * @param firstN : index of adjacency list
	 * @param numNodes : number of nodes connected to vFirst
	 * @return true if graph is connected
	 */
	public boolean dfCheck(int firstN,int numNodes){
		int v,i;
		st.push(firstN);
		boolean flag=false;
		while(!st.isEmpty()){
			v = st.pop();

			if(isVisited[v]==0){
				System.out.print("\n"+(v+1));
				isVisited[v]=1;
			}		
			for ( i=0;i<numNodes;i++){
				if((adjMatrix[v][i] != -1) && (isVisited[i] == 0)){
					st.push(v);
					isVisited[i]=1;
					System.out.print(" " + (i+1));
					v = i;
					counter++;
				}
			}
		}
		if (counter==numNodes){
			System.out.println("Graph connected \nCounter: "+counter);
			flag=true;
			return flag;
		}
		else{
			System.out.println("Graph not connected \nCounter: "+counter);
			return flag;
		}

	}
}	