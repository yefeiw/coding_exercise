import java.util.*;

class Solution {
    public void connect(TreeLinkNode root) {
        if (null == root) {
            return;
        }
        ArrayDeque<TreeLinkNode> queue = new ArrayDeque();
        queue.add(root);
        while(!queue.isEmpty()) {
            int curSize = queue.size();
            TreeLinkNode previous = null;
            for (int i = 0; i < curSize; i++) {
                TreeLinkNode cand = queue.remove();
                if ( i > 0) {
                    previous.next = cand;
                }
                previous = cand;
                if (null != cand.left) queue.add(cand.left);
                if (null != cand. right) queue.add(cand.right);
            }

        }
    }
}
public class rightNode{
   public static void main (String args[]) {

    System.out.println("Too hard to test, will see leetcode results.");
}
}
