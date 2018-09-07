/*
 * Pick One

Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1. 
Examples: 
s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

Note: You may assume the string contain only lowercase letters. 
 */
package amzn2;

import java.util.*;

public class FirstUniqueCharInString {
	// TC: O(n)
	// SC: O(1) uses constant space irrespective of input size
	 public int firstUniqChar(String s) {
	        if(s==null || s.length()<1)
	            return -1;
	        
	        int[] alphabet = new int[26];
	        int[] loc = new int[26];
	        
	        for(int i=0; i<s.length(); i++)
	        {
	            char c = s.charAt(i);
	            
	            alphabet[c-'a'] += 1;
	            loc[c-'a'] = i; // this will be overriden by the last char
	        }
	        
	        for(int i=0; i<s.length(); i++)
	        {
	            char ch = s.charAt(i);
	            
	            if(alphabet[ch-'a']==1)
	                return i;
	        }
	        
	        return -1;
	        
	        /*int res = -1;
	        for(int i=0; i<alphabet.length; i++)
	        {
	            if(alphabet[i]==1)
	            {
	                res = res==-1? loc[i] : Math.min(res, loc[i]);
	            }
	        }
	          
	        return res;*/
	    }
	
	// TC: O(n)
	// SC: O(n)
	public int firstUniqChar_(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for(int i=0; i<s.length(); i++)
        {
            map.put(s.charAt(i), 1+map.getOrDefault(s.charAt(i), 0));
        }
        
        for(int i=0; i<s.length(); i++)
        {
            if(map.get(s.charAt(i))==1)
                return i;
        }        
        
        return -1;
    }
}
