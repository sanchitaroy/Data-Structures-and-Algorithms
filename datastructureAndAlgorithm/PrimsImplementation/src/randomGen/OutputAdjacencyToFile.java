package randomGen;

import java.io.*;

/**
 * @author SRoy
 * outputs the adjacency matrix of connected graph to file
 */
public class OutputAdjacencyToFile {

	/*//For testing only
	public static void main(String[] args) {
		int[][] adjMx = {{-1,  73,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1},  
				{-1,  -1,  -1,  -1,  -1,  -1,  606,  -1,  -1,  -1},  
				{-1,  -1,  -1,  -1,  257,  740,  -1,  -1,  -1,  -1},  
				{65,  -1,  -1,  -1,  -1,  -1,  -1,  886,  -1,  -1},  
				{-1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  8},  
				{-1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1},  
				{-1,  -1,  382,  469,  -1,  -1,  -1,  -1,  815,  -1},  
				{-1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1},  
				{-1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1},  
				{-1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1}}     ;
		writeToFile(adjMx,10,"/home/sanchita/output_test.txt");		
	}*/

	/**
	 * @param adjM : Adjacency matrix of connected graph from Random Generator
	 * @param numNodes : number of nodes
	 * @param fileNameAndPath : full path of the file with file name
	 */
	public static void writeToFile(int[][] adjM, int numNodes, String fileNameAndPath){

		int v1, v2, len;
		int nEdge = 0;		
		String strFile;
		int maxEdgeCount = numNodes*numNodes;
		//int numEdge = 0;
		int outAr[][] = new int[maxEdgeCount][3]; //change for higher order of nodes
		try {

			strFile = fileNameAndPath;
			//to test
			//System.out.println(strFile);
			File file = new File(strFile.toString());
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0; i< adjM.length; i++){
				for(int j = 0; j< adjM[i].length; j++){
					//if(adjM[i][j] != 0){
					v1 = i;
					v2 = j;
					len = adjM[i][j];
					if(len!=-1){
						adjM[j][i] = -1;
						nEdge++;
						outAr[nEdge][0] = v1;
						outAr[nEdge][1] = v2;
						outAr[nEdge][2] = len;
						len = 0;
					}
				}
			}
			outAr[0][1] = nEdge;
			bw.write(numNodes+" "+(nEdge)+" ");
			bw.newLine();
			for(int i = 1; i <= nEdge; i++){
				bw.write(outAr[i][0]+" "+outAr[i][1]+" "+outAr[i][2]);
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No such file exists.");
		}
	}
}
