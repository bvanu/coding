/*
	Given a singly linked list, determine if it is a palindrome.
	Example 1:
	Input: 1->2
	Output: false
	Example 2:
	Input: 1->2->2->1
	Output: true
	Follow up:
	Could you do it in O(n) time and O(1) space? 
	
	Approach:
	Reversed first half == Second half?
 */

package amzn2;

public class PalindromeLinkedList {
	// 1. Mess up with the linked list
	public boolean isPalindrome(Node head)
	{
		Node rev = null;
		Node slow = head, fast = head;
		
		// 1. find the mid of the linked list
		while(fast!=null && fast.next!=null)
		{
			Node temp = rev;
			rev = slow;
			slow = slow.next;
			fast = fast.next.next;
			rev.next = temp;
		}
		
		// 2. find if the length of the linked list is odd or even, if odd move slow by one step
		if(fast!=null) // odd length
			slow = slow.next;
		
		// 3. check if palindrome or not
		while(slow!=null)
		{
			if(rev.value!=slow.value)
				return false;
			
			slow = slow.next;
			rev = rev.next;
		}
		
		return true;  
	}
	
	// 2. Recover the linked list while comparing values
	public boolean isPalindrome_(Node head)
	{
		Node rev = null;
		Node slow = head, fast = head;
		
		// 1. find the mid of the linked list
		while(fast!=null && fast.next!=null)
		{
			Node temp = rev;
			rev = slow;
			slow = slow.next;
			fast = fast.next.next;
			rev.next = temp;
		}
		
		// 2. find if the length of the linked list is odd or even, if odd move slow by one step
		Node tail = fast!=null ? slow.next : slow;
		
		// 3. check if palindrome or not
		while(rev!=null)
		{
			if(rev.value!=tail.value)
				return false;
			
			Node temp = slow;
			slow = rev;
			rev = rev.next;
			tail = tail.next;
			slow.next = temp;
		}
		
		return true;  
	}	
}
