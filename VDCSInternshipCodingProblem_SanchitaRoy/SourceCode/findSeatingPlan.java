package findSeatingPlan;

import java.util.Arrays;

/**
 * @author Sanchita Roy
 * 
 * Algorthim : 
 * Provided, n is a multiple of 2. N:S = 1:1 for the whole class. Objective is -
 * - All rows will have the same N:S ratio
 * - All columns will have different N:S ratio
 * - The ratio of smart to non smart students is as different as possible in every diagonal.
 * 
 * Since N:S = 1:1, and n is fixed for all rows (square matrix), hence, for each row, 
 * N:S must be 1:1. Also, if all columns have different ratios, each column will have a
 * different number of N or S (since n for each column is fixed). For all ratios to be different,
 * hence, the columns will have N or S ranging from 0 to n/2-1 and n/2+1 to n [if any column have
 * N or S as n/2, it violates either the row ratio or the column ratio property].
 * 
 * 1. Fill the matrix till n/2+1 rows with n/2 'N' from the left and then remaining n/2 'S'
 * 2. For the rest of the matrix :
 * 		i.  From the left, fill lower/left triangular matrix with 'N' till column n/2-1
 * 			ie -		
 * 						|\
 * 						| \
 * 						|__\
 * 		ii. Fill upper left triangular matrix with 'N' from column n/2 to n-1
 * 			ie - 		
 * 						|---/
 * 						|  /
 * 						| /
 * 		iii. Fill the rest by 'S'
 * 3. Swap column 1 and column n in the resultant matrix.
 * 4. Swap row 1 and n/2+1 in the resultant matrix.
 * 
 */
public class findSeatingPlan {

	static char[][] studMatrix;
	static int numRows = 0;

	public static void main(String args[]){

		if (args.length != 1){
			System.out.println("Please enter single argument : Number of rows (multiple of 2).\nExiting program...");
			return;
		}
		else{
			numRows = Integer.parseInt(args[0]);
			if(numRows%2 != 0){
				System.out.println("Number of rows should be a multiple of 2.\nExiting program...");
				return;
			}
		}

		if(numRows <=2){
			System.out.println("Desired seating arrangement can not be made for 1 or 2 rows where all the properties are maintained. \nPlease enter a different value. \nExiting program...");
			return;
		}
		else{
			studMatrix = getSittingArr(numRows);	
			validate(numRows);
			printMatrix(studMatrix);
		}		
	}


	/**
	 * @param n : number of students in the class.
	 * n is a multiple of 2.
	 * @return : the built matrix - seating arrangement according to the properties :
	 * 
	 * Time complexity : n^2
	 */
	public static char[][] getSittingArr(int n){

		studMatrix = new char[n][n];

		//N:S students is 1:1 (mentioned). 
		//Hence, we consider the ratio per row of the arrangement also as 1:1
		int ratioNum = n/2;
		
		//Observed data : for matrix with dimension less than 10x10, number of distinct diagonals
		//are more if no swapping is done.
		if(n<10){
			for(int i = 0; i<n; i++){
				for(int j = 0; j<n; j++){

					//fill the matrix till ratioNum+1 rows as n/2 as N and remaining as S 
					if(i<=ratioNum){
						if(j<n/2){
							studMatrix[i][j] = 'N';
						}
						else{
							studMatrix[i][j] = 'S';
						}
					}

					//fill bottom matrix to satisfy the three conditions given
					else{
						if((i-(ratioNum+1)<j && j<ratioNum) || 
								((n-1)-i) < (j-ratioNum)){
							studMatrix[i][j] = 'S';
						}
						else{
							studMatrix[i][j] = 'N';
						}
					}				
				}
			}
		}

		else{
			for(int i = 0; i<n; i++){
				for(int j = 0; j<n; j++){

					//column swap - An alternate way to swapping the 1st and last column
					//after the matrix fill as described in the algorithm
					if(j == 0){
						studMatrix[i][j] = 'S';
					}
					else if(j==n-1){
						studMatrix[i][j] = 'N';
					}

					//row swap - An alternate way to swapping the 1st and n/2+1th row
					//after the matrix fill as described in the algorithm
					else if(i == 0 && j<ratioNum){
						studMatrix[i][j] = 'S';
					}
					else if(i == 0 && j>=ratioNum){
						studMatrix[i][j] = 'N';
					}
					else if(i == ratioNum+1 && j!=0 && j<ratioNum){
						studMatrix[i][j] = 'N';
					}
					else if(i == ratioNum+1 && j!=n-1 && j>=ratioNum){
						studMatrix[i][j] = 'S';
					}

					//fill the matrix till ratioNum+1 rows as n/2 as N and remaining as S 
					else if(i<=ratioNum){
						if(j<n/2){
							studMatrix[i][j] = 'N';
						}
						else{
							studMatrix[i][j] = 'S';
						}
					}

					//fill bottom matrix to satisfy the three conditions given
					else{
						if((i-(ratioNum+1)<j && j<ratioNum) || 
								((n-1)-i) < (j-ratioNum)){
							studMatrix[i][j] = 'S';
						}
						else{
							studMatrix[i][j] = 'N';
						}
					}				
				}
			}
		}
		return studMatrix;		
	}

