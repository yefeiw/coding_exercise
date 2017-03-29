import java.util.*;

class Solution {
 public ListNode oddEvenList(ListNode head) {
    if (!isValid(head)) {
        return head;
    }
        //step 1, split the list to halves
    ListNode[] heads = new ListNode[2];
    heads = splitList(head);
        //step 3, merge the two splitted lists back together
    head = mergeList(heads);
    return head;
}

boolean isValid(ListNode head) {
    if (null == head || null == head.next|| null == head.next.next) {
        return false;
    }
    return true;
}
//Assumption, there will be no cycles in the lists, otherwise, I will have to report error here.
ListNode[] splitList(ListNode head) {
        ListNode ret = head.next;//returning even-positioned nodes.
        ListNode a = head, b = ret;
        while(null != a.next && null != b.next) {
            ListNode tempA = a.next.next;
            ListNode tempB = b.next.next;
            a.next = tempA;
            b.next = tempB;
            a = tempA;
            b = tempB;
        }
        if(null != a) a.next = null;
        if (null != b) b.next = null; 
        ListNode[] heads = new ListNode[2];
        heads[0] = head;
        heads[1] = ret; 
        return heads;
    }

    ListNode mergeList(ListNode[] heads) {
        ListNode a = heads[0];
        ListNode b= heads[1];
        ListNode a_end = a;

        while(a_end.next!= null) {
            a_end = a_end.next;
        }
        a_end.next = b;
        return a;
    }
}

public class OddEvenLinkedList {
   public static void main (String args[]) {
        // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 10;
    Input  testInput = new Input();
    Solution sol = new Solution();
    for (int i = 0; i < testIteration; i++) {
        ListNode input = testInput.generateRandomListNode();
        testInput.printListNode(input);
        ListNode output = sol.oddEvenList(input);
            // ref = sol.reorderListRef(input);
            // if(output != ref) {
            //     System.out.println("diff found, exiting");
            //     return;
            // }
        testInput.printListNode(output);
    }
    System.out.println("test passed, ready to submit");
}
}
/* test cases used at leetcode:
[]
[1]
[1,2]
[1,2,3]
[1,2,3,4]
*/