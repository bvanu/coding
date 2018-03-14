/*
 * Number of ways to make given sum with given coins
 * 
 * Coins: {3, 2, 1}
 * Total: 5
 * 
 * Output:
 * {1, 1, 1, 1, 1}
 * {1, 1, 1, 2}
 * {1, 1, 3}
 * {1, 2, 2}
 * {2, 3}
 * 5 ways
 * 
 * 	i/j=0	1	2	3	4	5	
 * 	0	1	0	0	0	0	0	
 * 	3	1	0	0	1	0	0	
 * 	2	1	0	1	1	1	1	
 * 	1	1	1	2	3	4	5	
 * 	
 * Number of possibilities: 5
 * 
 * TC: O(mn) ~ T(Total*NumOfCoins) ~ T(Total), Total >>> NumOfCOins, pseudo polynomial
 * SC: O(mn)
 * 
 * Source: https://www.youtube.com/watch?v=_fgjrs570YE
 */

package amzn2;

import java.util.Arrays;

public class CoinChanging {

	public static int numberOfSolutions(int coins[], int total)
	{

		int temp[][] = new int[coins.length+1][total+1];

		for(int i=0; i <= coins.length; i++)
		{
			temp[i][0] = 1;
		}

		for(int i=1; i <= coins.length; i++)
		{
			for(int j=1; j <= total ; j++)
			{
				if(coins[i-1] > j)
				{
					temp[i][j] = temp[i-1][j];
				}
				else
				{
					temp[i][j] = temp[i][j-coins[i-1]] + temp[i-1][j];
				}
			}
		}

		printMatrix(temp, coins);
		return temp[coins.length][total];
	}

	public static void printMatrix(int[][] matrix, int[] coins)
	{
		System.out.print("\t");
		for(int i=0; i<matrix[0].length; i++)
		{
			System.out.print(i + "\t");
		}
		System.out.println();

		for(int i=0; i<matrix.length; i++)
		{
			if(i==0)
			{
				System.out.print("0\t");
			}
			else
			{
				System.out.print(coins[i-1] + "\t");
			}
			
			for(int j=0; j<matrix[i].length; j++)
			{
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	 /**

     * Space efficient DP solution

     */

    public int numberOfSolutionsOnSpace(int total, int arr[])
    {
        int temp[] = new int[total+1];
        temp[0] = 1;

        for(int i=0; i < arr.length; i++)
        {
            for(int j=1; j <= total ; j++)
            {
                if(j >= arr[i])
                {
                    temp[j] += temp[j-arr[i]];
                }
            }
        }
        
        return temp[total];
    }

	public static void main(String[] args)
	{
		int[] coins = {1, 3, 7, 6};
		int total = 13;

		System.out.println("Number of possibilities: " + numberOfSolutions(coins, total) + "\n\n");

		int[] coins2 = {2};
		int total2 = 3;

		System.out.println("Number of possibilities: " + numberOfSolutions(coins2, total2) + "\n\n");

		int[] coins3 = {1, 2, 3};
		int total3 = 5;

		System.out.println("Number of possibilities: " + numberOfSolutions(coins3, total3) + "\n\n");
		
		int[] coins4 = {3, 2, 1};
		int total4 = 5;

		System.out.println("Number of possibilities: " + numberOfSolutions(coins4, total4) + "\n\n");
	}

}
