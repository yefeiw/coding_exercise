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
    class TreeIterator {
        Deque<TreeNode> stack;
        public TreeIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            TreeNode iter = root;
            while(iter != null) {
                stack.push(iter);
                iter = iter.left;
            }

        }
        public boolean hasNext() {
            return (stack.size() > 0);
        }
        public TreeNode next() {
            TreeNode ret = stack.pop();
            if (ret.right != null) {
                TreeNode iter = ret.right;
                while (iter != null) {
                    stack.push(iter);
                    iter = iter.left;
                }
            }
            return ret;
        }
    }
    public boolean isValidBST(TreeNode root) {
        TreeIterator iter = new TreeIterator(root);
        int prev = Integer.MIN_VALUE;
        boolean isFirst = true;

        while(iter.hasNext()) {
            int cur = iter.next().val;
            if (!isFirst && cur <= prev) {
                return false;
            }
            isFirst = false;
            prev = cur;
        }
        return true;
    }
}
