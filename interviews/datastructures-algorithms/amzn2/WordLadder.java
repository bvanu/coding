/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

Approach is BFS
Output: O(26nm) ~ O(nm)
n - number of words in the dictionary
m - length of the word
 */

package amzn2;

import java.util.*;

public class WordLadder {
	public static int wordLadder(String beginWord, String endWord, List<String> wordList)
	{
		Set<String> dict = new HashSet<String>(wordList);
		
		if(!dict.contains(endWord))
			return 0;
		
		int step = 0;
		List<String> wordLadder = new ArrayList<String>();
		Queue<String> qu = new LinkedList<String>();
		qu.offer(beginWord);
		dict.remove(beginWord);
		
		while(!qu.isEmpty())
		{
			int size = qu.size();
			
			for(int i=0; i<size; i++)
			{
				String currWord = qu.poll();
				
				if(currWord.equals(endWord))
				{
					return ++step;
				}
				
				findNext(currWord, dict, qu);
			}
			step++;
		}
				
		return 0;
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
		System.out.println(wordLadder("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
	}
}
