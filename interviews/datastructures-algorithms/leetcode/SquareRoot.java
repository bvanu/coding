/*
 * Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
Example 1:
Input: 4
Output: 2
Example 2:
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
             
Similar idea, a little cleaner implementation. I used the trick m=(l+r+1)/2, something I learned from others on Leetcode,
basically to tilt the mid calculations towards to the right (instead of to the left from default c++ integer division flooring).

TC: O(logn) Binary Search approach
SC: O(1)
 */
package leetcode;

public class SquareRoot {
	public static int mySqrt(int x) {
		int l=0,r=x;
		while (l<r) {
			int m=(l+r+1)/2;
			//System.out.println(m);
			if (m>x/m) r=m-1;
			else l=m;
		}
		return r;
	}
	
	public boolean isPerfectSquare(int num) {
        int x = mySqrt(num);
        
        if(x*x == num)
            return true;
        else
            return false;
    }

	public static void main(String[] args)
	{
		System.out.println(mySqrt(2));
		System.out.println(mySqrt(8));
		System.out.println(mySqrt(9));	
		System.out.println(mySqrt(Integer.MAX_VALUE));	 // as I don't do m*m it will not overflow
	}
}
