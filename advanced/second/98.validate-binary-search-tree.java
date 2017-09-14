/*
 * [98] Validate Binary Search Tree
 *
 * https://leetcode.com/problems/validate-binary-search-tree
 *
 * algorithms
 * Medium (23.42%)
 * Total Accepted:    180.8K
 * Total Submissions: 771.8K
 * Testcase Example:  '[2,1,3]'
 *
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * 
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the
 * node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * Example 1:
 * 
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 
 * Binary tree [2,1,3], return true.
 * 
 * 
 * Example 2:
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * 
 * Binary tree [1,2,3], return false.
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
    public boolean isValidBST(TreeNode root) {
        int[] stat =  new int[2];
        return helper(root, stat);
    }

    //util function
    private boolean helper(TreeNode root, int[] stat) {
    	if(root == null) {
    		return true;
    	}

    	int[] leftStat = new int[2];
    	int[] rightStat = new int[2];
    	boolean left = helper(root.left, leftStat);
    	boolean right = helper(root.right,rightStat);
    	if (left == false || right == false) return false;
    	if (root.left != null && leftStat[1] >= root.val) return false;
    	if (root.right != null && rightStat[0] <= root.val) return false;
    	stat[0] = (root.left == null) ? root.val : leftStat[0];
    	stat[1] = (root.right == null) ? root.val : rightStat[1];
    	return true;
    }
}
