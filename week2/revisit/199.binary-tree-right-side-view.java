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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
            return ret;
        }
        bfsHelper(root,ret);
        return ret;
    }
    void bfsHelper(TreeNode root, List<Integer> ret) {
        Deque<TreeNode> queue =  new ArrayDeque<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode front  = queue.remove();
                if (i == size - 1) {
                    ret.add(front.val);
                }
                if (front.left != null) queue.add(front.left);
                if (front.right != null) queue.add(front.right);

            }
        }
    }
}
