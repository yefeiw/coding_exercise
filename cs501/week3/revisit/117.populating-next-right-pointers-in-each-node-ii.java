/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        //deque for BFS
        Deque<TreeLinkNode> queue = new ArrayDeque<TreeLinkNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size =  queue.size();
            TreeLinkNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode front = queue.remove();
                if(prev != null) prev.next = front;
                prev = front;
                if(front.left != null) queue.add(front.left);
                if(front.right != null) queue.add(front.right);
            }
        }

    }
}
