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
		if(l1==null && l2==null)
			return null;
		if(l1==null)
			return l2;
		if(l2==null)
			return l1;

		ListNode l3= new ListNode(0);
		ListNode h3 = l3;
		int sum = 0, carry = 0;

		while(l1!=null && l2!=null)
		{
			sum = l1.val + l2.val + carry;
			l1 = l1.next;
			l2 = l2.next;

			carry = sum/10;
			sum = sum%10;

			l3.next = new ListNode(sum);
			l3 = l3.next;                

			//printLinkedList(h3);
		}

		while(l1!=null)
		{
			if(carry==0)
			{
				l3.next = l1;
				return h3.next;
			}
			else
			{
				sum = l1.val + carry;
				carry = sum/10;
				sum = sum%10;

				l3.next = new ListNode(sum);
				l3 = l3.next;
				l1 = l1.next;
			}
		}

		while(l2!=null)
		{
			if(carry==0)
			{
				l3.next = l2;
				return h3.next;
			}
			else
			{
				sum = l2.val + carry;
				carry = sum/10;
				sum = sum%10;

				l3.next = new ListNode(sum);
				l3 = l3.next;
				l2 = l2.next;
			}
		}

		if(carry>0) // assumption is carry is single digigt
		{
			l3.next = new ListNode(carry);
		}

		return h3.next;
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
