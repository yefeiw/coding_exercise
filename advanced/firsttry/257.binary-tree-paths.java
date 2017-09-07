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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret =  new ArrayList<>();
        if(root == null) {
        	return ret;
        }
        List<Integer> stack = new ArrayList<>();
        helper(root, ret, stack);

        return ret;

    }

    private void helper(TreeNode root, List<String> ret, List<Integer> stack) {
    	if (root == null) {
    		return;
    	}
    	stack.add(root.val);
    	if (root.left == null && root.right == null) {
    		//leaf node, add and return
    		ret.add(printTree(stack));
    		stack.remove(stack.size() -1);
    		return;
    	}
    	helper(root.left, ret, stack);
    	helper(root.right,ret, stack);
    	stack.remove(stack.size() -1);
    }

    private String printTree(List<Integer> input) {
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < input.size(); i++) {
    		sb.append(input.get(i));
    		if (i < input.size() -1 ) {
    			sb.append("->");
    		}
    	}
    	return sb.toString();
    }
}