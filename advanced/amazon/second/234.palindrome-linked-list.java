/*
 * [234] Palindrome Linked List
 *
 * https://leetcode.com/problems/palindrome-linked-list
 *
 * algorithms
 * Easy (32.86%)
 * Total Accepted:    118.7K
 * Total Submissions: 361.2K
 * Testcase Example:  '[]'
 *
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
        	return true;
        }

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null && fast.next.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        }
        //At this point, the two pointers are a && fast.next.next != nullt\ most differ by 1
        ListNode later = slow.next;
        slow.next = null;
        later = reverse(later);
        fast = head;
        while(fast != null && later != null) {
        	if (fast.val != later.val) {
        		//restore state?
        		return false;
            }
            fast = fast.next;
            later = later.next; 
        }
        return true;
    }
    //util functions
    ListNode reverse(ListNode head) {
    	if (head == null) return head;
    	ListNode prev = null;
    	ListNode iter = head;
    	while(iter != null) {
    		ListNode next = iter.next;
    		iter.next = prev;
    		prev = iter;
    		iter = next;
    	}
    	return prev;
    }
}
