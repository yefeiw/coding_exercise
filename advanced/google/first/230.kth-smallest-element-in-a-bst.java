/*
 * [230] Kth Smallest Element in a BST
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 *
 * algorithms
 * Medium (45.36%)
 * Total Accepted:    140.4K
 * Total Submissions: 309.5K
 * Testcase Example:  '[1]\n1'
 *
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it.
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to
 * find the kth smallest frequently? How would you optimize the kthSmallest
 * routine?
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
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
    public int kthSmallest(TreeNode root, int k) {
       int left = count(root.left);
       int right = count(root.right);
       //System.out.println("left is "+ left + "and right is "+ right);
       if (left > k - 1) {
           return kthSmallest(root.left, k);
       } else if (left == k - 1) {
           return root.val;
       } else {
           //left < k - 1
           return kthSmallest(root.right, k - 1 - left);
       }

    }

    private int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
}
