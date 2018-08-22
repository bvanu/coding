/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

TC: O(mn) m# lenght of s, n #words in the dictionary
SC: O(1)


For al substrings of the words the loop can run for #words in the dictionary
 */

package amzn2;

import java.util.Arrays;
import java.util.List;
import java.util.*;

public class WordBreak {	
	Set<String> map = new HashSet<String>();
	
	public boolean wordBreak(String s, List<String> wordDict) {
        if(wordDict.contains(s))
            return true;
        
        if(map.contains(s)) // can't break words
            return false;
        
        for(String word: wordDict)
        {
            if(s.startsWith(word))
            {
            	// System.out.println(s + " " + word);
                if(wordBreak(s.substring(word.length()), wordDict))
                    return true;
            }
        }
        
        map.add(s);
        
        return false;
    }
	
	/* This doesn't work for words like "catsand", dict: "cats", "cat", "and"
	 * as it breaks into cat and sand
	public static boolean wordBreak(String str, List<String> wordDict)
	{
		if(str==null || str.length()<1)
			return false;
		
		int start = 0;
		int maxLengthSoFar = 0;
		String result = "";
		
		for(int i=0; i<str.length(); i++)
		{
			String currWord = str.substring(start, i+1);
			
			if(wordDict.contains(currWord))
			{
				result += currWord + " ";
				start = i+1;
				maxLengthSoFar = i+1;
			}		
		}
		
		System.out.println(result);
		
		if(maxLengthSoFar == str.length())
			return true;
		
		return false;
	} */
	
	public static void main(String[] args)
	{
		WordBreak wb = new WordBreak();
		
		System.out.println(wb.wordBreak("catsandog", Arrays.asList("cats", "cat", "sand", "and", "dog", "an"))); // true
		System.out.println(wb.wordBreak("leetcode", Arrays.asList("leet", "code"))); // true
		System.out.println(wb.wordBreak("catsandog", Arrays.asList("cat", "sand", "og"))); // true
		System.out.println(wb.wordBreak("catsandog", Arrays.asList("cats", "cat", "sand", "and", "dog"))); // false
	}
}
