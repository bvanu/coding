/*
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered a whitespace character.
Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, 231 − 1 or −231 is returned.
 

Example 1:

Input: str = "42"
Output: 42
Example 2:

Input: str = "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: str = "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: str = "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: str = "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN (−231) is returned.
 

Constraints:

0 <= s.length <= 200
s consists of English letters (lower-case and upper-case), digits, ' ', '+', '-' and '.'.
*/

package amzn2;

public class Atoi {
	public static int convertStringToInteger(String str)
	{
		if(str==null)
			return 0;
		
		// remove white spaces
		str = str.trim();
		if(str.length()<1)
            return 0;
		
		// check sign
		int start = 0;
		int sign = 1;
		if(str.charAt(start) == '+')
		{
			sign = 1;
			start++;
		}
		else if(str.charAt(start) == '-')
		{
			sign =-1;
			start++;
		}
		
		// get number
		long sum = 0;
		for(int i=start; i<str.length(); i++)
		{
			int val = str.charAt(i)-'0';
			if(val>=0 && val<=9)
			{
				sum = sum*10 + val;
				
				if(sign*sum<Integer.MIN_VALUE)
					return Integer.MIN_VALUE ;
				if(sign*sum>Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
			}
			else
				break;
		}
		
		return (int)sum*sign;
	}
	
	public static void main(String[] args0) {
		System.out.println(convertStringToInteger("   +49 words"));
		System.out.println(convertStringToInteger("-749 words"));
		System.out.println(convertStringToInteger("rat 749 words"));
		System.out.println(convertStringToInteger("-91283472332"));
		System.out.println(convertStringToInteger("   "));
		System.out.println(convertStringToInteger(""));
	}
}
