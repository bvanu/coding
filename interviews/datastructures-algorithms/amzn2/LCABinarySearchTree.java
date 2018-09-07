/*
 * Find the least common ancestor of GIVEN two nodes in a tree 
 * This is NOT a BINARY SEARCH TREE)
 * 
 * TC: O(logn)
 */

package amzn2;

import amzn2.TreeNode;

/*
class TreeNode
{
	int value;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value, TreeNode left, TreeNode right)
	{
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
 */

public class LCABinarySearchTree {
	public TreeNode findLCA(TreeNode root, TreeNode node1, TreeNode node2)
	{
		if(root == null || root.value == node1.value || root.value == node2.value )
			return root;

		if(node1.value < root.value && root.value < node2.value)
			return root;

		if(node2.value < root.value)
			return findLCA(root.left, node1, node2);

		return findLCA(root.right, node1, node2);
	}

	/* If if we are not sure the two given nodes are in BST or not, first verify if the nodes exist
	 * Also, verify if node1.val < node2.val, if not swap for the conditions to be valid 
	 */
	public TreeNode checkIfnodesExist(TreeNode root, int val1, int val2)
	{
		if(root==null)
			return null;

		if(val1>val2)
		{
			// swap values
			int temp = val1;
			val1 = val2;
			val2 = temp;
		}

		TreeNode node1 = findNode(root, val1);

		if(node1!=null)
		{
			TreeNode node2 = findNode(root, val2);

			if(node2 != null)
			{
				return findLCA(root, node1, node2);
			}
		}

		return null;
	}

	// search in BST O(logn)
	public TreeNode findNode(TreeNode root, int val)
	{
		if(root == null)
			return null;

		if(root.value == val)
			return root;

		if(val < root.value)
			return findNode(root.left, val);
		else
			return findNode(root.right, val);
	}

	public static void main(String[] args)
	{
		LCABinarySearchTree lca = new LCABinarySearchTree();

		TreeNode one = new TreeNode(1, null, null);
		TreeNode three = new TreeNode(3, null, null);
		TreeNode root = new TreeNode(2, one, three);

		TreeNode lcaNode = lca.findLCA(root, one, root);
		System.out.println(lcaNode.value);

		TreeNode lcaNode2 = lca.checkIfnodesExist(root, 4, 1);
		if(lcaNode2!=null)
			System.out.println(lcaNode2.value);
		else
			System.out.println(lcaNode2);

	}
}
