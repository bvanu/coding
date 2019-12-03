/*
 *  Given an unsorted array of integers, find the length of longest increasing subsequence.
 *  For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
 *  Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *  
 *  Source: https://www.geeksforgeeks.org/longest-increasing-subsequence
 *  
 *  TC: O(n2) dynamic programming 
 */
package leetcode;

public class LongestIncreasingSubsequence {
	public static int lengthOfLIS(int[] nums) {
		if(nums==null || nums.length<1)
			return 0;

		int max = 0;
		int n = nums.length;
		int[] lis = new int[n];

		for(int i =0; i<n; i++)
			lis[i] = 1;

		for(int i=1; i<n ; i++)
		{
			for(int j=0; j<i; j++)
			{
				if(nums[i]> nums[j] && lis[i] < lis[j]+1)
					lis[i] = lis[j] + 1;
			}
		}

		for(int i=0; i<n; i++)
		{
			if(max<lis[i])
				max = lis[i];
		}

		return max;
	}

	public static void main(String args[])
	{
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println("Length of lis is " + lengthOfLIS( arr) );
	}
}
