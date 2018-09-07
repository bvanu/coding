package amzn2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnagramSort implements Comparator<String>{

	// TC: O(nlogm) m = length of word, n = number of words
	// SC: O(n)
	public List<List<String>> groupAnagrams(String[] strs) 
	{
		if(strs==null || strs.length<1)
			return new ArrayList<List<String>>();

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		for(String str: strs)
		{
			char[] ca = str.toCharArray();
			Arrays.sort(ca);
			String keyString = new String(ca);

			if(!map.containsKey(keyString))
			{
				map.put(keyString, new ArrayList<String>());
			}
			map.get(keyString).add(str);
		}

		return new ArrayList<List<String>>(map.values());
	}	

	public String sortString(String str)
	{
		if(str==null || str.length()<1)
			return null;

		char[] charArray = str.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}

	public int compare(String s1, String s2)
	{
		return sortString(s1).compareTo(sortString(s2));
	}


	public static void main(String[] args)
	{

		String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
		Arrays.sort(input, new AnagramSort());
		System.out.println(Arrays.toString(input));

		AnagramSort as = new AnagramSort();
		System.out.println(as.groupAnagrams(input));
	}
}
