/*
 * [206] Reverse Linked List
 *
 * https://leetcode.com/problems/reverse-linked-list
 *
 * algorithms
 * Easy (45.64%)
 * Total Accepted:    256.3K
 * Total Submissions: 561.6K
 * Testcase Example:  '[]'
 *
 * Reverse a singly linked list.
 * 
 * click to show more hints.
 * 
 * Hint:
 * A linked list can be reversed either iteratively or recursively. Could you
 * implement both?
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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null) {
        	ListNode next = head.next;
        	head.next = prev;
        	prev = head;
        	head = next;
        }
        return prev;
    }
}
