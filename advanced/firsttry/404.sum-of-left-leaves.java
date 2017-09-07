/*
 * [404] Sum of Left Leaves
 *
 * https://leetcode.com/problems/sum-of-left-leaves
 *
 * algorithms
 * Easy (46.94%)
 * Total Accepted:    60.8K
 * Total Submissions: 129.4K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * There are two left leaves in the binary tree, with values 9 and 15
 * respectively. Return 24.
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
    	int ans = 0;
    	if (root == null || isLeaf(root)) {
    		return ans;
    	}
    	if (root.left != null) {
	        if (isLeaf(root.left)) {
	        	ans += root.left.val;
	        } else {
	        	ans += sumOfLeftLeaves(root.left);
	        }
    	}
    	if (root.right != null) {
    		ans += sumOfLeftLeaves(root.right);
    	}
    	return ans;
    }
    private boolean isLeaf(TreeNode node) {
    	if (node == null) return false;
    	return (node.left == null && node.right == null);
    }
}
