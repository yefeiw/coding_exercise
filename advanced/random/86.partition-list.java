/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
    	if (head == null) return head;
        ListNode smallerHead = null;
        ListNode largerHead = null;

        ListNode iter = head;
        ListNode smallerIter = null;
        ListNode largerIter = null;
        while(iter!= null) {
        	if (iter.val < x) {
        		if(smallerHead == null) {
        			smallerHead = iter;
        			smallerIter = iter;
        		}else {
        			smallerIter.next = iter;
        			smallerIter = smallerIter.next;
        		}
        	} else {
        		if (largerHead == null) {
        			largerHead = iter;
        			largerIter = iter;
        		} else {
        			largerIter.next = iter;
        			largerIter = largerIter.next;
        		}
        	}
        	ListNode next = iter.next;
        	iter.next = null;
        	iter = next;
        }
        if (smallerIter != null) smallerIter.next = largerHead;
        return (smallerHead != null) ? smallerHead : largerHead;
    }
}