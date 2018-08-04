 import java.util.*;
 import java.lang.*;

class ListNode {
    public ListNode prev;
    public ListNode next;
    public int val;
    public ListNode (int val) {
        this.val = val;
    }
}


public class linkedListGC {
    public static void main(String[] args) throws InterruptedException{
        int[] values = {3,4,5,2,1,6};
        ListNode head = setupListNode(values);
        System.gc();
        Solution s = new Solution();
        head = s.removeFirstOccurrence(head, 3);
        System.gc();
        List<Integer> s1 = new ArrayList<>();
        List<Integer> s2 = new ArrayList<>();
        s1 = null;
        System.gc();
        s2 = null;
        System.gc();

        System.out.println("The new head is " + head.val);
    }

    static private ListNode setupListNode(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        for(int val : values) {
            ListNode newNode = new ListNode(val);
            head.next = newNode;
            newNode.prev = head;
            head = head.next;
        }
        if (dummy.next != null) {
            dummy.next.prev = null;
        }

        return dummy.next;
    }
}