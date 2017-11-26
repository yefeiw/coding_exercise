/*
 * [272] Closest Binary Search Tree Value II
 *
 * https://leetcode.com/problems/closest-binary-search-tree-value-ii
 *
 * algorithms
 * Hard (39.38%)
 * Total Accepted:    19.8K
 * Total Submissions: 50.3K
 * Testcase Example:  '[1]\n0.000000\n1'
 *
 *
 * Given a non-empty binary search tree and a target value, find k values in
 * the BST that are closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that
 * are closest to the target.
 *
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n)
 * runtime (where n = total nodes)?
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> predecessor = new ArrayDeque();
        Deque<Integer> successor = new ArrayDeque();

        initPredecessor(root,target, k, predecessor);
        initSuccessor(root,target,k,successor);

        List<Integer> ret = new ArrayList<>();
        while (ret.size() < k) {
            double predecessorDist = (predecessor.isEmpty()) ? Double.MAX_VALUE : target - predecessor.peek();
            double successorDist = (successor.isEmpty()) ? Double.MAX_VALUE : successor.peek() - target;
            if (predecessorDist < successorDist) {
                ret.add(predecessor.pop());
            } else {
                ret.add(successor.pop());
            }
        }
        return ret;
    }

    //util functions
    private void initPredecessor(TreeNode root, double target, int k, Deque<Integer> predecessor) {
        if (root == null) {
            return;
        }
        initPredecessor(root.right,target, k, predecessor);
        if (root.val <= target && predecessor.size() < k) {
            predecessor.add(root.val);
        }
        initPredecessor(root.left,target,k,predecessor);
    }

    private void initSuccessor(TreeNode root, double target, int k, Deque<Integer> successor) {
        if (root == null) {
            return;
        }

        initSuccessor(root.left,target,k,successor);
        if (root.val > target && successor.size() < k) {
            successor.add(root.val);
        }
        initSuccessor(root.right,target,k,successor);
    }
}
