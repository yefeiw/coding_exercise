/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
	//data structure
	Deque<TreeNode> stack;
    public BSTIterator(TreeNode root) {
    	stack = new ArrayDeque<TreeNode>();
    	while (root != null) {
    		stack.push(root);
    		root = root.left;
    	}
        
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (!stack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode top  =stack.pop();
        if (top.right != null) {
        	TreeNode iter = top.right;
        	while(iter != null) {
        		stack.push(iter);
        		iter = iter.left;
        	}
        }
        return top.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */