/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = splitList(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        ListNode merge = mergeList(left,right);
        return merge;
    }
    ListNode splitList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null&& fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode ret = slow.next;
        slow.next = null;
        return ret;
    }
    ListNode mergeList(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;
        if(left.val < right.val) {
            left.next = mergeList(left.next,right);
            return left;
        } else {
            right.next = mergeList(left, right.next);
            return right;
        }
    }
}

