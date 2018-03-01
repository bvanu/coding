/*
 * Write class for trie and searching in a trie  
 * Applications:
 * Auto complete, spell checker, solving word games
 * 
 * Assumptions:
 * Maximum of R links to its children, where each link corresponds to one of character values from dataset alphabet. 
 * In this article we assume that R is 26, the number of lower case latin letters
 * 
 * Source: https://leetcode.com/articles/implement-trie-prefix-tree/
 * 
 * Insert key:
 * TC: O(m)
 * SC: O(m)
 * 
 * Search word:
 * TC: O(m)
 * SC: O(1)
 * 
 * Starts with:
 * TC: O(m)
 * SC: O(1)
 */

package amzn2;

class TrieNode
{
	// R links to node children
	private TrieNode[] children;
	private final int R = 26;
	
	// is this word end
	boolean isEnd;
	
	public TrieNode()
	{
		children = new TrieNode[R];
	}
	
	public TrieNode getTrieNode(char ch)
	{
		return children[ch-'a'];
	}
	
	public void putTrieNode(char ch, TrieNode node)
	{
		children[ch-'a'] = node;
	}
	
	public void setEnd()
	{
		isEnd = true;
	}
	
	public boolean getEnd()
	{
		return isEnd;
	}
	
	public boolean containsKey(char ch)
	{
		return children[ch-'a'] != null;
	}
}


public class Trie {
	private TrieNode root;
	
	public Trie()
	{
		root = new TrieNode();
	}
	
	// insert a word in a trie
	public void insert(String word)
	{
		TrieNode currNode = root;
		
		for(int i=0; i<word.length(); i++)
		{
			char letter = word.charAt(i);
			
			// if letter is not there in trie, so insert the letter in trie
			if(!currNode.containsKey(letter))
			{
				currNode.putTrieNode(letter, new TrieNode());
			}
				
			currNode = currNode.getTrieNode(letter);
		}
		
		// setEnd to true as this is the end of the word
		currNode.setEnd();
	}
	
	// search a prefix or whole key in trie and returns the node where search ends
	public TrieNode searchPrefix(String word)
	{
		TrieNode currNode = root;
		
		for(int i=0; i<word.length(); i++)
		{
			char letter = word.charAt(i);
			
			if(currNode.containsKey(letter))
				currNode = currNode.getTrieNode(letter);
			else
				return null;
		}
		
		return currNode;
	}
	
    // returns if the word is in the trie.
	public boolean searchWord(String word)
	{
		TrieNode node = searchPrefix(word);
		
		return node != null && node.isEnd;
	}
	
	// returns if there is any word in the trie that starts with the given prefix.
	public boolean startsWith(String prefix)
	{
		TrieNode node = searchPrefix(prefix);
		
		return node != null ;
	}	
	
	
	public static void main(String[] args)
	{
		Trie te = new Trie();
		
		te.insert("trie");
		te.insert("search");
		
		System.out.println(te.searchWord("tri")); // false
		System.out.println(te.startsWith("tri")); // true
		System.out.println(te.searchWord("trie")); // true
		System.out.println(te.startsWith("trie")); // true
	}
}