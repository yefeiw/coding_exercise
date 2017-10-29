/*
 * [366] Find Leaves of Binary Tree
 *
 * https://leetcode.com/problems/find-leaves-of-binary-tree
 *
 * algorithms
 * Medium (60.15%)
 * Total Accepted:    20.4K
 * Total Submissions: 34K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves, repeat until the tree is empty.
 *
 *
 *
 * Example:
 * Given binary tree
 *
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \
 * ⁠     4   5
 *
 *
 *
 * Returns [4, 5, 3], [2], [1].
 *
 *
 *
 * Explanation:
 *
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 *
 * ⁠         1
 * ⁠        /
 * ⁠       2
 *
 *
 *
 * 2. Now removing the leaf [2] would result in this tree:
 *
 * ⁠         1
 *
 *
 *
 * 3. Now removing the leaf [1] would result in the empty tree:
 *
 * ⁠         []
 *
 *
 *
 *
 * Returns [4, 5, 3], [2], [1].
 *
 *
 * Credits:Special thanks to @elmirap for adding this problem and creating all
 * test cases.
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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        int level = getLevel(root);
        for(int i = 0; i < level; i++) {
            ret.add(new ArrayList<>());
        }
        helper(root, ret);
        return ret;
    }

    //util function
    private int helper(TreeNode root, List<List<Integer>> ret) {
        if (root == null) {
            return -1;
        }
        int left = helper(root.left,ret);
        int right = helper(root.right,ret);
        int selfLevel = Math.max(left,right) + 1;
        ret.get(selfLevel).add(root.val);
        return selfLevel;
    }

    private int getLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getLevel(root.left);
        int right = getLevel(root.right);
        return Math.max(left,right) + 1;
    }
}
