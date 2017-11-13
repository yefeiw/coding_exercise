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
      TreeLinkNode queue = root;
      TreeLinkNode level = new TreeLinkNode (0);
      while(queue != null) {
          level.next = null;
          TreeLinkNode current = level;
          while(queue != null) {
              if (queue.left != null) {
                  current.next = queue.left;
                  current = current.next;
              }
              if (queue.right != null) {
                  current.next = queue.right;
                  current = current.next;
              }
              queue = queue.next;
          }

          queue = level.next;
      }
      

    }
}
