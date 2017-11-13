/*
 * [129] Sum Root to Leaf Numbers
 *
 * https://leetcode.com/problems/sum-root-to-leaf-numbers
 *
 * algorithms
 * Medium (37.11%)
 * Total Accepted:    122.7K
 * Total Submissions: 330.5K
 * Testcase Example:  '[]'
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number
 * 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 *
 *
 *
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 *
 * Return the sum = 12 + 13 = 25.
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
    public int sumNumbers(TreeNode root) {
        int ret = 0;
        if (root == null) {
            return 0;
        }
        return dfsHelper(root, 0);
    }

    //util function
    private int dfsHelper(TreeNode root, int payload) {
        if (root == null) {
            return payload;
        }
        payload = payload * 10 + root.val;
        if (isLeaf(root)) {
            return payload;
        }
        int ret = 0;
        if (root.left != null) {
            ret += dfsHelper(root.left,payload);
        }
        if (root.right != null)  {
            ret += dfsHelper(root.right,payload);
        }
        return ret;
    }


    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
