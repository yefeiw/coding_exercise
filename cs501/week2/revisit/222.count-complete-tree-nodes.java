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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countHeight(root.left);
        int right = countHeight(root.right);
        //System.out.printf("left is %d and right is %d\n",left, right);
        if (left == right) {
            //if left == right, left is full, count right
            return (1<<left) + countNodes(root.right);
        } else {
            //if left != right, right is full, count left
            return (1<<right) + countNodes(root.left);
        }
    }
    int countHeight(TreeNode root) {
        int ret = 0;
        while(root != null) {
          root = root.left;
          ret++;
        }
        return ret;
    }
}
