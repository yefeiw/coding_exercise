/*
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists
 *
 * algorithms
 * Hard (27.37%)
 * Total Accepted:    165.4K
 * Total Submissions: 604.5K
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
        if (lists.length == 0) {
        	return null;
        }
        int m =  lists.length;
        return helper(lists, 0, m-1);
    }
    private ListNode helper(ListNode[] lists, int start, int end) {
    	if (start == end) {
    		return lists[start];
    	}
    	if (start > end) {
    		return null;
    	}
    	ListNode left = helper(lists, start, start + (end - start) / 2);
    	ListNode right = helper(lists, start +(end - start) /2 + 1, end);
    	return merge(left, right);
    }
    private ListNode merge(ListNode left, ListNode right) {
    	if(left == null) return right;
    	if(right == null) return left;
    	ListNode ret = null;
    	if(left.val < right.val) {
    		ret = left;
    		left.next = merge(left.next, right);
    	} else {
    		ret = right;
    		ret.next = merge(left, right.next);
    	}
    	return ret;
    }
}
