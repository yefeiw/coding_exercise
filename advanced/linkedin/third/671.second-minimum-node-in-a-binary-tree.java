/*
 * [671] Second Minimum Node In a Binary Tree
 *
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree
 *
 * algorithms
 * Easy (41.78%)
 * Total Accepted:    12.7K
 * Total Submissions: 30.3K
 * Testcase Example:  '[2,2,5,null,null,5,7]'
 *
 *
 * Given a non-empty special binary tree consisting of nodes with the
 * non-negative value, where each node in this tree has exactly two or zero
 * sub-node. If the node has two sub-nodes, then this node's value is the
 * smaller value among its two sub-nodes.
 *
 *
 *
 * Given such a binary tree, you need to output the second minimum value in the
 * set made of all the nodes' value in the whole tree.
 *
 *
 *
 * If no such second minimum value exists, output -1 instead.
 *
 *
 * Example 1:
 *
 * Input:
 * ⁠   2
 * ⁠  / \
 * ⁠ 2   5
 * ⁠    / \
 * ⁠   5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 *
 *
 * Example 2:
 *
 * Input:
 * ⁠   2
 * ⁠  / \
 * ⁠ 2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest
 * value.
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
    public int findSecondMinimumValue(TreeNode root) {
        int[] mins = new int[2];
        Arrays.fill(mins,Integer.MAX_VALUE);
        helper(root, mins);
        if (mins[1] < Integer.MAX_VALUE) {
            return mins[1];
        } else {
            return -1;
        }
    }

    //util functions
    private void helper(TreeNode root, int[] mins) {
        if (root == null) {
            return;
        }
        int cand = root.val;
        if (cand < mins[0]) {
            mins[1] = mins[0];
            mins[0] = cand;
        } else if (cand != mins[0] && cand < mins[1]) {
            mins[1] = cand;
        }
        helper(root.left,mins);
        helper(root.right,mins);
    }
}
