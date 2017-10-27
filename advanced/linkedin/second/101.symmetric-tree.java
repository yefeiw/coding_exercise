/*
 * [101] Symmetric Tree
 *
 * https://leetcode.com/problems/symmetric-tree
 *
 * algorithms
 * Easy (39.28%)
 * Total Accepted:    204.1K
 * Total Submissions: 519.7K
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 *
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠/ \ / \
 * 3  4 4  3
 *
 *
 *
 * But the following [1,2,2,null,3,null,3]  is not:
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠  \   \
 * ⁠  3    3
 *
 *
 *
 *
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    //util functions
    private boolean helper(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2 == null;
        if (t2 == null) return t1 == null;
        if(!helper(t1.left,t2.right)) {
            return false;
        }
        if (!helper(t1.right, t2.left)) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return true;
    }
}
