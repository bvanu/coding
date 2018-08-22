/*
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Example 1:
Input:
11110
11010
11000
00000

Output: 1
Example 2:
Input:
11000
11000
00100
00011

Output: 3

TC: O(mnlogn)
SC: O(mn)
 */

package amzn2;

import java.util.Arrays;

public class NumberOfIslands {
	public int numIslands(char[][] grid) 
	{
        if(grid==null || grid.length<1)
        	return 0;
        
        int numOfIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] isVisited = new boolean[m][n];
        
        System.out.println(Arrays.deepToString(grid));
        System.out.println(Arrays.deepToString(isVisited));
        
        for(int i=0; i<m; i++)
        {
        	for(int j=0; j<n; j++)
        	{
        		if(grid[i][j]=='1' && !isVisited[i][j])
        		{
        			numOfIslands++;
        			DFS(grid, i, j, isVisited);
        		}
        		
        	}
        }
        
        return numOfIslands;
    }
	
	public void DFS(char[][] grid, int i, int j, boolean[][] isVisited) 
	{
		int m = grid.length;
		int n = grid[0].length;
		
		if(i<0 || i>=m || j<0 || j>=n || grid[i][j]=='0' || isVisited[i][j])
			return;
		
		isVisited[i][j] = true;
		
		DFS(grid, i+1, j, isVisited);
		DFS(grid, i-1, j, isVisited);
		DFS(grid, i, j+1, isVisited);
		DFS(grid, i, j-1, isVisited);
	}
	
	public static void main(String[] args)
	{
		NumberOfIslands ni = new NumberOfIslands();
		
		char[][] grid = {{'1', '0', '0'}, {'0', '1', '1'}, {'0', '1', '1'}};
		System.out.println(ni.numIslands(grid));;
	}
}
