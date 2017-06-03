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
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        int[] res = new int[1];//result value.
        res[0] = Integer.MIN_VALUE;
        helper(root,k, res);
        return res[0];
    }
    int helper(TreeNode root, int k, int[] res) {
        //if walked into leave or we have already found the answer just return
        if (root == null || res[0] != Integer.MIN_VALUE) {
            return 0;
        }
        int left = helper(root.left, k, res);
        //right = all - left - root
        int right = helper(root.right, k-left-1,res);
        //special case: result already found
        if (left == k - 1) {
            res[0] = root.val;
        }
        return left+right+1;
    }
}
