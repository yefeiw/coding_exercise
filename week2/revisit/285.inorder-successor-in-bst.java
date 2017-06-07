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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode prev = null;
        int key = p.val;
        while (cur != null && cur.val != key) {
            if(key < cur.val) {
                prev = cur;
                cur = cur.left;
            } else  {
                cur = cur.right;
            }
        }
        //if node p is not in the tree
        if (cur == null) return cur;
        if (p.right != null) {
            cur = p.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        } else {
            return prev;
        }
    }
}
