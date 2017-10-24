/*
 * [21] Merge Two Sorted Lists
 *
 * https://leetcode.com/problems/merge-two-sorted-lists
 *
 * algorithms
 * Easy (39.27%)
 * Total Accepted:    269.6K
 * Total Submissions: 686.6K
 * Testcase Example:  '[]\n[]'
 *
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            ListNode ret = l1;
            ret.next = mergeTwoLists(l1.next,l2);
            return ret;
        } else {
            ListNode ret = l2;
            ret.next = mergeTwoLists(l1,l2.next);
            return ret;
        }
    }
}
