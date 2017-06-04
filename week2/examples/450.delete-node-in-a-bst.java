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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        //The reason for this dummy node is to keep a previous node.
        //Note that here, the root could also be deleted.
        TreeNode dummy = new TreeNode(Integer.MIN_VALUE);
        dummy.left = root;
        TreeNode prev = dummy;
        TreeNode cur = root;
        while(cur != null && cur.val != key) {
            prev = cur;
            cur = (key < cur.val) ? cur.left : cur.right;
        }
        if (cur == null) { //node is not found, return
            return root;
        }
        //Here we have found both the root and the immediate parent.
        //case 1. Both children exists
        if (cur.left != null && cur.right != null) {
            prev = cur;
            TreeNode target = cur;
            cur = cur.right;
            while(cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
            //Here we have found the value of cur, exchange but we should NOT remove.
            //Because the removed node will not have two children in this case.
            target.val = cur.val;
        }
        //case 2 no children
        if (cur.left == null && cur.right == null) {
            if (cur == prev.left) prev.left = null;
            else prev.right = null;
            return dummy.left;
        }
        //case 3 one children
        //reaching here means there is only one children, no need to else
        TreeNode child = (cur.left != null) ? cur.left : cur.right;
        if (cur == prev.left) prev.left = child;
        else prev.right = child;
        return dummy.left;
    }
}
