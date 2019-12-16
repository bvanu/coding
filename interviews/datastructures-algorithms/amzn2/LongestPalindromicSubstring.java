/*
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:
Input: "cbbd"
Output: "bb"

Approach 4: Expand Around Center

In fact, we could solve it in O(n^2) time using only constant space.

We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are 
only 2n−1 such centers.

You might be asking why there are 2n−1 but not n centers? The reason is the center of a palindrome can be in between two 
letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.

Complexity Analysis
Time complexity : O(n^2). Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2).
Space complexity : O(1). 

 */
package amzn2;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        
        if(s==null || n<=1)
            return s;
        
        int start = 0, end = 0;
        
        for(int i=0; i<n; i++)
        { 
            int len1 = extendPalindrome(s,i, i);
            int len2 = extendPalindrome(s, i, i+1);
            
            int len = Math.max(len1, len2);
            
            if(len>end-start+1)
            {
                start = i-(len-1)/2;
                end = i+len/2;
            }
        }
        
        return s.substring(start, end+1);
    }
    
    private int extendPalindrome(String s, int left, int right)
    {
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right))
        {
            left--;
            right++;
        }
        
        return right-left-1; // (right-1) + (left+1) -1
    }
}
