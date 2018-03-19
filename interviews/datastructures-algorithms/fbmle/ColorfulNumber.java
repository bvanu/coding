package fbmle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class ColorfulNumber {
	public static int isColorful(int A) {
	    HashSet<Integer> hashSet = new HashSet<>();
	    ArrayList<Integer> numbers = new ArrayList<>();
	    
	    while (A != 0) {
	        int num = A % 10;
	        numbers.add(num);
	        A /= 10;
	    }
	    
	    Collections.reverse(numbers);   
	    int n = numbers.size();
	    
	    for (int i = 0; i < n; i++) {
	        for (int j = i; j < n; j++) {
	            int prod = 1;
	            for (int k = i; k <= j; k++) {
	                prod *= numbers.get(k);
	            }
	            
	            if (hashSet.contains(prod))
	                return 0;
	                
	            hashSet.add(prod);
	        }
	    }
	    
	    System.out.println(hashSet);
	    
	    return 1;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Is 3245 colorful number?" + isColorful(3245) + " \n");
		System.out.println("Is 3462 colorful number?" + isColorful(3462));
	}
}
