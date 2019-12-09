// SOLUTION 1

/*
PROBLEM:
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

  
Solution:
We are given a beginWord and an endWord. Let these two represent start node and end node of a graph. We have to reach from the start node to the end node using some intermediate nodes/words. The intermediate nodes are determined by the wordList given to us. The only condition for every step we take on this ladder of words is the current word should change by just one letter.


We will essentially be working with an undirected and unweighted graph with words as nodes and edges between words which differ by just one letter. The problem boils down to finding the shortest path from a start node to a destination node, if there exists one. Hence it can be solved using Breadth First Search approach.

One of the most important step here is to figure out how to find adjacent nodes i.e. words which differ by one letter. To efficiently find the neighboring nodes for any given word we do some pre-processing on the words of the given wordList. The pre-processing involves replacing the letter of a word by a non-alphabet say, *.


This pre-processing helps to form generic states to represent a single letter change.

For e.g. Dog ----> D*g <---- Dig

Both Dog and Dig map to the same intermediate or generic state D*g.

The preprocessing step helps us find out the generic one letter away nodes for any word of the word list and hence making it easier and quicker to get the adjacent nodes. Otherwise, for every word we will have to iterate over the entire word list and find words that differ by one letter. That would take a lot of time. This preprocessing step essentially builds the adjacency list first before beginning the breadth first search algorithm.

For eg. While doing BFS if we have to find the adjacent nodes for Dug we can first find all the generic states for Dug.

Dug => *ug
Dug => D*g
Dug => Du*
The second transformation D*g could then be mapped to Dog or Dig, since all of them share the same generic state. Having a common generic transformation means two words are connected and differ by one letter.

Approach 1: Breadth First Search

Intuition

Start from beginWord and search the endWord using BFS.

Algorithm

Do the pre-processing on the given wordList and find all the possible generic/intermediate states. Save these intermediate states in a dictionary with key as the intermediate word and value as the list of words which have the same intermediate word.

Push a tuple containing the beginWord and 1 in a queue. The 1 represents the level number of a node. We have to return the level of the endNode as that would represent the shortest sequence/distance from the beginWord.

To prevent cycles, use a visited dictionary.

While the queue has elements, get the front element of the queue. Let's call this word as current_word.

Find all the generic transformations of the current_word and find out if any of these transformations is also a transformation of other words in the word list. This is achieved by checking the all_combo_dict.

The list of words we get from all_combo_dict are all the words which have a common intermediate state with the current_word. These new set of words will be the adjacent nodes/words to current_word and hence added to the queue.

Hence, for each word in this list of intermediate words, append (word, level + 1) into the queue where level is the level for the current_word.

Eventually if you reach the desired word, its level would represent the shortest transformation sequence length.

Termination condition for standard BFS is finding the end word. 


Complexity Analysis

Time Complexity: 
O(M×N), where M is the length of words and N is the total number of words in the input word list. Finding out all the transformations takes 
M iterations for each of the N words. Also, breadth first search in the worst case might go to each of the N words.

Space Complexity: O(M×N), to store all M transformations for each of the N words, in the all_combo_dict dictionary. Visited dictionary is of 
N size. Queue for BFS in worst case would need space for all N words.
 */

import java.util.HashMap;
import java.util.*;

class Pair
{
	String key;
	int value;
	
	public Pair(String key, int value)
	{
		this.key = key;
		this.value = value;
	}
}

public class WordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    // Since all words are of same length.
    int L = beginWord.length();

    // Dictionary to hold combination of words that can be formed,
    // from any given word. By changing one letter at a time.
    HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();

    wordList.forEach(
        word -> {
          for (int i = 0; i < L; i++) {
            // Key is the generic word
            // Value is a list of words which have the same intermediate generic word.
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            ArrayList<String> transformations =
                allComboDict.getOrDefault(newWord, new ArrayList<String>());
            transformations.add(word);
            allComboDict.put(newWord, transformations);
          }
        });

    // Queue for BFS
    Queue<Pair> Q = new LinkedList<Pair>();
    Q.add(new Pair(beginWord, 1));

    // Visited to make sure we don't repeat processing same word.
    HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    visited.put(beginWord, true);

    while (!Q.isEmpty()) {
      Pair node = Q.remove();
      String word = node.key;
      int level = node.value;
      for (int i = 0; i < L; i++) {

        // Intermediate words for current word
        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

        // Next states are all the words which share the same intermediate state.
        for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
          // If at any point if we find what we are looking for
          // i.e. the end word - we can return with the answer.
          if (adjacentWord.equals(endWord)) {
            return level + 1;
          }
          // Otherwise, add it to the BFS Queue. Also mark it visited
          if (!visited.containsKey(adjacentWord)) {
            visited.put(adjacentWord, true);
            Q.add(new Pair(adjacentWord, level + 1));
          }
        }
      }
    }

    return 0;
  }
}




// SOLUTION 2

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
		List<List<String>> wordLadder = new LinkedList<>();
		Queue<String> qu = new LinkedList<String>();
		qu.offer(beginWord);
		dict.remove(beginWord);
		
		while(!qu.isEmpty())
		{
			int size = qu.size();
			List<String> words = new ArrayList<String>();
			
			for(int i=0; i<size; i++)
			{
				String currWord = qu.poll();
				words.add(currWord);
				
				if(currWord.equals(endWord))
				{
					wordLadder.add(Arrays.asList(endWord));
					System.out.println("WordLadder: " + wordLadder);
					return ++step;
				}
				
				findNext(currWord, dict, qu);
			}
			wordLadder.add(words);
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
		System.out.println(wordLadder("hit", "cog", Arrays.asList("hot","dot","lot","cog")));
	}
}
