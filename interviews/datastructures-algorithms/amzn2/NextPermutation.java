/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

 * Single Pass Approach

Algorithm

First, we observe that for any given sequence that is in descending order, no next larger permutation is possible. For example, no next permutation is possible for the following array:

[9, 5, 4, 3, 1]
We need to find the first pair of two successive numbers 
a
[
i
]
a[i] and 
a
[
i
−
1
]
a[i−1], from the right, which satisfy 
a
[
i
]
>
a
[
i
−
1
]
a[i]>a[i−1]. Now, no rearrangements to the right of 
a
[
i
−
1
]
a[i−1] can create a larger permutation since that subarray consists of numbers in descending order. Thus, we need to rearrange the numbers to the right of 
a
[
i
−
1
]
a[i−1] including itself.

Now, what kind of rearrangement will produce the next larger number? We want to create the permutation just larger than the current one. Therefore, we need to replace the number 
a
[
i
−
1
]
a[i−1] with the number which is just larger than itself among the numbers lying to its right section, say 
a
[
j
]
a[j].

 Next Permutation 

We swap the numbers 
a
[
i
−
1
]
a[i−1] and 
a
[
j
]
a[j]. We now have the correct number at index 
i
−
1
i−1. But still the current permutation isn't the permutation that we are looking for. We need the smallest permutation that can be formed by using the numbers only to the right of 
a
[
i
−
1
]
a[i−1]. Therefore, we need to place those numbers in ascending order to get their smallest permutation.

But, recall that while scanning the numbers from the right, we simply kept decrementing the index until we found the pair 
a
[
i
]
a[i] and 
a
[
i
−
1
]
a[i−1] where, 
a
[
i
]
>
a
[
i
−
1
]
a[i]>a[i−1]. Thus, all numbers to the right of 
a
[
i
−
1
]
a[i−1] were already sorted in descending order. Furthermore, swapping 
a
[
i
−
1
]
a[i−1] and 
a
[
j
]
a[j] didn't change that order. Therefore, we simply need to reverse the numbers following 
a
[
i
−
1
]
a[i−1] to get the next smallest lexicographic permutation.
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
