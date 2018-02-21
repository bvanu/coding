/*
 * Verify if the Tree validates the definition.
 * parent_node.data = left_node.data + right_node.data
 * 
 * Assumptions:
 * 1. Binary tree
 * 
 * TC: O(nlogn)
 */

package amazonsde2;

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

public class ValidateTree {
	// returns node value itself if the validation succeeds else returns -1
	public int validate(TreeNode node)
	{
		// leaf node, as it is a binary tree if sibling exists there will be both right and left
		if(node.left == null && node.right==null )
			return node.value;

		int leftNode = validate(node.left);
		int rightNode = validate(node.right);

		if(leftNode == -1 || rightNode ==-1)
			return -1;

		// validate tree
		if(node.value == (node.left.value + node.right.value))
			return node.value;

		return -1;
	}

	public static void main(String[] args)
	{
		ValidateTree vt = new ValidateTree();

		TreeNode four = new TreeNode(4, null, null);
		TreeNode one = new TreeNode(1, null, null);
		TreeNode three = new TreeNode(3, null, null);
		TreeNode six = new TreeNode(6, null, null);
		TreeNode five = new TreeNode(5, four, one);
		TreeNode nine = new TreeNode(9, three, six);
		TreeNode root = new TreeNode(14, five, nine);

		if(vt.validate(root)!=-1)
			System.out.println("Valid tree");
		else
			System.out.println("Invalid tree");
	}
}
