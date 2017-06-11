//Notes:
// 1. The key point here is divide and conquer
// 2. In helper function, if we make sure we return immediately if start == end, we don't need to consider start > end
// 3. In merge function, recursive solution will work for leetcode, although it is highly not recommended to use the recursive solution.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return helper(lists,0,lists.length -1);
    }
    ListNode helper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = helper(lists, start, mid);
        ListNode right = helper(lists, mid+1, end);
        return merge(left, right);
    }
    ListNode merge(ListNode left, ListNode right) {
        if(left == null || right == null) {
            return (left == null) ? right : left;
        }
        //recursive version
        if (left.val <= right.val) {
            left.next = merge(left.next,right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }
}
