package amzn2;

public class Atoi {
	public static int convertStringToInteger(String str)
	{
		if(str==null)
			return 0;
		
		// remove white spaces
		str = str.trim();
		if(str.length()<1)
            return 0;
		
		// check sign
		int start = 0;
		int sign = 1;
		if(str.charAt(start) == '+')
		{
			sign = 1;
			start++;
		}
		else if(str.charAt(start) == '-')
		{
			sign =-1;
			start++;
		}
		
		// get number
		long sum = 0;
		for(int i=start; i<str.length(); i++)
		{
			int val = str.charAt(i)-'0';
			if(val>=0 && val<=9)
			{
				sum = sum*10 + val;
				
				if(sign*sum<Integer.MIN_VALUE)
					return Integer.MIN_VALUE ;
				if(sign*sum>Integer.MAX_VALUE)
					return Integer.MAX_VALUE ;
			}
			else
				break;
		}
		
		return (int)sum*sign;
	}
	
	public static void main(String[] args0) {
		System.out.println(convertStringToInteger("   +49 words"));
		System.out.println(convertStringToInteger("-749 words"));
		System.out.println(convertStringToInteger("rat 749 words"));
		System.out.println(convertStringToInteger("-91283472332"));
		System.out.println(convertStringToInteger("   "));
		System.out.println(convertStringToInteger(""));
	}
}
