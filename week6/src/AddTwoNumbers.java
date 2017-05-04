import java.util.*;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ArrayDeque<ListNode> stack = new ArrayDeque<ListNode>();
        int lLen = getLength(l1);
        int rLen = getLength(l2);
        if (lLen == 0) return l2;
        if (rLen == 0) return l1;
        int round = Math.max(lLen, rLen);
        ListNode tail = dummy;
        for(int i = round-1; i >=0; i--) {
            int left = getInput(l1,i,lLen);
            int right = getInput(l2,i,rLen);
            int sum = (left + right) %10;
            int carry = (left + right) / 10;
            while(carry > 0) {
                int tempSum = 0;
                if (stack.isEmpty()) {
                    ListNode newHead = new ListNode(0);
                    newHead.next = dummy.next;
                    dummy.next = newHead;
                    stack.push(newHead);
                    if(tail == dummy) {
                        tail = tail.next;
                    }
                }
                ListNode parent = stack.pop();
                tempSum = (parent.val + carry) % 10;
                carry = (parent.val + carry) / 10;
                parent.val = tempSum;
            }
            ListNode newTail = new ListNode(sum);
            tail.next = newTail;
            stack.push(newTail);
            tail = tail.next;
            l1 = advanceInput(l1,i,lLen);
            l2 = advanceInput(l2,i,rLen);
        }
        return dummy.next;
    }
    //get the length of the current linked list.
    int getLength(ListNode head) {
        if (null == head) return 0;
        int ret = 0;
        while(null != head) {
            ret++;
            head = head.next;
        }
        return ret;
    }
    
    //tell if we need to output zero or the current value
    int getInput(ListNode input, int round, int length) {
        if (round >= length) return 0;
        else return input.val;
    }
    //tell wehther we need to advance the pointer
    ListNode advanceInput(ListNode input, int round, int length) {
        if (round >= length) return input;
        else return input.next;
    }
}
public class AddTwoNumbers {
	 public static void main (String args[]) {
        int testIteration = 10;
        Input  testInput = new Input();
        Solution sol = new Solution();
  
        for (int i = 0; i < testIteration; i++) {
            ListNode l1 = testInput.generateRandomListNode();
            ListNode l2 = testInput.generateRandomListNode();
            System.out.println("input: ");
            testInput.printListNode(l1);
            testInput.printListNode(l2);
            ListNode output = sol.addTwoNumbers(l1,l2);
            System.out.println("output: ");
            testInput.printListNode(output);

        }  

        System.out.println("Test exeucted with no crashes, please verify output manually");
    }
}