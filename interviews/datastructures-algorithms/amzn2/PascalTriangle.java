package amzn2;

/*
 * Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5, the result should be:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

TC: O(n^2)
SC: O(1)
 */

import java.util.*;

public class PascalTriangle {
	public static List<List<Integer>> pascalTriangle(int rowIndex)
	{
		List<List<Integer>> res = new LinkedList<>();
		
		if(rowIndex<=0)
            return res;
				
		List<Integer> curr = null, prev = new ArrayList<Integer>();
		
		// first row
		prev.add(1);
		res.add(prev);
		
		for(int i=1; i<rowIndex; i++)
		{
			curr = new ArrayList<Integer>();
			curr.add(1); // first
			
			for(int j=1; j<i; j++)
			{
				curr.add(prev.get(j) + prev.get(j-1)); // middle
			}
			curr.add(1); // last
			res.add(curr);
			prev = curr;
		}
		
		return res;
	}
	
	public static void main(String[] args)
	{
		List<List<Integer>> res = pascalTriangle(5);
		
		for(int i=0; i<res.size(); i++)
		{
			System.out.println(res.get(i));
		}
	}
}
