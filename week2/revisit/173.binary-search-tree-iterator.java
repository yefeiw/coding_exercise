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
    // Internal States
    // Storing the root
    TreeNode next;
    //Stack to track the current path
    Deque<TreeNode> stack;
   // Important utility function to track the current stack usage
    void furnishStack(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    public BSTIterator(TreeNode root) {
        next = null;
        stack = new ArrayDeque<TreeNode>();
        furnishStack(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (next != null) {
            furnishStack(next);
            next = null;
        }
        return (!stack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext()) {
            return 0;
        }
        TreeNode ret = stack.pop();
        next = ret.right;
        return ret.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
