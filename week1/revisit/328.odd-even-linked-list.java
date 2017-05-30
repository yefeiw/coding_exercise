/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode oddHead = head;
        ListNode oddTail = head;
        ListNode evenHead = even;
        while(odd != null && even != null) {
            ListNode nextOdd = (odd.next != null) ? odd.next.next : null;
            ListNode nextEven = (even.next != null) ? even.next.next : null;
            odd.next = nextOdd;
            even.next = nextEven;
            oddTail = odd;
            odd = nextOdd;
            even = nextEven;
        }
        if(odd != null) odd.next = evenHead;
        else oddTail.next = evenHead;
        return head;

    }
}
