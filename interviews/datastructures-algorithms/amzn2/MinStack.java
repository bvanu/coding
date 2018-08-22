/*
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. 
push(x) -- Push element x onto stack. 
pop() -- Removes the element on top of the stack. 
top() -- Get the top element. 
getMin() -- Retrieve the minimum element in the stack. 

Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

package amzn2;

import java.util.Stack;

public class MinStack {
	Stack<Integer> stk1;
	Stack<Integer> stk2;
	
	public MinStack()
	{
		stk1 = new Stack<Integer>();
		stk2 = new Stack<Integer>();
	}
	
	public void push(int x)
	{
		stk1.push(x);
		
		if(stk2.empty() || x<=getMin())
			stk2.push(x);
	}
	
	public int pop()
	{
		if(stk1.peek()==getMin())
			stk2.pop();
		
		return stk1.pop();
	}
	
	public int top()
	{
		return stk1.peek();
	}
	
	public int getMin()
	{
		return stk2.peek();
	}
	
	public static void main(String[] args)
	{
		/*["MinStack","push","push","push","getMin","pop","top","getMin"]
		[[],[-2],[0],[-3],[],[],[],[]]
		*/
		
		MinStack ms = new MinStack();
		
		ms.push(-2);
		ms.push(0);
		ms.push(-3);
		System.out.println(ms.getMin());
		System.out.println(ms.pop());
		System.out.println(ms.top());
		System.out.println(ms.getMin());
	}
}
