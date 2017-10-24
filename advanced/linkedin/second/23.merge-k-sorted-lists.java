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
        return mergeHelper(lists,0,lists.length-1);
    }

    //util functions
    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start > end) return null;
        if (start == end) return lists[start];

        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start,mid);
        ListNode right = mergeHelper(lists, mid+1,end);

        return mergeTwoLists(left,right);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode ret = null;
        if (l1.val < l2.val) {
            ret = l1;
            ret.next = mergeTwoLists(l1.next,l2);
        } else {
            ret = l2;
            ret.next = mergeTwoLists(l1,l2.next);
        }

        return ret;
    }
}
