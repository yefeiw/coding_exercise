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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret =  new ArrayList<List<Integer>>();
        if (root == null) return ret;
        //zigzag level order traversal -> level order traversal
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                TreeNode front = queue.remove();
                if(count % 2 != 0) {
                    level.add(0,front.val);
                } else {
                    level.add(front.val);
                }
                if (front.left != null ) {
                    queue.add(front.left);
                }
                if (front.right !=  null) {
                    queue.add(front.right);
                }
            }
            count++;
            ret.add(level);
        }
        return ret;
    }
}
