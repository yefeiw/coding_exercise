/*
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (42.07%)
 * Total Accepted:    167.1K
 * Total Submissions: 397.2K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 * 
 * Output: ["1->2->5", "1->3"]
 * 
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    public List<String> binaryTreePaths(TreeNode root) {
       if(root == null) {
           return Collections.emptyList();
       } 
       List<String> ret = new ArrayList<>();
       helper(root, ret, "");
       return ret;
    }

    private void helper(TreeNode root, List<String> ret, String curString) {
        if (root == null) {
            return;
        } 
        curString += (curString.isEmpty()) ? root.val : ("->"+root.val);
        if (root.left == null && root.right == null) {
            ret.add(curString);
            return;
        }
        helper(root.left, ret, curString);
        helper(root.right, ret, curString);
    }
}
