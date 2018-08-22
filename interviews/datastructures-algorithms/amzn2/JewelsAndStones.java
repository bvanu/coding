/*
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
Example 1:
Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:
Input: J = "z", S = "ZZ"
Output: 0
Note:
S and J will consist of letters and have length at most 50.
The characters in J are distinct.

TC: O(n)
SC: O(1)
O(1) means the space is constant, i.e. it doesn't grow with the size of data on which the algorithm is operating.
This is technically (if the problem never scales) correct here, but often O(1) means we are operating "in-place" or only using a small constant additional space
He has actually made a new array, and if our constraints for the problem (currently at both strings max 50) were to grow, his boolean array would have to grow as well. Think about using ArrayList instead of fixed array.... does the boolean list have to grow the longer the strings are? if it does, (yes it does) it is not O(1) space. What if we only had ONE variable called count and we used += to store the times we saw a union between J and S? THAT is O(1) for sure... count is a small constant space. THAT is a better way to understand O(1) .
PS this answer is verbose.
 */
package amzn2;

public class JewelsAndStones {
	public int numJewelsInStones(String J, String S)
	{
		boolean[] arr = new boolean[128]; // number of ascii characters are 128
		int count = 0;
		
		// Mark all the jewels
		for(int i=0; i<J.length(); i++)
		{
			arr[J.charAt(i)] = true;
		}
		
		// Count number of Jewels in Stones
		for(int i=0; i<S.length(); i++){
			if(arr[S.charAt(i)])
				count++;
		}
		
		return count;
	}
	
	public static void main(String[] args)
	{		
		JewelsAndStones jas = new JewelsAndStones();
		
		String J = "aA";
		String S = "aAAbbbb";
		
		System.out.println(jas.numJewelsInStones(J, S));
	}
}
