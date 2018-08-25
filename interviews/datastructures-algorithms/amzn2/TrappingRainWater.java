/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6

Solution:
Intuition As in Approach 2, instead of computing the left and right parts seperately, we may think of some way to do it in one iteration. From the figure in dynamic programming approach, notice that as long as 
right_max[i]>left_max[i]
right_max[i]>left_max[i]
(from element 0 to 6), the water trapped depends upon the left_max, and similar is the case when 
left_max[i]>right_max[i]
left_max[i]>right_max[i]
(from element 8 to 11). So, we can say that if there is a larger bar at one end (say right), we are assured that the water trapped would be dependant on height of bar in current direction (from left to right). As soon as we find the bar at other end (right) is smaller, we start iterating in opposite direction (from right to left). We must maintain 
left_max
left_max
and 
right_max
right_max
during the iteration, but now we can do it in one iteration using 2 pointers, switching between the two.
https://leetcode.com/problems/trapping-rain-water/solution/#

 * TC: O(n)
 * SC: O(1)
 */
package amzn2;

import java.util.*;

public class TrappingRainWater {
	public int trap(int[] height) {
		int left = 0, right = height.length-1;
		int left_max=0, right_max = 0;
		int ans = 0;

		while(left<right)
		{
			if(height[left]<height[right])
			{
				if(height[left] >= left_max)
					left_max=height[left];
				else
					ans+=left_max-height[left];
				left++;
			}
			else
			{
				if(height[right]>=right_max)
					right_max=height[right];
				else
					ans+=right_max-height[right];
				right--;
			}
		}
		return ans;
	}

	/*public int trap(int[] height)
	{
		if(height==null || height.length<1)
			return 0;

		int x, y, k, rainWaterTrapped = 0;
		List<Integer> inBetween;

		for(int i=0; i<height.length;)
		{
			inBetween = new ArrayList<Integer>();

			// 1. find x
			while(i<height.length && height[i]==0)
			{
				i++;
			}
			if(i>=height.length)
				break;
			x = i;
			i++;

			// 2.find y and inBetween
			k = i;
			while(k<height.length && height[k]<x)
			{				
				inBetween.add(height[k]);
				k++;
			}
			if(k>=height.length && i<height.length)
			{
				i++;
				break;
			}
			y = i;

			System.out.println("trappedWater: " + rainWaterTrapped);
			// 3. find water trapped
			if(height[y]>=height[x])
			{
				for(int j=x+1; j<y; j++)
				{
					System.out.println("height[x]: " + height[x] + " height[j]: " + height[j]);
					rainWaterTrapped += height[x]-height[j];
				}
			}
			System.out.println("x: " + x + " y: " + y + " trappedWater: " + rainWaterTrapped);
		}

		return rainWaterTrapped;
	}*/

	public static void main(String[] args)
	{
		TrappingRainWater tr = new TrappingRainWater();

		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(tr.trap(height));
	}
}
