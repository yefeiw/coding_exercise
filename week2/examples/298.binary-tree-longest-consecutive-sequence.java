/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int longestConsecutive(TreeNode root) {
        //this is a corner case handling.
        //The assumption of comparing this node vs. the parent in that this node is not null.
        //Therefore, we need to particularly handle the situation where even root is null
        if (root == null) return 0;
        return helper(root,null, 1);
    }
    int helper(TreeNode root, TreeNode parent, int count) {
        if (root == null) {
            return count;
        }
        //This is simple, telling whether there is such a consecutive path
        if(parent != null) {
            if (root.val == parent.val + 1) {
                count++;
            } else  {
                count = 1;
            }
        }
        //This is harder to get the actual maximum value.
        int left = helper(root.left, root, count);
        int right = helper(root.right, root, count);
        count = Math.max(count,Math.max(left,right));
        return count;
    }

}
