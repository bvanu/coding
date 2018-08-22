/*
 * iven an array, rotate the array to the right by k steps, where k is non-negative.
Example 1:
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:
Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

TC: O(n)
SC: O(1)
 */
package amzn2;

import java.util.Arrays;

public class RotateArrayKTimes {
	public int[] rotateKTimes(int[] arr, int k)
	{
		if(arr==null || arr.length<1)
			return null;
		
		int n = arr.length;
		
		if(k>=n)
			k = k%n;
		
		// to left <-
		reverseArray(arr, 0, k-1);
		reverseArray(arr, k, n-1);
		reverseArray(arr, 0, n-1);
		
		// to the right ->
		//reverseArray(arr, 0, n-k-1);
		//reverseArray(arr, n-k, n-1);
		//reverseArray(arr, 0, n-1);
		
		return arr;
	}
	
	public int[] reverseArray(int[] arr, int start, int end)
	{
		while(start<end)
		{
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			
			start++;
			end--;
		}
		
		return arr;
	}
	
	public static void main(String[] args)
	{
		RotateArrayKTimes ra = new RotateArrayKTimes();
		
		int[] arr = {1,2,3,4};
		System.out.println(Arrays.toString(ra.rotateKTimes(arr, 3)));
	}
}
