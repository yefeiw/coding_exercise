/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
      if (head == null) {
          return head;
      }
    Map<RandomListNode, RandomListNode> dict = new HashMap<RandomListNode, RandomListNode>();
    RandomListNode newHead = new RandomListNode(head.label);
    RandomListNode ret = newHead;
    dict.put(newHead, head);
    while(head != null) {
        //construct newHead->next;
        if(head.next != null) {
            RandomListNode next = dict.containsKey(head.next)?dict.get(head.next) : new RandomListNode(head.next.label);
            dict.put(head.next,next);
            newHead.next = next;
        }
        //construct newHead->random;
        if(head.random != null) {
            RandomListNode random = dict.containsKey(head.random) ? dict.get(head.random) : new RandomListNode(head.random.label);
            dict.put(head.random,random);
            newHead.random = random;
        }
        //advance pointers
        head = head.next;
        newHead = newHead.next;
    }
    return ret;
        
    }

}
