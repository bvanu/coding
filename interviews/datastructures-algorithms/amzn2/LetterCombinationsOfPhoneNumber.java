/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

TC: O(n^n) n = number of digits
SC: O(m) m= number of unique combinations for every recursive calls
 */
package amzn2;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {
String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        
        if(digits==null || digits.length()<1)
        	return res;
                
        StringBuilder sb = new StringBuilder();
        
        helper(digits, res, sb, 0);
        
        return res;    	
    }
    
    public void helper(String digits, List<String> res, StringBuilder sb, int index)
    {
        // base case
    	if(index==digits.length())
    	{
    		res.add(new String(sb));
    		return;
    	}
    	
        // recursive rule
    	String letters = mapping[digits.charAt(index)-'0']; // (int) gives the ascii value
    	
    	for(int i=0; i<letters.length(); i++)
    	{
    		sb.append(letters.charAt(i));
    		helper(digits, res, sb, index+1);
    		sb.deleteCharAt(sb.length()-1);
    	}
    }
}
