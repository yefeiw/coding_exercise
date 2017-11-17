/*
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists
 *
 * algorithms
 * Hard (27.62%)
 * Total Accepted:    176.7K
 * Total Submissions: 639.8K
 * Testcase Example:  '[]'
 *
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 *
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
    public ListNode mergeKLists(ListNode[] lists) {
        return helper(lists, 0, lists.length -1);
    }

    private ListNode helper(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start)  / 2;
        ListNode left = helper(lists, start, mid);
        ListNode right = helper(lists, mid+1, end);
        return mergeTwoLists(left,right);
    }

    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode iter = dummy;
        while(left != null && right != null) {
            ListNode next = (left.val < right.val) ? left : right;
            iter.next = next;
            if (next == left)  left = left.next;
            else right = right.next;
            iter = iter.next;
        }
        if (left == null) iter.next = right;
        else iter.next = left;
        return dummy.next;
    }
}
