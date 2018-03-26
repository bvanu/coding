/*
 * Given a sorted array which is rotated n number of times, find the given element in such array
 * 
 * TC: O(logn)
 */

package amzn2;

public class RotatedSortedArray {
	public static int modifiedBinarySearch(int[] arr, int start, int end, int value)
	{
		if(arr==null || arr.length<1)
			return -1;
		
		if(start<=end)
		{			
			int mid = (start+end)/2;
			
			if(arr[mid]==value)
				return mid;
			
			// right subarry is sorted
			if(arr[mid]<arr[end])
			{
				if(value>arr[mid] && value <=arr[end])
					return modifiedBinarySearch(arr, mid+1, end, value);
				else
					return modifiedBinarySearch(arr, start, mid-1, value);
			}
			else // left sub array is sorted
			{
				if(value>=arr[start] && value <arr[mid])
					modifiedBinarySearch(arr, start, mid-1, value);
				else
				{
					return modifiedBinarySearch(arr, mid+1, end, value);
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {4, 5, 6, 7, 0, 1, 2 };
		System.out.println(modifiedBinarySearch(arr, 0, arr.length-1, 7));
		
		int[] arr2 = {4, 5, 6, 7, 0, 1, 2 };
		System.out.println(modifiedBinarySearch(arr2, 0, arr.length-1, 2));
	}	
}
