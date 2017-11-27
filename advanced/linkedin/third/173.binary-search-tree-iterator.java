/*
 * [173] Binary Search Tree Iterator
 *
 * https://leetcode.com/problems/binary-search-tree-iterator
 *
 * algorithms
 * Medium (41.62%)
 * Total Accepted:    99.1K
 * Total Submissions: 238K
 * Testcase Example:  '[]'
 *
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h)
 * memory, where h is the height of the tree.
 *
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    private Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque();
        TreeNode iter = root;
        while(iter != null) {
            stack.push(iter);
            iter = iter.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode top = stack.pop();
        int ret = top.val;
        if (top.right != null) {
            TreeNode iter = top.right;
            while(iter != null) {
                stack.push(iter);
                iter = iter.left;
            }
        }
        return ret;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
