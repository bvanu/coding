package Zillow;

/*
 * Write a function that takes a string and returns its length without using built in properties (such as .length or .size()).
 */

public class StringLength {
	public static int findLength(String str)
	{
		if(str==null)
			return 0;
		/*
		int i=0;
		
		try
		{
			while(true)
			{
				str.charAt(i);
				i++;
			}
				
		}
		catch(IndexOutOfBoundsException e)
		{
			return i;
		}*/
		return 1;
	}
	
	public static void main(String[] args)
	{
		System.out.println(findLength("abcdef"));
	}
}
