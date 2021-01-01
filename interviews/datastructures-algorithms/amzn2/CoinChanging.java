/*
Question: You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that
amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Examples:

1
Input:: coins = [1, 2, 5], amount = 11
Output 3

2
Input: coins = [2], amount = 3
Output: -1


Approach 1 (Brute Force)

We can generate all sets of coin frequencies that end up summing to the total amount given the coins that we granted and then take the sequence with the least coins in it.
This is not only harder to implement, but it entails an automatic exponential amount of work to compute all the sets of coin frequencies to only take the best answer 
(the set with the least coins) from.

Approach 2 (Top Down Dynamic Programming)

Example:
Input:: coins = [1, 2, 5], amount = 11
Output 3

The answer to the subproblem for amount 11 is the same thing as the MINIMUM of the answers to the sub problems with each currency deduced from the original sub problem (11) PLUS
ONE since we are acting as if each coin we subtract from 11 is the last coin used to make change.
Not all paths will yield an answer, some will yield an optimal answer.

Complexities (Top Down)
A is the amount to make change for.
n is the total denominations avaliable to make change with.

Time: O( A * n )
For each amount we will approximately be doing n work in trying to deduct each denomination from the current sub problem
Our recursive tree will at maximum have a depth of S (worst case if each call deducts 1 at each call).
Space: O( A )
We answer and store a total of A subproblems in our dynamic programming table to get to our globally optimum answer.


Approach 3 (Bottom Up Dynamic Programming)
Example:
Input:: coins = [1, 2, 5], amount = 11
Output 3
We can also create a dynamic programming table going from the bottom up to the amount we want an answer for.


Complexities (Bottom Up)
A is the amount to make change for.
n is the total denominations avaliable to make change with.
Time: O( A * n ) For each amount we will potentially try each of the denominations (there are n of them).
Space: O( A ) We answer and store a total of A subproblems in our dynamic programming table to get to our globally optimum answer.
 
 * Source: https://www.youtube.com/watch?v=jgiZlGzXMBw
 */

/*
The Bottom Up Approach
This code passes all Leetcode test cases as of Jan. 10 2019
Runtime: 31 ms
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins==null || coins.length<1)
            return 0;
        
         // We use this to fill the dp table with default values
        int max = amount+1;
        
         // This table will store the answer to our sub problems
        int[] numCoins = new int[amount+1];
        
        // 1. Initialize the dp table
        Arrays.fill(numCoins, max);
        
        /*
        The answer to making change with minimum coins for 0
        will always be 0 coins no matter what the coins we are
        given are
        */
        numCoins[0] = 0;
        
        // 2. Solve every subproblem from 1 to amount
        for(int i=1; i<numCoins.length; i++) // is i the currentSum
        {   
            // For each coin we are given
            for(int j=0; j<coins.length; j++)
            {
                // If it is less than or equal to the sub problem amount
                if(i-coins[j]>=0)
                {
                    // Try it. See if it gives us a more optimal solution
                    numCoins[i] = Math.min(numCoins[i], 1+numCoins[i-coins[j]]); 
                }
            }
            
        }

        /*
        dp[amount] has our answer. If we do not have an answer then dp[amount]
        will be amount + 1 and hence dp[amount] > amount will be true. We then
        return -1.
        Otherwise, dp[amount] holds the answer
        */
        return numCoins[amount]>amount? -1 : numCoins[amount];
    }
}


/*
The Top Down Approach : DEPTH FIRST SEARCH
This code passes all Leetcode test cases as of Jan. 10 2019
Runtime: 53 ms
*/
public class CoinChangeTopDown {

public int coinChange(int[] coins, int amount) {    
  
  if (amount < 1) {
    return 0;
  }

  return coinChange(coins, amount, new int[amount + 1]);
}

private int coinChange(int[] coins, int remainder, int[] dp) {

  /*
    Minimum coins to make change for a negative amount is -1.
    This is just a base case we arbitrarily define.
  */
  if (remainder < 0) {
    return -1;
  }

  /*
    The minimum coins needed to make change for 0 is always 0
    coins no matter what coins we have.
  */
  if (remainder == 0) {
    return 0;
  }

  // We already have an answer cached. Return it.
  if (dp[remainder] != 0) {
    return dp[remainder];
  }

  /*
    No answer yet. Try each coin as the last coin in the change that
    we make for the amount
  */
  int minimum = Integer.MAX_VALUE;
  for (int coin : coins) {
    int changeResult = coinChange(coins, remainder - coin, dp);

    /*
      If making change was possible (changeResult >= 0) and 
      the change result beats our present minimum, add one to
      that smallest value
      
      We accept that coin as the last coin in our change making
      sequence up to this point since it minimizes the coins we
      need
    */
    if (changeResult >= 0 && changeResult < minimum) {
      minimum = 1 + changeResult;
    }
  }

  /*
    If no answer is found (minimum == Integer.MAX_VALUE) then the
    sub problem answer is just arbitrarily made to be -1, otherwise
    the sub problem's answer is "minimum"
  */
  dp[remainder] = (minimum == Integer.MAX_VALUE) ? -1 : minimum;

  // Return the sub problem's answer
  return dp[remainder];
}

}
