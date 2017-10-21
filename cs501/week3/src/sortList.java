import java.util.*;

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //step 1, split list.
        ListNode left = head;
        ListNode right = splitList(head);
        
        //step 2, sort each list.
        left = sortList(left);
        right = sortList(right);
        //step 3, merge sorted list;
        return mergeSortedList(left,right);
    }
    ListNode splitList(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode ret = slow.next;
        slow.next = null;
        return ret;
    }
    ListNode mergeSortedList(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
        return dummy.next;
    }

}
public class sortList{
    public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 1;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
           ListNode input  = testInput.generateRandomListNode();
           testInput.printListNode(input);
           ListNode output = sol.sortList(input);
           testInput.printListNode(output);
        }
        System.out.println("Test Executed, please manually check input");
    }
}
/* test cases used at leetcode:
[]
[0]
[0,1,2,3,4,5,6,7]
[0,1]
[0,1,2]
*/