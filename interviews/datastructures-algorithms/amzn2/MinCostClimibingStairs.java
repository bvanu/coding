/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed). 
Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1. 
Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

TC: O(n)
SC: O(1)

A bit greedy and DP solution
 */

package amzn2;

public class MinCostClimibingStairs {
	public static int minCostClimbingStairs(int[] cost) {
        if(cost==null || cost.length<1)
            return 0;
        
        int n = cost.length;
        
        for(int i=2; i<n; i++)
        {
            cost[i] += Math.min(cost[i-1], cost[i-2]);
        }
        
        return Math.min(cost[n-1], cost[n-2]);
    }
	
	public static void main(String[] args)
	{
		int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		
		System.out.println(minCostClimbingStairs(cost));
	}
}
