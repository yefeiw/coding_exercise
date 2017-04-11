import java.util.*;

class Solution {
   public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if(root == null) {
            return ret;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<TreeNode>();
        deque.add(root);
        int level = 0;
        while (!deque.isEmpty()) {
            List<Integer> curRet = new LinkedList<Integer>();
            int curSize =  deque.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode cand = deque.remove();
                if(level % 2 == 0) {
                    curRet.add(cand.val);
                } else {
                    curRet.add(0,cand.val);
                }
                if(cand.left != null) deque.add(cand.left);
                if(cand.right != null) deque.add(cand.right);
            }
            ret.add(curRet);
            level++;
        }
        return ret;
    }
}
public class zigZag{
   public static void main (String args[]) {
    TreeNode root = new TreeNode(0);
    Solution sol = new Solution();
    List<List<Integer>> ret = sol.zigzagLevelOrder(root);

    System.out.println("Too hard to test, will see leetcode results.");
}
}
