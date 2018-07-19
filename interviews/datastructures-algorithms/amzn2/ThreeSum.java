/*Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note:
The solution set must not contain duplicate triplets.
 */

package amzn2;

import java.util.*;

public class ThreeSum {

	public static List<List<Integer>> threeSum(int[] num) {
		if(num==null || num.length<1)
			return null;
		
		Arrays.sort(num);
		List<List<Integer>> res = new LinkedList<>(); 
		for (int i = 0; i < num.length-2; i++) {
			if (i == 0 || (i > 0 && num[i] != num[i-1])) {
				int lo = i+1, hi = num.length-1, sum = 0 - num[i];
				while (lo < hi) {
					if (num[lo] + num[hi] == sum) {
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
						while (lo < hi && num[lo] == num[lo+1]) lo++;
						while (lo < hi && num[hi] == num[hi-1]) hi--;
						lo++; hi--;
					} else if (num[lo] + num[hi] < sum) lo++;
					else hi--;
				}
			}
		}
		return res;
	}

	/* this method doens't account for duplicates 
	 * OUTPUT: [[-1, 0, 1], [-1, 1, 0], [-1, 2, -1], [-1, -1, 2], [0, 1, -1], [0, -1, 1], [1, -1, 0]]
	public static List<List<Integer>> findThreeSum(int[] nums)
	{
		if(nums==null || nums.length<1)
			return null;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // value, index
		List<List<Integer>> res= new ArrayList<List<Integer>>();

		// store nums in map
		for(int i=0; i<nums.length; i++)
			map.put(nums[i],  i);

		// search for -(a+b) in map
		for(int i=0; i<nums.length; i++)
		{
			for(int j=i+1; j<nums.length-1; j++)
			{
				int val = -(nums[i] + nums[j]);

				if(map.containsKey(val) && map.get(val)!=i && map.get(val)!=j)
				{
					List<Integer> subRes = new ArrayList<Integer>();
					subRes.add(nums[i]);
					subRes.add(nums[j]);
					subRes.add(val);

					res.add(subRes);
				}
			}
		}			
		return res;
	}
	*/

	public static void main(String[] args)
	{
		List<List<Integer>> res = threeSum(new int[] {-1, 0, 1, 2, -1, -4});
		System.out.println(res);
		
		List<List<Integer>> res1 = threeSum(new int[] {});
		System.out.println(res1);
		
		List<List<Integer>> res2 = threeSum(null);
		System.out.println(res2);
	}
}
