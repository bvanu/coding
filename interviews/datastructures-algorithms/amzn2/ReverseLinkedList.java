/*
 * TC: O(n)
 * SC:O(1)
 */
package amzn2;

public class ReverseLinkedList {
	public ListNode reverseList_(ListNode head) {
        if(head==null || head.next==null)
            return head;
        
        ListNode prev = head, temp;
        ListNode curr = prev.next;
        head.next = null;
        
        while(curr!=null)
        {
            temp = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = temp;
        }
        
        return prev;
    }
	
	public ListNode reverseList(ListNode head) {
        if(head==null)
            return null;
        
        ListNode rev = head, fwd = head, temp = null;
        
        while(fwd!=null)
        {
            fwd = fwd.next;
            rev.next = temp;
            temp = rev;
            rev = fwd;
        }
        
        return temp;
    }
}