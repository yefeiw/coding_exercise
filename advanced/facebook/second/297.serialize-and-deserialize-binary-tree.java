/*
 * [297] Serialize and Deserialize Binary Tree
 *
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree
 *
 * algorithms
 * Hard (33.54%)
 * Total Accepted:    73.3K
 * Total Submissions: 218.4K
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
        if (root == null) {
        	return "#";
        } else {
        	return Integer.toString(root.val)+","+serialize(root.left) + "," + serialize(root.right);
        }

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if (data.length() == 0) {
    		return null;
    	}
        String[] nodes = data.split(",");
        if (nodes.length == 0) {
        	return null;
        }
        int[] idx = new int[1];
        TreeNode root = convert(nodes,idx);
        return root;
    }

    private TreeNode convert(String[] nodes, int[] idx) {
    	//System.out.println("nodes length is " + nodes.length + " and idx at "+idx[0]);
    	if (nodes[idx[0]].equals("#")) {
    		idx[0]++;
    		return null;
    	}
    	TreeNode root = new TreeNode (Integer.parseInt(nodes[idx[0]]));
    	idx[0]++;
    	root.left = convert(nodes, idx);
    	root.right = convert(nodes, idx);
    	return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
