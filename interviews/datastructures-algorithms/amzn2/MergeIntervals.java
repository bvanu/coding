/*
 * Given a collection of intervals, merge all overlapping intervals.
Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

TC: O(nlogn)
SC: O(1)
 */
package amzn2;

import java.util.*;

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
	private class CompareIntervals implements Comparator<Interval>
    {
        public int compare(Interval o1, Interval o2)
        {
            return o1.start<o2.start ? -1 :o1.start>o2.start ? 1:0;
        }
            
    }
	
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new CompareIntervals());              
      
        List<Interval> mergedIntervals = new ArrayList<Interval>();
        
        for(Interval interval: intervals)
        {
            int n = mergedIntervals.size();
            
            if(mergedIntervals.isEmpty() || mergedIntervals.get(n-1).end<interval.start)
                mergedIntervals.add(interval);
            else
            	mergedIntervals.get(n-1).end = Math.max(mergedIntervals.get(n-1).end, interval.end);
        }
        
        /*
         LinkedList<Interval> mergedIntervals = new LinkedList<Interval>();
        
        for(Interval interval: intervals)
        {
            if(mergedIntervals.isEmpty() || mergedIntervals.getLast().end<interval.start)
                mergedIntervals.add(interval);
            else
            	mergedIntervals.getLast().end = Math.max(mergedIntervals.getLast().end, interval.end);
        }
        */
        
        return mergedIntervals;
    }    
}
