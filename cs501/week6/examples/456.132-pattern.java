/*
 * [456] 132 Pattern
 *
 * https://leetcode.com/problems/132-pattern
 *
 * algorithms
 * Medium (28.81%)
 * Total Accepted:    13.4K
 * Total Submissions: 46.6K
 * Testcase Example:  '[1,2,3,4]'
 *
 *
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a
 * subsequence ai, aj, ak such
 * that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n
 * numbers as input and checks whether there is a 132 pattern in the list.
 *
 * Note: n will be less than 15,000.
 *
 * Example 1:
 *
 * Input: [1, 2, 3, 4]
 *
 * Output: False
 *
 * Explanation: There is no 132 pattern in the sequence.
 *
 *
 *
 * Example 2:
 *
 * Input: [3, 1, 4, 2]
 *
 * Output: True
 *
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 *
 *
 * Example 3:
 *
 * Input: [-1, 3, 2, 0]
 *
 * Output: True
 *
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1,
 * 3, 0] and [-1, 2, 0].
 *
 *
 */
class Solution {
    class Pair{
      public int min;
      public int max;
      public Pair(int min, int max) {
        this.min = min;
        this.max = max;
      }

    }
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
          return false;
        }
        Deque<Pair> stack = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
          int cand = nums[i];
          if (stack.isEmpty() || cand < stack.peek().min) {
            stack.push(new Pair(cand,cand));
          } else if (cand > stack.peek().min) {
            Pair last = stack.pop();
            if (cand < last.max) {
              return true;
            }
            last.max = cand;
            while(!stack.isEmpty() && cand >= stack.peek().max) {
              stack.pop();
            }
            if (!stack.isEmpty() && cand > stack.peek().min) {
              return true;
            }
            stack.push(last);
          }
        }
        return false;
    }
}
