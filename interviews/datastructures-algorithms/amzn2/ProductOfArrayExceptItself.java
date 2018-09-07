/*
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).
Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
package amzn2;

public class ProductOfArrayExceptItself {
	// TC: O(n) SC: O(1)
	public int[] productExceptSelf(int[] nums) {
        if(nums==null || nums.length<1)
            return null;
        
        int n = nums.length;
        int[] prod = new int[n];
        
        // 1. compute left prod, temp store left product so far excluding the current element
        for(int i=0, temp=1 ; i<n; i++)
        {
            prod[i] = temp;
            temp *= nums[i];
        }
        
        // 2. compute right prod and multiply with left prod
        for(int i=n-1, temp =1; i>=0; i--)
        {
            prod[i] *= temp;
            temp *= nums[i];
        }
        
        return prod;
    }
}
