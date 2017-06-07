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
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        helper(root,ret);
        return ret;
    }
    int helper(TreeNode root, List<List<Integer>> ret) {
        //return -1 so that if root is null, parent will return 0;
        if (root == null) {
            return -1;
        }
        int left = helper(root.left, ret);
        int right = helper(root.right,ret);
        //get the max level number
        int cur = Math.max(left,right) +1;
        //add a level if the current level from the bottom is not yet represented in the ret.
        //What an idea!
        if(ret.size() <= cur) {
            ret.add(new ArrayList<Integer>());
        }
        ret.get(cur).add(root.val);
        return cur;
    }
}
