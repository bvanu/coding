/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:
Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
Output: O(26nm) ~ O(nm)
n - number of words in the dictionary
m - length of the word
 */

package amzn2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLader2 {
	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> prevRes = new LinkedList<>();
		List<List<String>> res = new LinkedList<>();
		Set<String> dict = new HashSet<String>(wordList);
		
		if(!dict.contains(endWord))
			return res;
		
		Queue<String> qu = new LinkedList<String>();
		qu.offer(beginWord);
		dict.remove(beginWord);
		int k =0;
		while(!qu.isEmpty())
		{
			int size = qu.size();			
			
			for(int i=0; i<size; i++)
			{
				String currWord = qu.poll();
				//List<String> words = new ArrayList<String>();
				
				// add to the lists
				if(prevRes.size()<1)
				{					
					res.add(new ArrayList<String>(Arrays.asList(currWord)));
					System.out.println("in if: res" + res);
				}
				else
				{
					System.out.println("prevRes: " + prevRes);
					System.out.println("res: " + res);
					
					for(List<String> lis: prevRes)
					{
						System.out.println("Before: " + k + " " + lis); k++;
						System.out.println("Before res: " + res); 
						lis.add(currWord);
						System.out.println("Adding to lis: " + lis); 
						System.out.println("After res: " + res); 
						res.add(lis);
						
						System.out.println(res);
					}
				}
				
				if(currWord.equals(endWord))
				{
					return res;
				}
				
				findNext(currWord, dict, qu);
				
				prevRes = res;
			}
		}			
		
		return new LinkedList<>();
	}
	
	public static void findNext(String word, Set<String> dict, Queue<String> qu)
	{
		char[] arr = word.toCharArray();
		
		for(int i=0; i<arr.length; i++)
		{
			char item = arr[i];
			
			for(char j='a'; j<'z'; j++)
			{
				if(arr[i]!=j)
					arr[i] = j;
				
				String tempWord = new String(arr);
				
				if(dict.contains(tempWord))
				{
					qu.offer(tempWord);
					dict.remove(tempWord);
				}
			}
			// replacing the character that got changed
			arr[i] = item;
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
		System.out.println(findLadders("hit", "cog", Arrays.asList("hot","dot","lot","cog")));
	}
}
