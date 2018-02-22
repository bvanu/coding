/*
 * Swap every other element in a linked list. e.g. a->b->c->d becomes b->a->d->c 
 * 
 * Source: 
 * https://www.youtube.com/watch?v=jiLloHVmLDc
 * https://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list-by-changing-links/
 * 
 * TC: O(n)  
 */
package amazonsde2;

class Node
{
	char value;
	Node next;

	public Node(char value)
	{
		this.value = value;
		this.next = null;
	}
	
	public Node(char value, Node next)
	{
		this.value = value;
		this.next = next;
	}
}

public class SwapAlternateNodes {
	
	/* This program swaps the nodes of linked list rather than swapping the field from the nodes.
	 * Imagine a case where a node contains many fields, there will be plenty of unnecessary swap calls.
	*/
	public Node swapAlternateNodes(Node head)
	{
		 // If linked list is empty or there is only one node in list
        if (head == null || head.next == null) {
            return null;
        }
 
        // Initialize previous and current pointers
        Node prevNode = head;
		Node currNode = head.next;
 
        head = currNode;  // Change head before proceeding
 
        // Traverse the list
        while (true) {
        	Node nextNode = currNode.next;
			currNode.next = prevNode; // Change next of current as previous node
 
            // If next NULL or next is the last node
			if(nextNode == null || nextNode.next == null)
			{
				prevNode.next = nextNode;
				break;
			}
 
            // Change next of previous to next next
            prevNode.next = nextNode.next;
 
            // Update previous and curr
            prevNode = nextNode;
            currNode = prevNode.next;
        }
        
        return head;
	}
	
	
	// swapping just the data of the nodes without changing the links
	public Node swapAlternateNodesData(Node head)
	{
		if(head==null)
			return head;
		
		Node currNode = head;
		
		while(currNode!=null && currNode.next!=null)
		{
			char temp = currNode.value;
			currNode.value= currNode.next.value;
			currNode.next.value = temp;
			
			if(currNode.next!=null)
			{
				currNode = currNode.next.next;
			}
		}
		
		return head;
	}
	
	public void printLinkedList(Node node)
	{		
		while(node!=null)
		{
			System.out.print(node.value + "\t");
			node = node.next;
		}
		
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		SwapAlternateNodes san = new SwapAlternateNodes();
		
		Node nodec = new Node('c', null);
		Node noded = new Node('d', nodec);
		Node nodea = new Node('a', noded);
		Node head = new Node('b', nodea);
		
		Node newHead = san.swapAlternateNodesData(head);
		san.printLinkedList(newHead);
		
		Node newHead2= san.swapAlternateNodes(head);
		san.printLinkedList(newHead2);
	}
}
