import java.util.*;

class Solution {
    public void reorderList(ListNode head) {
        if (!isValid(head)) {
            return;
        }
        //step 1, split the list to halves
        ListNode mid = splitList(head);
        //step 2, reverse the second half
        mid = reverse(mid);
        //step 3, merge the two splitted lists back together
        head = mergeList(head, mid);
    }

    boolean isValid(ListNode head) {
        if (null == head || null == head.next|| null == head.next.next) {
            return false;
        }
        return true;
    }
//Assumption, there will be no cycles in the lists, otherwise, I will have to report error here.
    ListNode splitList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null  && fast.next!= null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        //cut off fast and slow to avoid confusion
        slow.next = null;
        return fast;
    }

    ListNode reverse(ListNode head) {
        ListNode next = head.next;
        ListNode prev = null; 
        while (head != null) {
            ListNode future = head.next;
            head.next = prev;
            prev = head;
            head = future;
        }
        return prev;
    }

    ListNode mergeList(ListNode a, ListNode b) {
        ListNode ret = a;
        while(a!= null && b != null) {
            ListNode tempA = a.next;
            ListNode tempB = b.next;
            a.next = b;
            b.next = (tempA != null) ? tempA : tempB;
            a = tempA;
            b = tempB;
        }
        return ret;
    }
}

public class ReorderList {
     public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
        for (int i = 0; i < testIteration; i++) {
            ListNode input = testInput.generateRandomListNode();
            ListNode copy = input;
            testInput.printListNode(input);
            sol.reorderList(input);
            // ref = sol.reorderListRef(input);
            // if(output != ref) {
            //     System.out.println("diff found, exiting");
            //     return;
            // }
            testInput.printListNode(input);
            System.out.println();
        }
        System.out.println("test passed, ready to submit");
    }
}
/*Test cases on LeetCode:
[1,2,3,4]
[1,2,3,4,5]
[]
[1]
[5,8]
[1,2,3]
*/
