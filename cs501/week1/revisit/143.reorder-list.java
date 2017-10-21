/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        if (null == head) {
            return;
        }
        ListNode mid = split(head);
        mid = reverse(mid);
        head = merge(head,mid);
    }
    ListNode split(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(null != fast && null != fast.next) {
           fast = fast.next;
           fast = fast.next;
           slow = slow.next;
        }
        ListNode ret = slow.next;
        slow.next = null;
        return ret;
    }
    ListNode reverse(ListNode head) {
        ListNode prev = null;
        while(null != head) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    ListNode merge(ListNode left, ListNode right) {
        ListNode ret = left;
        while(left != null && right != null) {
            ListNode lnext = left.next;
            ListNode rnext = right.next;
            left.next = right;
            if(lnext != null) right.next =lnext;
            left = lnext;
            right = rnext;
        }
        return ret;
    }
        
}
