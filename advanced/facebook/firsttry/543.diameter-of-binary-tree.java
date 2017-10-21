/*
 * [543] Diameter of Binary Tree
 *
 * https://leetcode.com/problems/diameter-of-binary-tree
 *
 * algorithms
 * Easy (43.75%)
 * Total Accepted:    25.6K
 * Total Submissions: 58.5K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path
 * between any two nodes in a tree. This path may or may not pass through the
 * root.
 * 
 * 
 * 
 * Example:
 * Given a binary tree 
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \     
 * ⁠     4   5    
 * 
 * 
 * 
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * 
 * Note:
 * The length of path between two nodes is represented by the number of edges
 * between them.
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
	private int res = 0;
	class Stat {
		public int diameter;
		public int longestLeg;
		public Stat(int diameter, int longestLeg) {
			this.diameter = diameter;
			this.longestLeg = longestLeg;
		}
	}
    public int diameterOfBinaryTree(TreeNode root) {
        Stat stat = new Stat(0,0);
        helper(root, stat);
        //BUGWARNING: it is clear that the diameter may or may not be from root.
        return res;
    }
    private void helper(TreeNode root, Stat stat) {
    	if (root == null) {
    		stat.longestLeg = -1;
    		return;
    	}
    	Stat left = new Stat(0,0);
    	Stat right = new Stat(0,0);
    	helper(root.left, left);
    	helper(root.right,right);
    	stat.diameter = left.longestLeg + right.longestLeg +2;
    	if (stat.diameter > res) {
    		res = stat.diameter;
    	}
    	stat.longestLeg = Math.max(left.longestLeg, right.longestLeg) + 1;
    }
}
