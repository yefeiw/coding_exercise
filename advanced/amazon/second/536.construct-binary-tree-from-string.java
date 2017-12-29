/*
 * [536] Construct Binary Tree from String
 *
 * https://leetcode.com/problems/construct-binary-tree-from-string/description/
 *
 * algorithms
 * Medium (42.82%)
 * Total Accepted:    8.9K
 * Total Submissions: 20.9K
 * Testcase Example:  '"4(2(3)(1))(6(5))"'
 *
 * You need to construct a binary tree from a string consisting of parenthesis
 * and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by
 * zero, one or two pairs of parenthesis. The integer represents the root's
 * value and a pair of parenthesis contains a child binary tree with the same
 * structure.
 *
 * You always start to construct the left child node of the parent first if it
 * exists.
 *
 * Example:
 *
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *
 * ⁠      4
 * ⁠    /   \
 * ⁠   2     6
 * ⁠  / \   /
 * ⁠ 3   1 5
 *
 *
 *
 * Note:
 *
 * There will only be '(',  ')',  '-' and  '0' ~ '9' in the input string.
 * An empty tree is represented by "" instead of "()".
 *
 *
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode str2tree(String s) {
        if (s.length() == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack();
        //TODO: get a better idea about this method
        for (int i = 0, j = i; i < s.length(); i++, j = i) {
            //System.out.println("i is "+i);
            //System.out.println("current top is" + (stack.isEmpty() ? "null" : stack.peek().val));
            char c = s.charAt(i);
            if (c == ')') {
                //assume valid string, so no check
                stack.pop();
            } else if (c == '-' || Character.isDigit(s.charAt(i))) {
                //valid numbers here
                while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    i++;
                }
                int cand = Integer.parseInt(s.substring(j,i+1));
                TreeNode currentNode = new TreeNode(cand);
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) parent.left = currentNode;
                    else parent.right = currentNode;
                }
                //System.out.println("pushing "+ currentNode.val);
                stack.push(currentNode);
            }
        }

        return (stack.isEmpty()) ? null : stack.peek();

    }
}
