/*
 * [25] Reverse Nodes in k-Group
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group
 *
 * algorithms
 * Hard (30.84%)
 * Total Accepted:    102.2K
 * Total Submissions: 331.3K
 * Testcase Example:  '[]\n1'
 *
 * 
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * 
 * 
 * k is a positive integer and is less than or equal to the length of the
 * linked list. If the number of nodes is not a multiple of k then left-out
 * nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be
 * changed.
 * 
 * Only constant memory is allowed.
 * 
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * 
 * 
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * 
 * 
 * For k = 3, you should return: 3->2->1->4->5
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
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null) {
			return head;
		}
        //Three parts, pre, mid, next;
		ListNode preTail = null;
		ListNode midHead = head;
		ListNode midTail = null;
		ListNode nextHead = null;

		ListNode dummy = new ListNode(0);
		ListNode iter = dummy;
		preTail = iter;
		iter.next = head;
		while(true) {
			int i = 0;
			for (i = 0; i < k; i++) {
				if (iter == null || iter.next == null) break;
				iter = iter.next;
			}
			if (i < k) {
				return dummy.next;
			}
			midTail = iter;
			nextHead = iter.next;
        //split linked list
			iter.next = null;
			preTail.next = null;
        //reverse linked list;
			iter = reverse(iter);
        //get the linked list back together
			midHead.next = nextHead;
			preTail.next = midTail;
			preTail = midHead;
			midHead = nextHead;
			iter = midHead;
		}
	}
	private ListNode reverse(ListNode head) {
		ListNode prev = null;
		while(head!= null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
}
