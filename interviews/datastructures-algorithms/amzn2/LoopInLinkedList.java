/*
 * Given a linked list, determine if it has a cycle in it. 
	Follow up:
	Can you solve it without using extra space? 

	TC: O(n)
	SC: O(1)
 */
package amzn2;

public class LoopInLinkedList {
	public boolean hasCycle(ListNode head) {
        if(head==null)
            return false;
        
        ListNode slow = head, fast = head;
        
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow==fast)
            {
            	// to find where the cycle/loop starts
            	fast = head;
            	
            	while(fast!=slow)
            	{
            		fast  = fast.next;
            		slow = slow.next;
            	}
            	
            	System.out.println(slow.val);
                return true;
            }
        }
        
        return false;
    }
}
