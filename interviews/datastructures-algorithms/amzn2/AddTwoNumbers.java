/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
	You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	Example:
	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
	Explanation: 342 + 465 = 807.
 * 
 * T: O(n)
 * S: O(1)
 */
package amzn2;

import amzn2.ListNode;

/*
class ListNode
{
	int val;
	ListNode next;

	public ListNode(int val)
	{
		this.val = val;
	}
}
 */

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p1 = l1;
		ListNode p2 = l2;
		int carry = 0;
		ListNode cur = dummyHead;
		
		while (p1 != null || p2 != null) {
		    int n1 = p1 != null ? p1.val : 0;
		    int n2 = p2 != null ? p2.val : 0;
		    int sum = n1 + n2 + carry;
		    carry = sum / 10;
		    cur.next = new ListNode(sum % 10);
		    cur = cur.next;
		    if (p1 != null) p1 = p1.next;
		    if (p2 != null) p2 = p2.next;
		}
		if (carry > 0) cur.next = new ListNode(carry);
		return dummyHead.next;
	}

	private void printLinkedList(ListNode head)
	{
		while(head!=null)
		{
			System.out.println(head.val);
			head = head.next;
		}
	}
}
