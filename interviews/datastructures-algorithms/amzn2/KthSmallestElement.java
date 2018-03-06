/*
 * Given an unsorted array, find kth minimum element.
 * 
 * Solved using
 * 1. Arrays implementing Max heap from scratch
 * 2. Priority queues
 * 
 * TC: O(nlogk)
 * SC: O(logk)
 */
package amzn2;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestElement {
	/*
	 * Using arrays
	 */
	public int findKthSmallestElement(int[] a, int k) throws Exception
	{
		if(a==null || a.length<1 || k>a.length)
			throw new IllegalArgumentException();
		
		int n = a.length, heapSize;
		int[] maxHeap = new int[k+1];
		maxHeap[0] = 0;
		
		// build a heap of size k
		for(int i=0; i<k; i++)
		{
			maxHeap[i+1] = a[i];
		}
		buildMaxHeap(maxHeap, k+1);
		//System.out.println("initial heap: " + Arrays.toString(maxHeap));
		
		// maxHeapify in each iteration
		for(int i=k; i<n; i++)
		{
			if(maxHeap[1]>a[i])
			{
				maxHeap[1] = a[i];
				maxHeapify(maxHeap, k+1, 1);
			}
		}
		
		// return min element
		return maxHeap[1];
	}
	
	// called only once, when building the heap for the first time
	public int[] buildMaxHeap(int[] heap, int heapSize)
	{
		if(heap == null || heap.length < 1)
			return null;
		
		int lastElementIndex = heap.length-1;
		int parentIndex = lastElementIndex/2; // this gives the parent of lastElementIndex
		
		for(int i=parentIndex; i>0; i--)
		{
			maxHeapify(heap, heapSize, i);
		}
		
		return heap;
	}
	
	public int[] maxHeapify(int[] heap, int heapSize, int i)
	{
		if(heap == null || heap.length < 1)
			return null;
		
		int l = 2*i;
		int r = 2*i+1;
		int largest = i;
		
		if(l < heapSize && heap[l] > heap[largest])
			largest = l;
		if(r < heapSize && heap[r] > heap[largest])
			largest = r;
				
		if(i != largest)
		{
			int temp = heap[i];
			heap[i] = heap[largest];
			heap[largest] = temp;
			
			maxHeapify(heap, heapSize, largest);
			
		}
		
		return heap;
	}
	
	/*
	 * Using Priority queue
	 */
	public int findKthSmallestUsingPQ(int[] a, int k) throws Exception
	{
		if(a==null || a.length<1)
			throw new IllegalArgumentException();
		
		// java 8, lambda expressions
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((m,n) -> n-m);
		
		for(int i=0; i<k; i++)
			maxHeap.add(a[i]);
		
		for(int i=k; i<a.length; i++)
		{
			if(maxHeap.peek()>a[i])
			{
				maxHeap.poll();
				maxHeap.add(a[i]);
			}
		}
		
		return maxHeap.peek();
	}
	
	public static void main(String[] args) throws Exception
	{
		KthSmallestElement ke = new KthSmallestElement();
		int k;
		
		int[] test1 = {-2, -1, -6}; 
		k = 3;
		System.out.println(k + "rd smallest elemnt is " + ke.findKthSmallestElement(test1, k));
		System.out.println(k + "rd smallest elemnt is " + ke.findKthSmallestUsingPQ(test1, k));
		Arrays.sort(test1);;
		System.out.println(Arrays.toString(test1) + "\n"); // -1
		
		int[] test2 = {2, 1, 9, 8, 7, 6, 3, 5, 4};
		k = 5;
		System.out.println(k + "th smallest elemnt is " + ke.findKthSmallestElement(test2, k));
		System.out.println(k + "th smallest elemnt is " + ke.findKthSmallestUsingPQ(test2, k));
		Arrays.sort(test2);;
		System.out.println(Arrays.toString(test2) + "\n"); // 5
		
		int[] test3 = {-2, -11, 0, -9, 8, 7, -6, 3, 5, 4};
		k = 8;
		System.out.println(k + "th smallest elemnt is " + ke.findKthSmallestElement(test3, k));
		System.out.println(k + "th smallest elemnt is " + ke.findKthSmallestUsingPQ(test3, k));
		Arrays.sort(test3);;
		System.out.println(Arrays.toString(test3) + "\n"); // 5
	}
}
