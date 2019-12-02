/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

 * Here’s my attempt to simplify the intuition:

Let’s say we define
number refers to the value of the whole array. In the example provided, since we have 6, 3, 4, 9, 8, 7, 1, so the value of number is 6349871.
element[i] refers to specific value of an element in the array. Here element[2] is 4.

First we should note two important properties:

Number is the smallest one when all the elements all in ascending order, no matter how the permutation could perform on it.
Number is the greatest one when all the elements all in descending order, no matter how the permutation could perform on it.

The goal is to find the next smallest number that is greater than its current number. So we first find the first two adjacent ascending elements from the right, which are 4 & 9 in this example, respectively. We don’t need to do anything with the elements that are in front of these two.

Then because we want the whole number to be the smallest number that is greater than its current number, we want 4 to be replaced with the smallest number from the later array that is greater than 4, which is 7 here

change
6, 3, 4, 9, 8, 7, 1
to
6, 3, 7, 9, 8, 4, 1

Then we don’t need to do anything with elements that are former or equal to 7, which are 6, 3, 7.
At last, we want to make sure the elements later than 7, which are 9, 8, 4, 1 forms the smallest number. So according property 1, we sort it in ascending order to be 1, 4, 8, 9.

So in the end, we have 6, 3, 7, 1, 4, 8, 9.

TC: O(n)
SC: O(1)
In place
 */

package amzn2;

import java.util.Arrays;

public class NextPermutation {
	public void findnextPermutation(int[] nums)
	{
		if(nums==null || nums.length<1)
			return;
		
		// 1. find the adjacent pair that is in ascending order form the right
		int pivot;
		
		for(pivot=nums.length-1; pivot>0; pivot--)
		{
			if(nums[pivot-1]<nums[pivot])
				break;
		}
		
		// 2. swap this element with the first largest element from the right, this is to get he next largest permutation
		if(pivot!=0)
			swap(nums, pivot-1);
		
		// 3. Arrange the elements after pivot in ascending order
		inverse(nums, pivot);		
		
		System.out.println(Arrays.toString(nums));
	}
	
	public void swap(int[] nums, int pivot)
	{
		int j = nums.length - 1;;
		System.out.println("BEFORE SWAP:" + Arrays.toString(nums));
		
		while(pivot<j)
		{
			if(nums[j--]>nums[pivot])
			{
				int temp = nums[pivot];
				nums[pivot] = nums[++j];
				nums[j] = temp;
				
				break;
			}
		}
		
		System.out.println("AFTER SWAP:" + Arrays.toString(nums));
	}
	
	public void inverse(int[] nums, int pivot)
	{
		int end = nums.length-1;
		int start = pivot;
		
		while(start<end)
		{
			int temp = nums[start];
			nums[start++] = nums[end];
			nums[end--] = temp;
		}
	}
	
	public static void main(String[] args)
	{
		NextPermutation np = new NextPermutation();
		
		int[] nums = {1, 2, 3};
		np.findnextPermutation(nums);
	}
}
