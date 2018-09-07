/*
 	Note: This is a companion problem to the System Design problem: Design TinyURL.
	TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
	Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

	Source: https://leetcode.com/articles/encode-and-decode-tinyurl/#
 */

package amzn2;

import java.util.*;

public class TinyUrl {
	
	/*
    Approach #2 Random fixed-length encoding
	Algorithm
	In this case, again, we make use of the set of numbers and alphabets to generate the coding for the given URLs. 
	But in this case, the length of the code is fixed to 6 only. 
	In case, the code generated collides with some previously generated code, we form a new random code.
	
	Performance Analysis
	The number of URLs that can be encoded is quite large in this case, nearly of the order (10+26∗2)^6
	The length of the encoded URLs is fixed to 6 units, which is a significant reduction for very large URLs.
	The performance of this scheme is quite good, due to a very less probability of repeated same codes generated.
	We can increase the number of encodings possible as well, by increasing the length of the encoded strings. Thus, there exists a tradeoff 
	between the length of the code and the number of encodings possible.
	Predicting the encoding isn't possible in this scheme since random numbers are used.
     */
    String alphabets = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    java.util.Random rand = new java.util.Random(); // what is the time complexity of rand ?
    String key = getRand(); 
    Map<String, String> map = new HashMap<String, String>();
    
    public String getRand()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i=0; i<6; i++)
    	{
    		sb.append(alphabets.charAt(rand.nextInt(62)));
    	}
    	
    	return sb.toString();
    }
    
    // Encodes a URL to a shortened URL.
    public String encode_(String longUrl) {
        if(longUrl==null ||longUrl.length()<1)
            return null;

        if(map.containsKey(key))
        	key = getRand();
        
        map.put(key,  longUrl);
        
        return "https://tinyurl.com" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode_(String shortUrl) {
        if(shortUrl==null ||shortUrl.length()<1)
            return null;
        
        String key = shortUrl.replaceAll("https://tinyurl.com", "");
        
        return map.get(key);
    }
	/* 
	 * 
	Approach #1 Using hashcode
	Algorithm
	In this method, we make use of an inbuilt function hashCode() to determine a code for mapping every URL.
	Again, the mapping is stored in a HashMap for decoding.
	The hash code for a String object is computed(using int arithmetic) as − 
	s[0]∗31(n−1)+s[1]∗31(n−2)+...+s[n−1]s[0]*31^{(n - 1)} + s[1]*31^{(n - 2)} + ... + s[n - 1]
	where s[i] is the ith character of the string, n is the length of the string.
	Performance Analysis
	
	The number of URLs that can be encoded is limited by the range of int, since hashCode() uses integer calculations.
	The average length of the encoded URL isn't directly related to the incoming longURL length.
	The hashCode() doesn't generate unique codes for different string. 
	This property of getting the same code for two different inputs is called collision.
	Thus, as the number of encoded URLs increases, the probability of collisions increases, which leads to failure.
	
	Thus, it isn't necessary that the collisions start occurring only after a certain number of strings have been encoded, but they could 
	occur way before the limit is even near to the int.
	This is similar to birthday paradox i.e. the probability of two people having the same birthday is nearly 50% if we consider only 23 people
	and 99.9% with just 70 people.
	Predicting the encoded URL isn't easy in this scheme.
	 */
	Map<Integer, String> urlMappings = new HashMap<Integer, String>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longUrl==null ||longUrl.length()<1)
            return null;
        
        // using hashMap might cause collision and if it happens the url will be overriden
        urlMappings.put(longUrl.hashCode(), longUrl);
        
        return "https://tinyurl.com" + longUrl.hashCode();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if(shortUrl==null ||shortUrl.length()<1)
            return null;
        
        int key = Integer.parseInt(shortUrl.replaceAll("https://tinyurl.com", ""));
        
        return urlMappings.get(key);
    }
    
    
}
