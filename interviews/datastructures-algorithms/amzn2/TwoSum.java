package amzn2;

import java.util.*;

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
        if(nums==null || nums.length<2)
            return new int[2];
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        int n= nums.length;
        
        for(int i=0; i<n; i++)
            map.put(nums[i], i);
        
        for(int i=0; i<n; i++)
        {
            int val = target-nums[i];
            
            if(map.containsKey(val) && map.get(val)!=i)
            {
                res[0] = i;
                res[1] = map.get(val);
                return res;
            }
        }
        
        return res;
    }
}
