/*
 * Given 2 array of size m , n , find the pair from diff array with minimum difference .
 * 
 * TC: O(nlogn)
 */

package amzn2;

import java.util.Arrays;

public class MinDifferencePair {
	public static int[] findPair(int[] a, int[] b)
	{
		if(a==null || b==null)
			return null;
		
		// merge sort arrays, O(nlogn)
		Arrays.sort(a);
		Arrays.sort(b);
		
		int[] minPair = new int[2];
		int m = a.length, n = b.length;
		int i = 0, j = 0;
		int min = Integer.MAX_VALUE, currMin;
		
		// merging as in merge sort, O(logn)
		while(i<m && j<n)
		{
			currMin = Math.abs(a[i]-b[j]);
			
			if(currMin < min)
			{
				min = currMin;
				minPair[0] = a[i];
				minPair[1] = b[j];
			}
			
			if(min == 0)
				break;
			
			if(a[i]<b[j])
				i++;
			else
				j++;
		}
		
		return minPair;
	}
	
	public static void main(String[] args)
	{
		int[] a = {2, 5, 11};
		int[] b = {10, 9, 4, 1};
		
		int[] res = findPair(a, b);
		System.out.println(Arrays.toString(res));
	}
}