/*
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:
Input: "cbbd"
Output: "bb"

TC: O(n^2)
SC: O(1)
 */
package amzn2;

public class LongestPalindromicSubstring {
	int lo;
    int maxLength;
        
    public LongestPalindromicSubstring()
    {
         lo = 0;
         maxLength = 0;
    }
    
    public String longestPalindrome(String s) {
        if(s==null ||s.length()<1)
            return s;
        
        for(int i=0; i<s.length(); i++)
        {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1);
        }
        
        return s.substring(lo, lo+maxLength);
    }
    
    public void extendPalindrome(String s, int start, int end)
    {
        while(start>=0 && end<s.length() && s.charAt(start)==s.charAt(end))
        {
            start--;
            end++;
        }
        
        if(maxLength<=end-start-1)
        {
            lo = start+1;
            maxLength = end-start-1;
        }
            
    }
}
