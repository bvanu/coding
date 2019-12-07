/*
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
Example 1:
Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 <= k <= array's length.
 * 
 * import java.util.Comparator;

public class MyComparator implements Comparator<Integer>
{
    public int compare( Integer x, Integer y )
    {
        return y - x;
    }
}
So, you can create a min-heap and max-heap in the following way:
PriorityQueue minHeap=new PriorityQueue();
PriorityQueue maxHeap=new PriorityQueue(size, new MyComparator());
 */

package amzn2;

import java.util.PriorityQueue;

public class KthLargestElemenet {
	public int findKthLargest(int[] nums, int k) {
		if(nums==null || nums.length<1 || k>nums.length)
			return 0;

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		//PriorityQueue<Integer> minHeap = new PriorityQueue<>(a,b -> a-b);
		/*PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(1,
				new Comparator<Integer>(){
			public int compare(Integer o1, Integer o2)
			{
				return o1-o2;
			}
		});*/

		for(int n: nums)
        	{
            		minHeap.add(n);
            
            		if(minHeap.size()>k)
                		minHeap.poll();
        	}

		return minHeap.poll();
	}
}
