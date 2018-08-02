package amzn2;

/*
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * This problem should be solved in place, i.e., no other array should be used.
 * 
 * TC: O(mn)
 * SC: O(1), In-place
 */

import java.util.Arrays;

public class SetMatrixZeros {
	public static void setZeroes(int[][] matrix) {
        if(matrix==null || matrix.length<1)
            return;
        
        // print array
        System.out.println("INPUT:");
        print2DArray(matrix);
        
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
        int m = matrix.length;
        int n = matrix[0].length;
        
        // set first row zero or not
        for(int i=0; i<m; i++)
        {
            if(matrix[i][0]==0)
            {
                firstRowZero = true;
                break;
            }
        }
        
        // set first col zero or not
        for(int j=0; j<n; j++)
        {
            if(matrix[0][j]==0)
            {
                firstColumnZero = true;
                break;
            }
        }
        
        // mark zeros on first row and column
        for(int i=1; i<m; i++)
        {
            for(int j=1; j<n; j++)
            {
                if(matrix[i][j]==0)
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // use mark zeros toset elements
        for(int i=1; i<m; i++)
        {
            for(int j=1; j<n; j++)
            {
                if(matrix[i][0]==0 || matrix[0][j]==0)
                    matrix[i][j] = 0;
            }
        }
        
        // set first row zero
        if(firstRowZero)
        {
            for(int i=0; i<m; i++)
            {
                matrix[i][0] = 0;
            }
        }
        
        // set first col zero
        if(firstColumnZero)
        {
            for(int j=0; j<n; j++)
            {
                matrix[0][j] = 0;
            }
        }
        
        // print array
        System.out.println("OUTPUT:");
        print2DArray(matrix);
        
    }
	
	public static void print2DArray(int[][] matrix)
	{
		 if(matrix==null || matrix.length<1)
	            return;
		 
		for(int i=0; i<matrix.length; i++)
		{
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
	
	public static void main(String[] args)
	{
		int[][] matrix = {{1,1,1}, {1,0,1}, {1,1,1}};
		setZeroes(matrix);
	}
}
