package amzn2;

class ListNode
{
	int val;
	ListNode  next;
	
	ListNode(int val)
	{
		this.val = val;
	}
}

public class MergeKSortedLists {
	public static ListNode mergeKLists(ListNode[] lists)
	{  
		if(lists==null || lists.length<1)
			return null;
		
		return mergeKLists(lists, 0, lists.length-1);
		
	}
	
	public static ListNode mergeKLists(ListNode[] lists, int start, int end)
	{
		if(start<end)
		{
			int mid = (start+end)/2;
			
			return merge(mergeKLists(lists, start, mid), mergeKLists(lists, mid+1, end));
		}
		
		return lists[start];
	}
	
	public static ListNode merge(ListNode node1, ListNode node2)
	{
		ListNode head = new ListNode(0); // so that you don't need a flag
		ListNode p = head;
		
		while(node1!=null && node2!=null)
		{
			if(node1.val<node2.val)
			{
				p.next = node1;
				p = p.next;
				node1 = node1.next;
			}
			else
			{
				p.next = node2;
				p = p.next;
				node2 = node2.next;
			}		
		}
		
		if(node1!=null)
        {
			p.next = node1;
            return head.next;
        }
		if(node2!=null)
        {
			p.next = node2;
            return head.next;
        }
		
		return head.next;
	}
	
	
	public static void main(String[] ags)
	{
		ListNode[] input = new ListNode[3];
	}
}
