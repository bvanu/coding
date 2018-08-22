/*
 * You're now a baseball game point recorder. 
Given a list of strings, each string can be one of the 4 following types: 
Integer (one round's score): Directly represents the number of points you get in this round.
"+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
"D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
"C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.

Each round's operation is permanent and could have an impact on the round before and the round after. 
You need to return the sum of the points you could get in all the rounds. 
Example 1:
Input: ["5","2","C","D","+"]
Output: 30
Explanation: 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get 2 points. The sum is: 7.
Operation 1: The round 2's data was invalid. The sum is: 5.  
Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
Round 4: You could get 5 + 10 = 15 points. The sum is: 30.

Example 2:
Input: ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation: 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get -2 points. The sum is: 3.
Round 3: You could get 4 points. The sum is: 7.
Operation 1: The round 3's data is invalid. The sum is: 3.  
Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
Round 5: You could get 9 points. The sum is: 8.
Round 6: You could get -4 + 9 = 5 points. The sum is 13.
Round 7: You could get 9 + 5 = 14 points. The sum is 27.

Note:
The size of the input list will be between 1 and 1000.
Every integer represented in the list will be between -30000 and 30000.

TC: O(n)
SC: O(1)
 */

package amzn2;

import java.util.*;

public class BaseballGame {
	
	public static int calPoints(String[] ops)
	{
		if(ops==null ||ops.length<1)
			return 0;
		
		Stack<Integer> points = new Stack<Integer>();
		int totalPoints = 0;
		
		for(String op: ops)
		{
			if(op=="C" && !points.isEmpty())
			{
				System.out.println("C:" + op);
				int removePoint = points.pop();
				totalPoints -= removePoint;
			}
			else if(op=="+" && !points.isEmpty())
			{
				System.out.println("+:" + op);
				int prevPoint = points.pop();				
				
				if(!points.isEmpty())
				{
					int prevPrevPoint = points.peek();
					
					points.push(prevPoint);
					points.push(prevPoint+prevPrevPoint);
					
					totalPoints += points.peek();
				}				
			}
			else if(op=="D" && !points.isEmpty())
			{
				System.out.println("D:" + op);
				points.push(2*points.peek());
				totalPoints += points.peek();
			}
			else
			{ // problem with this approach is we assume that the operations passed are valid and only one of + C D or Integer will be passed
				System.out.println("else:" + op);				
				points.push(Integer.parseInt(op));
				totalPoints += points.peek();
			}
		}
		
		return totalPoints;
	}
	
	public static void main(String[] args)
	{
		String[] ops = {"C","5","-2","4","C","D","9","+","+"};
		
		System.out.println(calPoints(ops));
	}
	
	/*
	// TC: O(n)
	// SC: O(n)
	public int calPoints(String[] ops)
	{
		if(ops==null ||ops.length<1)
			return 0;

		int[] roundPoints = new int[ops.length];
		int totalPoints = 0;
		
		for(int i=0; i<ops.length; i++)
		{
			char op = ops[i].toCharArray()[0];

			if(op>=48 && op<=57)
			{
				roundPoints[i] = Integer.parseInt(String.valueOf(op));
				totalPoints += roundPoints[i];
			}
			else if(op == '+')
			{
				int count = 0;
				int j = i-1;

				while(count<2 && j>=0)
				{
					if(roundPoints[j] != 0)
					{
						roundPoints[i] += roundPoints[j];
						count++;
					}
					j--;			
				}

				totalPoints += roundPoints[i];
			}
			else if(op == 'C')
			{
				totalPoints -= roundPoints[i-1];
				roundPoints[i-1] = 0;
				roundPoints[i] = 0;

			}
			else if(op == 'D')
			{	
				int j=0;
				
				while(roundPoints[j] == 0)
				{
					j--;
				}
				
				roundPoints[i] = 2*roundPoints[j];
				totalPoints += roundPoints[i];
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		
		return totalPoints;
	}
	*/
}
