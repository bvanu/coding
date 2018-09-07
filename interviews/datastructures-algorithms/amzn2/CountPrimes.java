/*
 * The inner loop does n/i steps, where i is prime => the whole complexity is sum(n/i) = n * sum(1/i).
 * According to prime harmonic series, the sum (1/i) where i is prime is log (log n). In total, O(n*log(log n)).
 */
package amzn2;

public class CountPrimes {
	public int countPrimes(int n) {
        if(n<0||n==1)
            return 0;
        
        // all are false by default
        boolean[] isPrime = new boolean[n];
        
        for(int i=2; i<n;i++)
            isPrime[i] = true;
        
        for(int i=2; i<n;i++) // O(n * m) factors of number
        {
            if(isPrime[i])
            {
                for(int j=2; j*i<n; j++)
                    isPrime[j*i] = false;
            }
        }
        
        int count = 0;
        for(int i=2; i<n;i++)
        {
            if(isPrime[i])
                count++;
        }
        
        return count;
    }
}
