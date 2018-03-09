/*
 * Coin denomination:
 * Given set of coins of unlimited quantity and a total. How many minimum coins would it take to form this total. 
 * 
 * Source: https://www.youtube.com/watch?v=NJuKJ8sasGk
 * 
 * TC: T(Total*NumOfCoins) Total >>> NumOfCOins, pseudo polynomial
 */
package amzn2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChangingMinCoins {
	public static List<Integer> findMinimumCoinsToMakeTotal(int[] coins, int total)
	{
		if(coins==null || coins.length<1)
			return null;

		// T[i] stores the minimum number of coins to make sum i 
		int[] T = new int[total+1];
		// R[i] stores the jth coin that contributed to make sum i, initialize with -1
		int[] R = new int[total+1];

		// T[0] represents the minimum number of coins to make sum 0 i.e. 0
		T[0] = 0;
		//R[0] = -1;

		// initially fill the remaining T[i] with infinity
		for(int i=1; i<=total; i++)
		{
			T[i] = Integer.MAX_VALUE-1;
			R[i] = -1;
		}

		for(int j=0; j<coins.length; j++)
		{
			for(int i=1; i<=total; i++)
			{
				if(i>=coins[j] && (1+T[i-coins[j]])<T[i])
				{
					T[i] = 1+T[i-coins[j]];
					R[i] = j;
				}
			}
		}
		
		List<Integer> minCoins = getCoinCombinations(R, coins);		
		
		return minCoins;
	}
	
	public static List<Integer> getCoinCombinations(int[] R, int[] coins)
	{
		if(R[R.length-1] == -1)
		{
			System.out.println("No coins can make the total");
			return null;
		}
		
		int index = R.length-1, j;
		List<Integer> res = new ArrayList<Integer>();
		
		while(index != 0)
		{
			j = R[index];
			res.add(coins[j]);
			
			index = index-coins[j];
		}
		
		return res;
	}
	
	public static void main(String[] args)
	{
		int[] coins = {1, 3, 7, 6};
		int total = 13;
		
		System.out.println(findMinimumCoinsToMakeTotal(coins, total));
	}
}
