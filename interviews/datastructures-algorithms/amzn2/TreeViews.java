/*
 * (C1) Given a binary tree, print all the leaf nodes.   
   (C2) Now, print all the left most nodes, and all right most nodes ( assume there is a triangle around the binary tree, so all nodes which falls on that triangle, print them in clockwise ordering).
   C1: Leaf nodes: Preorder traversal limited to leaves.
   C2: Left side: Preorder traversal limited to nodes which matches certain criteria.
   Right side: Only visit those nodes that meet certain criteria - Post Order traversal.
   C3: Level order traversal
   C4: Zig zag traversal

   				1
   		/				\
   		2				3
   	/		\		/		\
   	4		5		6		7	
   /  		 \	  	 	   /	
  8			 9			10	

  C1: TC: O(n), SC: O(1)
  C2: TC: O(n), SC: O(1)

  Output:
  Leaf nodes of the tree: 8	9	6	10	
Right tree view: 1	3	7	10	
Left tree view: 1	2	4	8	
Level order traversal of the tree:
[1]
[2, 3]
[4, 5, 6, 7]
[8, 9, 10]
 */

package amzn2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeViews {
	static int maxLevelSeenSoFar;

	/*
	 * With pre-order traversal
	 * TC: O(n)
	 * SC: O(1)
	 */
	public static void printLeafNodes(TreeNode root)
	{
		if(root==null)
			return;

		if(root.left==null && root.right==null)
			System.out.print(root.value + "\t");

		if(root.left!=null)
			printLeafNodes(root.left);
		if(root.right!=null)
			printLeafNodes(root.right);
	}

	/*
	 * With pre-order traversal
	 * TC: O(n)
	 * SC: O(1)
	 */
	public static void printRightTreeView(TreeNode root, int currentLevel)
	{
		if(root==null)
			return;

		if(currentLevel > maxLevelSeenSoFar)
		{
			System.out.print(root.value + "\t");
			maxLevelSeenSoFar++;
		}

		if(root.right!=null)
			printRightTreeView(root.right, currentLevel+1);
		if(root.left!=null)
			printRightTreeView(root.left, currentLevel+1);
	}

	/*
	 * With pre-order traversal
	 * TC: O(n)
	 * SC: O(1)
	 */
	public static void printLeftTreeView(TreeNode root, int currentLevel)
	{
		if(root==null)
			return;

		if(currentLevel > maxLevelSeenSoFar)
		{
			System.out.print(root.value + "\t");
			maxLevelSeenSoFar++;
		}

		if(root.left!=null)
			printLeftTreeView(root.left, currentLevel+1);
		if(root.right!=null)
			printLeftTreeView(root.right, currentLevel+1);		
	}

	/*
	 * TC: O(n)
	 * SC: O(k) for queue
	 */
	public static List<List<Integer>> levelOrderTraversal(TreeNode root)
	{
		if(root==null)
			return Collections.emptyList(); // new ArrayList<List<Integer>>();

		List<List<Integer>> levels = new ArrayList<List<Integer>>(); // = new LinkedList<>();

		Queue<TreeNode> nodes = new LinkedList<>();
		nodes.add(root);

		while(!nodes.isEmpty())
		{
			List<Integer> level = new ArrayList<>(nodes.size());
			levels.add(level);

			for(TreeNode node: new ArrayList<>(nodes)) // new ArrayList<>
			{
				level.add(node.value);

				if(node.left!=null)
					nodes.add(node.left);
				if(node.right!=null)
					nodes.add(node.right);

				nodes.poll();
			}
		}

		printListOfLists(levels);
		return levels;
	}


	public static List<List<Integer>> zigZagTraversal(TreeNode root)
	{
		if(root==null)
			return Collections.emptyList(); // new ArrayList<List<Integer>>();

		List<List<Integer>> levels = new ArrayList<List<Integer>>(); // = new LinkedList<>();
		boolean isLeftToRight = true;
		Queue<TreeNode> nodes = new LinkedList<>();
		nodes.add(root);

		while(!nodes.isEmpty())
		{
			List<Integer> level = new ArrayList<>(nodes.size());
			levels.add(level);

			for(TreeNode node: new ArrayList<>(nodes)) // new ArrayList<>
			{
				level.add(node.value);

				if(isLeftToRight)
				{
					if(node.left!=null)
						nodes.add(node.left);
					if(node.right!=null)
						nodes.add(node.right);
				}
				else
				{
					if(node.right!=null)
						nodes.add(node.right);
					if(node.left!=null)
						nodes.add(node.left);					
				}
				nodes.poll();
			}
			isLeftToRight = !isLeftToRight;
		}

		printListOfLists(levels);
		return levels;
	}

	public static void printListOfLists(List<List<Integer>> lists)
	{
		if(lists == null)
			return;

		for(List<Integer> list: lists)
		{
			System.out.println(Arrays.toString(list.toArray()));
		}
	}

	public static void printQueue(Queue<TreeNode> que)
	{
		System.out.println("Printng queue:");
		for(TreeNode i: que)
		{
			System.out.print(i.value + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		TreeNode eight = new TreeNode(8, null, null);
		TreeNode nine = new TreeNode(9, null, null);
		TreeNode ten = new TreeNode(10, null, null);
		TreeNode six = new TreeNode(6, null, null);
		TreeNode seven = new TreeNode(7, ten, null);
		TreeNode four = new TreeNode(4, eight, null);
		TreeNode five = new TreeNode(5, null, nine);
		TreeNode two = new TreeNode(2, four, five);
		TreeNode three = new TreeNode(3, six, seven);
		TreeNode root = new TreeNode(1, two, three);

		System.out.print("Leaf nodes of the tree: ");
		printLeafNodes(root);

		System.out.print("\nRight tree view: ");
		maxLevelSeenSoFar = 0;
		printRightTreeView(root, 1);

		System.out.print("\nLeft tree view: ");
		maxLevelSeenSoFar = 0;
		printLeftTreeView(root, 1);

		System.out.println("\nLevel order traversal of the tree:");		
		levelOrderTraversal(root);
		
		System.out.println("\nZig zag traversal of the tree:");	
		zigZagTraversal(root);

	}
}
