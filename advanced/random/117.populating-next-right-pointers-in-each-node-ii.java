/*
 * [117] Populating Next Right Pointers in Each Node II
 *
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
 *
 * algorithms
 * Medium (33.78%)
 * Total Accepted:    105K
 * Total Submissions: 310.8K
 * Testcase Example:  '{}'
 *
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous
 * solution still work?
 *
 * Note:
 * You may only use constant extra space.
 *
 *
 * For example,
 * Given the following binary tree,
 *
 * ⁠        1
 * ⁠      /  \
 * ⁠     2    3
 * ⁠    / \    \
 * ⁠   4   5    7
 *
 *
 *
 * After calling your function, the tree should look like:
 *
 * ⁠        1 -> NULL
 * ⁠      /  \
 * ⁠     2 -> 3 -> NULL
 * ⁠    / \    \
 * ⁠   4-> 5 -> 7 -> NULL
 *
 *
 */
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
      if (root == null) {
        return;
      }
      TreeLinkNode cur = root;
      TreeLinkNode child = null;
      TreeLinkNode prev = null;
      while(cur != null) {
        if (child == null) {
          if (cur.left != null) child = cur.left;
          if (cur.right != null) child = cur.right;
        }
        TreeLinkNode first = (cur.left != null) ? cur.left : cur.right;
        if(prev != null && first != null) {
          prev.next = first;
        }
        if (first != null && prev == null) {
          prev = first;
        }
        if (first == cur.left && cur.right != null) {
          cur.left.next = cur.right;
          prev = cur.right;
        }
        cur = cur.next;
        if (cur == null && child != null) {
          cur = child;
          child = null;
          prev = null;
        }
      }
    }
}
