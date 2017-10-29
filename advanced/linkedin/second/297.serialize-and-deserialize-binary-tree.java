/*
 * [297] Serialize and Deserialize Binary Tree
 *
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree
 *
 * algorithms
 * Hard (33.86%)
 * Total Accepted:    79.8K
 * Total Submissions: 235.7K
 * Testcase Example:  '[]'
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 *
 * For example, you may serialize the following tree
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * ⁠    / \
 * ⁠   4   5
 *
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a
 * binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 *
 *
 *
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 *
 *
 * Credits:Special thanks to @Louis1992 for adding this problem and creating
 * all test cases.
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderHelper(root,sb);
        return sb.toString();
    }

    private void preOrderHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#");
            sb.append(",");
        } else {
            sb.append(root.val).append(",");
            preOrderHelper(root.left,sb);
            preOrderHelper(root.right,sb);
        }

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        //build a queue
        Deque<String> queue = new ArrayDeque<>();
        for(String token : tokens) {
            if (token.length() > 0) {
                queue.add(token);
            }
        }
        return buildTree(queue);
    }

    //util function
    private TreeNode buildTree(Deque<String> queue) {
        String front = queue.remove();
        if (front.equals("#")) {
            return null;
        }
        TreeNode ret = new TreeNode(Integer.parseInt(front));
        ret.left = buildTree(queue);
        ret.right = buildTree(queue);
        return ret;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
