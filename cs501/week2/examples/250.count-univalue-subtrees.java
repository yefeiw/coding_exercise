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
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //count is the return value to be passed in.
        int[] count = new int[1];
        helper(root, count);
        return count[0];
    }
    //Note, the return value in this example is boolean.
    //This is basically telling whether the tree is univalue, and if so, add it to the total count.
    boolean helper(TreeNode root, int[] count) {
        //Note, if root is null, we need to return true here.
        //The key to the problem is to add to the count, not true or false in the leaves.
        if(root == null) {
            return true;
        }
        boolean left = helper(root.left, count);
        boolean right  = helper(root.right, count);
        if(!left || !right) {
            //left or right subtrees are not uni-value
            //if that is the case, return false, and why?
            return false;
        }
        if (root.left != null && root.val != root.left.val)  {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        //reaching here means it is a uni-value tree
        count[0]++;
        return true;
    }
}
