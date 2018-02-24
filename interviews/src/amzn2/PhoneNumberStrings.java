/* 
 * Given the dialer of a feature phone and a dictionary of words, find the string suggestions after dialing n numbers.
 * TC: O(3^n)
 */

package amzn2;

import java.util.*;

public class PhoneNumberStrings {
	Map<Integer, String> map = new HashMap<Integer, String>();
	Set<String> dictionary = new HashSet<String>();

	public PhoneNumberStrings()
	{
		// create number to letter mappings
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");

		// create a valid words dictionary
		dictionary.add("cup"); //287
		dictionary.add("rat"); //728
		dictionary.add("sat");
	}

	public List<String> getLetterCombinations(String phoneNumber)
	{
		if(phoneNumber==null || phoneNumber.length()<1)
			return new ArrayList<String>(); // empty list

		List<String> result = new ArrayList<String>();
		List<String> prevResult = new ArrayList<String>();
		result.add("");

		for(int i=0; i<phoneNumber.length(); i++)
		{			
			int digit = Character.getNumericValue(phoneNumber.charAt(i));
			if(map.containsKey(digit))
			{
				String letters = map.get(digit);
				for(String str: result)
				{
					for(int j=0; j<letters.length(); j++)
					{
						prevResult.add(str + letters.charAt(j));
					}
				}
			}
			else
			{
				continue;
			}

			result = prevResult;
			prevResult = new ArrayList<String>();
		}

		List<String> validWords = new ArrayList<String>();
		for(String str: result)
		{
			if(dictionary.contains(str))
				validWords.add(str);
		}

		return validWords;
	}

	public static void main(String[] args)
	{
		PhoneNumberStrings pns = new PhoneNumberStrings();
		System.out.println(pns.getLetterCombinations("7280"));
	}
}
