/*
 * [404] Sum of Left Leaves
 *
 * https://leetcode.com/problems/sum-of-left-leaves
 *
 * algorithms
 * Easy (47.03%)
 * Total Accepted:    63.6K
 * Total Submissions: 135.3K
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
        if (root == null) {
        	return 0;
        }
        int sum = 0;
        if (root.left != null) {
        	if (isLeaf(root.left)) {
        		sum = root.left.val;
        	} else {
        		sum += sumOfLeftLeaves(root.left);
        	}
        }
        if (root.right != null) {
        	sum += sumOfLeftLeaves(root.right);
        }
        return sum;
    }
    private boolean isLeaf(TreeNode root) {
    	if (root == null) {
    		return false;
    	}
    	return (root.left == null && root.right  == null);
    }
}
