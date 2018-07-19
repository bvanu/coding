package amzn2;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramSort implements Comparator<String>{
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
		AnagramSort as = new AnagramSort();
		String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
		Arrays.sort(input, new AnagramSort());
		
		System.out.println(Arrays.toString(input));
	}
}
