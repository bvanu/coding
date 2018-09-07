package amzn2;

import java.util.*;

public class ValidParanthesis {
	Map<Character, Character> dict;
    
    public ValidParanthesis()
    {
        dict = new HashMap<Character, Character>();
        
        dict.put('(', ')');
        dict.put('{', '}');
        dict.put('[', ']');
    }
    
    public boolean isValid(String s) {
    	if(s==null || s.length()<1)
            return true;
        
        Stack<Character> stk = new Stack<Character>();
        
        for(int i=0; i<s.length(); i++)
        {
            char bracket = s.charAt(i);
            
            if(dict.containsKey(bracket))
            {
                stk.push(bracket);
            }
            else
            {
                if(dict.values().contains(bracket)) // O(n) ?
                {
                    if(stk.isEmpty() || (!stk.isEmpty() && bracket!=dict.get(stk.pop())))
                        return false;
                }
            }             
        }
        
        return stk.isEmpty();
    }
}
