/*
 * Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

Example: 
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */

package amzn2;

import java.util.*;

public class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> nums;
    java.util.Random rand = new java.util.Random();
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        nums = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val))
        {
            map.put(val, nums.size());
            nums.add(val);
            System.out.println("INSERT: " + nums);
            return true;
        }
        System.out.println("INSERT: " + nums);
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
    	System.out.println("BEFORE REMOVE: " + nums);
        if(map.containsKey(val))
        {
            int loc = map.get(val);
            int n = nums.size();
            
            if(loc<n-1) // if not last one
            {
                int lastOne = nums.get(n-1);
                nums.set(loc, lastOne);
                map.put(lastOne, loc); // Change the index of the lastOne, as it got changed in the arrayList
            }
            
            map.remove(val);
            nums.remove(n-1);
            System.out.println("REMOVE: " + nums);
            return true;
        }
        System.out.println("REMOVE: " + nums);
        return false;
    }    
    
    /** Get a random element from the set. */
    public int getRandom() {
    	//System.out.println("GET RANDOM: " + nums);
        //int randIndex = (int) (Math.random() * (nums.size()-1));
        //return nums.get(randIndex); not working
        
        return nums.get( rand.nextInt(nums.size()) );
    }
    
    public static void main(String[] args)
	{		
    	RandomizedSet obj = new RandomizedSet();
    	/*
    	 * ["RandomizedSet","insert","insert","remove","insert","remove","getRandom"]
						[[],[0],[1],[0],[2],[1],[]]
    	 */
    	
    	/*System.out.println(obj.insert(0));
    	System.out.println(obj.insert(1));
    	System.out.println(obj.remove(0));
    	System.out.println(obj.insert(2));
    	System.out.println(obj.remove(1));
    	System.out.println(obj.getRandom());*/
    	
    	/*
    	 * ["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
							[[],[1],[2],[2],[],[1],[2],[]]
    	 * [null,true,false,true,2,true,false,2]
    	 */
    	System.out.println(obj.insert(1));
    	System.out.println(obj.remove(2));
    	System.out.println(obj.insert(2));
    	System.out.println(obj.getRandom());
    	System.out.println(obj.remove(1));
    	System.out.println(obj.insert(2));
    	System.out.println(obj.getRandom());
    	
	}
}