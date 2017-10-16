/*
 * [445] Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii
 *
 * algorithms
 * Medium (45.97%)
 * Total Accepted:    33.2K
 * Total Submissions: 72.1K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the
 * lists is not allowed.
 *
 *
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode ret =   addList(l1,l2);
        return reverseList(ret);
    }

    private ListNode reverseList(ListNode head) {
      ListNode pre = null;
      while(head != null) {
        ListNode next = head.next;
        head.next = pre;
        pre = head;
        head = next;
      }
      return pre;
    }

    private ListNode addList(ListNode l1, ListNode l2) {
      if (l1 == null) return l2;
      if (l2 == null) return l1;
      ListNode dummy = new ListNode(0);
      ListNode iter = dummy;

      int carry = 0;
      while(l2 != null || l1 != null || carry > 0) {
        int sum = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val : 0) + carry;
        int digit = sum % 10;
        carry = sum / 10;
        iter.next = new ListNode(digit);

        iter = iter.next;
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
      }
      return dummy.next;
    }
}
