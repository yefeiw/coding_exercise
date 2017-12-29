/*
 * [545] Boundary of Binary Tree
 *
 * https://leetcode.com/problems/boundary-of-binary-tree/description/
 *
 * algorithms
 * Medium (32.15%)
 * Total Accepted:    7.5K
 * Total Submissions: 23.3K
 * Testcase Example:  '[1,null,2,3,4]'
 *
 * Given a binary tree, return the values of its boundary in anti-clockwise
 * direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without
 * duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node. Right
 * boundary is defined as the path from root to the right-most node. If the
 * root doesn't have left subtree or right subtree, then the root itself is
 * left boundary or right boundary. Note this definition only applies to the
 * input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always
 * firstly travel to the left subtree if exists. If not, travel to the right
 * subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right
 * exchanged.
 *
 *
 * Example 1
 *
 * Input:
 * ⁠ 1
 * ⁠  \
 * ⁠   2
 * ⁠  / \
 * ⁠ 3   4
 *
 * Ouput:
 * [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means
 * you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have
 * [1,3,4,2].
 *
 *
 *
 *
 * Example 2
 *
 * Input:
 * ⁠   ____1_____
 * ⁠  /          \
 * ⁠ 2            3
 * ⁠/ \          /
 * 4   5        6
 * ⁠  / \      / \
 * ⁠ 7   8    9  10
 * ⁠
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to
 * definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have
 * [1,2,4,7,8,9,10,6,3].
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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ret = new ArrayList();
        if (root ==  null)  {
            return ret;
        }
        ret.add(root.val);
        addBoundary(root.left, ret,0);
        addLeaves(root.left,ret);
        addLeaves(root.right,ret);
        addBoundary(root.right, ret, 1);
        return ret;
    }

    private void addBoundary(TreeNode root, List<Integer> ret, int direction) {
        if (direction == 0) {
            if (root == null || isLeaf(root)) {
                return;
            }
            ret.add(root.val);
            if (root.left != null) {
                addBoundary(root.left,ret,0);
            } else {
                addBoundary(root.right,ret,0);
            }
        } else {
            if (root == null || isLeaf(root)) {
                return;
            }

            if (root.right != null) {
                addBoundary(root.right, ret, 1);
            } else {
                addBoundary (root.left, ret, 1);
            }
            ret.add(root.val);
        }
    }

    private boolean isLeaf(TreeNode root) {
        return (root.left == null && root.right == null);
    }

    private void addLeaves(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        //since we need to add the leaves in order, we need to traverse
        //the tree in order.
        addLeaves(root.left,ret);

        if (root.left == null && root.right == null) {
            ret.add(root.val);
            return;
        }
        addLeaves(root.right,ret);
    }


}
