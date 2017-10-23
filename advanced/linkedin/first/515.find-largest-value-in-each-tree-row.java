/*
 * [515] Find Largest Value in Each Tree Row
 *
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row
 *
 * algorithms
 * Medium (55.05%)
 * Total Accepted:    26.5K
 * Total Submissions: 48.2K
 * Testcase Example:  '[1,3,2,5,3,null,9]'
 *
 * You need to find the largest value in each row of a binary tree.
 *
 * Example:
 *
 * Input:
 *
 * ⁠         1
 * ⁠        / \
 * ⁠       3   2
 * ⁠      / \   \
 * ⁠     5   3   9
 *
 * Output: [1, 3, 9]
 *
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null) {
            return ret;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                TreeNode front = queue.remove();
                max = Math.max(max,front.val);
                if (front.left != null) {
                    queue.add(front.left);
                }
                if (front.right != null) {
                    queue.add(front.right);
                }
            }
            ret.add(max);
        }
        return ret;
    }
}
