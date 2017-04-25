import java.util.*;

class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (null == root) {
            return root;
        }
        getSum(root);
        return root;
    }
    public void getSum(TreeNode root) {
        if (root == null) {
            return;
        }
        getSum(root.right);
        root.val += sum;
        sum = root.val;
        getSum(root.left);
    }
}
public class GreaterTree{
   public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    
    System.out.println("TreeNode: too hard to test manually, please verify on leetcode");
}
}
