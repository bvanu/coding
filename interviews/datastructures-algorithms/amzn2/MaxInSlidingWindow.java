package amzn2;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.*;

public class MaxInSlidingWindow {
	public static int[] maxSlidingWindow(int[] nums, int k) {
		if(nums==null || nums.length<1)
			return new int[0];
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, 
				new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2)
			{
				return o2-o1;
			}
		});
		
		int n = nums.length;
		List<Integer> maxElements = new ArrayList<Integer>();
		
		for(int i=0; i<n; i++)
        {
            if(i<k)
            {
                maxHeap.add(nums[i]);
                //System.out.println("maxHeap: " + maxHeap);
            }
            else
            {
            	System.out.println("Before: maxHeap: " + maxHeap);
                int max = maxHeap.peek();
                maxElements.add(max);
                
                System.out.println("After removing: maxHeap: " + maxHeap);

                maxHeap.remove(nums[i-k]);
                maxHeap.add(nums[i]);
                
            }  
        }
		maxElements.add(maxHeap.peek());
		System.out.println(maxElements.size());
		int[] res = new int[maxElements.size()]; int i=0;
		for(int val: maxElements)
			res[i++] = val; 
		
		return res;
	}
	
	public static void main(String[] args)
	{
		int[] nums = {}; //{1,3,-1,-3,5,3,6,7};
		System.out.println(nums.length);
		int k =3;
		
		System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
	}
}
