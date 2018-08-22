/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put. 
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item. 
Follow up:
Could you do both operations in O(1) time complexity?
Example: 
LRUCache cache = new LRUCache( 2  capacity  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Using DLL, as remove can be done in O(1)
*/

package amzn2;

import java.util.*;

class DLLNode
{
	int key; // this is needed to reference the node in the map
	int value;
	
	DLLNode next;
	DLLNode prev;
	
	public DLLNode(int key, int value)
	{
		this.key = key;
		this.value = value;
	}
}
public class LRUCache {
	Map<Integer, DLLNode> map = new HashMap<Integer, DLLNode>();
	DLLNode head;
	DLLNode end;
	int capacity;
	
	public LRUCache(int capacity)
	{
		this.capacity = capacity;
		head = null;
		end = null;
	}

	// TC:O(1)
	public int get(int key)
	{
		System.out.print(map.keySet() + " \t");
		if (map.containsKey(key))
		{
			DLLNode curNode = map.get(key);
			
			remove(curNode);
			setHead(curNode);
			
			return curNode.value;
		}	
		
		return -1;
	}
	
	// TC:O(1)
	public void set(int key, int value)
	{
		// check if the key already exists in LRUCache, if so move to the front of the list
		if(map.containsKey(key))
		{
			DLLNode oldNode = map.get(key);
			oldNode.value = value;
			remove(oldNode);
			setHead(oldNode);
		}
		else
		{
			DLLNode newNode = new DLLNode(key, value);
			
			// if the cache capacity exceed, remove the least recently used node from DLL and map and insert the new node
			if(map.size()>=capacity)			
			{				
				map.remove(end.key);
				
				remove(end);
				//setHead(newNode);
			}
			
			setHead(newNode);
			map.put(key, newNode);
		}
		
		System.out.println(map.keySet());
	}
	
	public void remove(DLLNode n)
	{
		// if not head
		if(n.prev!=null)
		{
			n.prev.next = n.next;
		}
		else
		{
			head = n.next;
		}
		
		// if not end
		if(n.next!=null)
		{
			n.next.prev = n.prev;
		}
		else
		{
			end = n.prev;
		}
	}
	
	public void setHead(DLLNode n)
	{
		n.next = head;
		n.prev = null;
		
		// if head exists
		if(head!=null)
		{
			head.prev = n;
		}
		head = n;
		
		// if no end, set end as head
		if(end==null)
		{
			end = n; // or head
		}
	}	
	
	public static void main(String[] args)
	{
		/*
		 * Input:
		 * ["LRUCache","put","put","get","put","get","put","get","get","get"]
						[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
						
		 * My answer:
		 * [null,null,null,1,null,2,null,-1,3,4]
		 * 
		 * Expected answer:
		 * [null,null,null,1,null,-1,null,-1,3,4]
		 */
		
		LRUCache lru = new LRUCache(2);
		/*
		lru.set(1,1);
		lru.set(2,2);
		System.out.println(lru.get(1));
		lru.set(3, 3);;
		System.out.println(lru.get(2));
		lru.set(4,4);
		System.out.println(lru.get(1));
		System.out.println(lru.get(3));
		System.out.println(lru.get(4));	
		*/
		
		//["LRUCache","put","put","get","put","put","get"]
		//[[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
		// Output: [null,null,null,2,null,null,-1]
		lru.set(2,1);
		lru.set(2,2);
		System.out.println(lru.get(2));
		lru.set(1,1);
		lru.set(4,1);
		System.out.println(lru.get(2));
		
	}
}
