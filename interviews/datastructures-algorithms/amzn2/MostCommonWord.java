/*
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.

TC: O(n)
SC: O(n)
 */
package amzn2;

import java.util.*;

public class MostCommonWord {
	public String mostCommonWord(String paragraph, String[] banned) {
        if(paragraph==null || paragraph.length()<1)
            return null;
        
        Set<String> bannedDict = new HashSet<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for(String word: banned)
        {
            bannedDict.add(word);   
        }
        
        String[] words = paragraph.replaceAll("[!?',;.]", "").toLowerCase().split(" ");

        int resCount = 0;
        String resString = "";
        
        for(String word: words)
        {
            if(!bannedDict.contains(word))
            {
                map.put(word, map.getOrDefault(word,0)+1);
                
                if(resCount<map.get(word))
                {
                    resCount = map.get(word);
                    resString = word;
                }
            }
        }
        
        return resString;
    }
	
	public static void main(String[] args)
	{		
		MostCommonWord mcw = new MostCommonWord();
		
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = {"hit"};
		System.out.println(mcw.mostCommonWord(paragraph, banned));
	}
}
