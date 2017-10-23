/*
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 *
 * algorithms
 * Medium (32.46%)
 * Total Accepted:    93.9K
 * Total Submissions: 289.2K
 * Testcase Example:  '[]\n[]'
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      int m = inorder.length;
        return helper(m-1,0,m-1,inorder, postorder);
    }
    //util function
    private TreeNode helper(int postEnd, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (postEnd < 0 || inStart > inEnd) {
            return null;
        }

        int root = postorder[postEnd];
        int divider = -1;

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root) {
                divider = i;
                break;
            }
        }

        TreeNode ret = new TreeNode(root);
        int rightLength = inEnd - divider;
        ret.left = helper(postEnd-1 - rightLength,inStart,divider-1,inorder, postorder);
        ret.right = helper(postEnd-1,divider+1,inEnd,inorder,postorder);

        return ret;


    }
}
