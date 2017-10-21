/*
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths
 *
 * algorithms
 * Easy (38.87%)
 * Total Accepted:    123.4K
 * Total Submissions: 317.4K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * 
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * 
 * For example, given the following binary tree:
 * 
 * 
 * 
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 * 
 * 
 * 
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 * 
 * 
 * Credits:Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root == null) {
        	return ret;
        }
        String s = Integer.toString(root.val); 
        helper(root,s,ret);
        return ret;
    }

    //util functions
    private void helper(TreeNode root, String s, List<String> ret) {
    	if (root == null) {
    		return;
    	}
    	if(root.left == null && root.right == null) {
    		ret.add(s);
    		return;
    	}
    	if (root.left != null) {
    		helper(root.left,s+"->"+Integer.toString(root.left.val),ret);
    	}
    	if (root.right != null) {
    		helper(root.right, s+"->"+Integer.toString(root.right.val), ret);
    	}
    }
}