	/**
	 * @param matrix
	 * 
	 * source : http://www.tutorialspoint.com/java/util/arrays_deeptostring.htm
	 * 
	 */
	public static void printMatrix(char[][] matrix){
		System.out.println("Please see the seating arrangement below :");
		System.out.println(Arrays.deepToString(matrix).replaceAll("],", "],\r\n"));
	}

	/**
	 * @param n : dimension of matrix
	 * 
	 * Tests the properties :
	 * - All rows have same ratio of N:S
	 * - All columns have different ratio of N:S
	 * - Counts the number of diagonals for each ratio and the number of distinct ratios.
	 * 
	 * Time complexity : n^2
	 */
	public static void validate(int n){
		float numS, numN, ratio;
		float r1 = 0;
		float r2 = 0;
		float[] ratioArr = new float[2*(2*n-1)]; //number of diagonals
		int count = 0;
		int countDistinct = 0;
		int countRowElN = 0;
		int countColElN = 0;
		float[] colTest = new float[n];
		int i, j;

		//row and column test
		for(i = 0; i<n; i++){	
			for(j = 0; j<n; j++){
				if(studMatrix[i][j] == 'N'){
					countRowElN++;
				}	

				//prepare column test array
				if(studMatrix[j][i] == 'N'){
					countColElN++;
				}				
			}
			if(i == 0){
				r1 = (float)countRowElN/(n-countRowElN);
			}

			//the below can be avoided by using only the variable countRowElN instead of ratio
			//since, no x:y = m:n exist where x!=m and y!=n and x+y=m+n
			//Thus, this implies, for the second property, for each column, there
			//would be unique number of 'N's or 'S's.

			//row test
			r2 = (float)countRowElN/(n-countRowElN);
			if(r1 != r2){
				System.out.println("Error : All rows do not have same ratio. \nExiting...");
				System.exit(0);
			}

			//column test
			colTest[i] = (float)countColElN/(n-countColElN);
			countRowElN = 0;
			countColElN = 0;
		}		

		//column test
		for(i = 0; i<n; i++){
			for(j=i+1; j<n; j++){
				if(colTest[i] == colTest[j]){
					System.out.println("Error : column "+i+" and column "+j+" has same ratio. \nExiting...");
					System.exit(0);
				}
			}
		}

		//diagonal test		
		for (i = 0; i < n*2-1; ++i) {
			numS = 0;
			numN = 0;
			ratio = 0;
			int z = i < n ? 0 : i - n + 1;
			for (j = z; j <= i - z; ++j) {
				if(studMatrix[j][(n-1)-(i-j)] == 'S'){
					numS++;
				}
				else{
					numN++;
				}
			}
			ratio = numS/numN;
			ratioArr[count] = ratio;
			count++;
		}

		for (i = 0; i < 2 * n - 1; ++i) {
			numS = 0;
			numN = 0;
			ratio = 0;
			int z = i < n ? 0 : i - n + 1;
			for (j = z; j <= i - z; ++j) {
				if(studMatrix[j][i - j] == 'S'){
					numS++;
				}
				else{
					numN++;
				}
			}	    	
			ratio = numS/numN;
			ratioArr[count] = ratio;
			count++;
		}

		for(i = 0; i<ratioArr.length; i++){
			if(ratioArr[i] == -1){
				continue;
			}
			count = 1;
			for(j = i+1; j<ratioArr.length; j++){
				if(ratioArr[i] == ratioArr[j]){
					count++;
					ratioArr[j] = -1;
				}
			}
			System.out.println("ratio : "+ratioArr[i] +"\thas diagonals : "+count);
			countDistinct++;
		}

		System.out.println("All rows have N:S ratio as : "+r1);
		System.out.println("All columns have different N:S ratio.");
		System.out.println("Count of distinct diagonal ratios : "+ countDistinct);
	}
}
