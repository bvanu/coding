/*
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * 
 *      _______14______
 *     /               \
 *  ___5__           ___9__
 * /      \         /      \
 * 4       1       3        6
 * 
 * Output: [[14], [5, 9], [4, 1, 3, 6]]
 * 
 * TC: O(n) have to touch each element
 */
package amzn2;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class QueueNode
{
	TreeNode node;
	int level;
	
	public QueueNode(TreeNode node, int level)
	{
		this.node = node;
		this.level = level;
	}
}

public class LevelOrderBinaryTree {
	public List<List<Integer>> levelOrder(TreeNode root)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Queue<TreeNode> que = new LinkedList<TreeNode>();
				
		que.add(root);
		
		while(!que.isEmpty())
		{
			int count = que.size();
			List<Integer> subList = new ArrayList<Integer>();
			
			for(int i=0; i < count; i++)
			{
				TreeNode currNode = que.poll();
				subList.add(currNode.value);
				
				if(currNode.left != null)
					que.add(currNode.left);
				if(currNode.right != null)
					que.add(currNode.right);
			}
			
			result.add(subList);
		}
		
		return result;
	}
	
	public static void main(String[] args)
	{
		LevelOrderBinaryTree lobt = new LevelOrderBinaryTree();

		TreeNode four = new TreeNode(4, null, null);
		TreeNode one = new TreeNode(1, null, null);
		TreeNode three = new TreeNode(3, null, null);
		TreeNode six = new TreeNode(6, null, null);
		TreeNode five = new TreeNode(5, four, one);
		TreeNode nine = new TreeNode(9, three, six);
		TreeNode root = new TreeNode(14, five, nine);

		System.out.println(lobt.levelOrder(root));
	}
}


